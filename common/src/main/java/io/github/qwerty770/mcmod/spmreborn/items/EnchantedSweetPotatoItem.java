package io.github.qwerty770.mcmod.spmreborn.items;

import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import io.github.qwerty770.mcmod.spmreborn.util.inventory.PeelInserter;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoStatus;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EnchantedSweetPotatoItem extends EnchantedItem implements SweetPotatoProperties {
    // Update to Minecraft 1.20 -- 2023/10/30  Removed all usages of net.fabricmc.fabric.api.util.NbtType
    // Update to Minecraft 1.21 -- 2024/10/17  Replaced NBT with Data Component
    private final SweetPotatoType sweetPotatoType;

    public EnchantedSweetPotatoItem(Properties settings, SweetPotatoType type) {
        super(settings.food(Objects.requireNonNull(type.getComponent(SweetPotatoStatus.ENCHANTED)).asFoodComponent()));
        this.sweetPotatoType = type;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        if (user instanceof Player playerEntity) {
            playerEntity.awardStat(SweetPotatoStats.SWEET_POTATO_EATEN);
            if (!((Player) user).getAbilities().instabuild)
                PeelInserter.run(playerEntity);
        }

        if (!world.isClientSide) {
            Optional<List<MobEffectInstance>> statusEffectInstances = calcEffect(stack);
            statusEffectInstances.ifPresent(set -> set.forEach(statusEffectInstance -> {
                if (!statusEffectInstance.getEffect().value().isInstantenous()) {
                    user.addEffect(new MobEffectInstance(statusEffectInstance));
                } else {
                    statusEffectInstance.getEffect().value()
                    .applyInstantenousEffect((ServerLevel) world, user, user, user, statusEffectInstance.getAmplifier(), 1.0D);
                }
            }));
        }

        return super.finishUsingItem(stack, world, user);
    }

    public static Optional<List<MobEffectInstance>> calcEffect(ItemStack stack) {
        Item item = stack.getItem();
        if (!(item instanceof EnchantedSweetPotatoItem)) return Optional.empty();
        List<MobEffectInstance> effects = stack.get(SweetPotatoDataComponentTypes.STATUS_EFFECTS.get());
        return Optional.ofNullable(effects);
    }

    public static void applyEffects(ItemStack stack, List<MobEffectInstance> effects, @Nullable Integer displayIndex) {
        stack.applyComponents(DataComponentMap.builder().set(SweetPotatoDataComponentTypes.STATUS_EFFECTS.get(), effects).build());
        if (displayIndex != null) {
            stack.applyComponents(DataComponentMap.builder().set(SweetPotatoDataComponentTypes.DISPLAY_INDEX.get(), displayIndex).build());
        }
    }

    @Deprecated
    @SuppressWarnings("unused")
    private static MobEffectInstance calcEffect() {
        return new MobEffectInstance(MobEffects.LUCK, 200, 1);    // Luck II 10s
        // Remember, this is just a trial.
        // The REAL calculation should be added later.
        // teddyxlandlee, please decide the details with your group. 13 Jun 2020 night
    }

    @Override
    public SweetPotatoStatus getStatus() {
        return SweetPotatoStatus.ENCHANTED;
    }

    @Override
    public SweetPotatoType asType() {
        return this.sweetPotatoType;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);
        MutableComponent mainTip = Component.translatable("tooltip.spmreborn.enchanted_sweet_potato.effects");
        components.add(mainTip);

        List<MobEffectInstance> effects = stack.get(SweetPotatoDataComponentTypes.STATUS_EFFECTS.get());
        int index = stack.getOrDefault(SweetPotatoDataComponentTypes.DISPLAY_INDEX.get(), 0);
        if (index == -1 || effects == null) {
            mainTip.append(Component.translatable("effect.none").withStyle(ChatFormatting.ITALIC));
            return;
        }
        if (index == 99) {
            mainTip.append(Component.literal("???").withStyle(ChatFormatting.ITALIC));
            return;
        }
        Optional<List<MobEffectInstance>> statusEffectInstances = calcEffect(stack);
        if (statusEffectInstances.isEmpty()) {
            mainTip.append(Component.literal("???").withStyle(ChatFormatting.ITALIC));
            return;
        }
        List<MobEffectInstance> sei = statusEffectInstances.get();
        MobEffectInstance toBeShown = (sei.size() <= index) ? null : sei.get(index);
        if (toBeShown != null) {
            mainTip.append(Component.translatable(toBeShown.getDescriptionId()).withStyle(ChatFormatting.ITALIC));
            mainTip.append(" ").append(Component.translatable("potion.potency." + toBeShown.getAmplifier()));
            mainTip.append(Component.literal(" ...").withStyle(ChatFormatting.ITALIC));
        } else mainTip.append(Component.literal("???").withStyle(ChatFormatting.ITALIC));
    }
}
