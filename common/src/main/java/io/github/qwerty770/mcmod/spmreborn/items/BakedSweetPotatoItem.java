package io.github.qwerty770.mcmod.spmreborn.items;

import io.github.qwerty770.mcmod.spmreborn.stats.SweetPotatoStats;
import io.github.qwerty770.mcmod.spmreborn.util.inventory.PeelInserter;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoStatus;
import io.github.qwerty770.mcmod.spmreborn.items.sweetpotato.SweetPotatoType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BakedSweetPotatoItem extends Item implements SweetPotatoProperties {
    private final SweetPotatoType sweetPotatoType;

    public BakedSweetPotatoItem(Properties settings, SweetPotatoType type) {
        super(settings.food(Objects.requireNonNull(type.getComponent(SweetPotatoStatus.BAKED)).asFoodComponent()));
        this.sweetPotatoType = type;
    }

    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        super.finishUsingItem(stack, world, user);
        if (user instanceof Player playerEntity) {
            playerEntity.awardStat(SweetPotatoStats.SWEET_POTATO_EATEN);
            if (!((Player) user).getAbilities().instabuild)
                PeelInserter.run(playerEntity);
        }

        return stack;
    }

    @Override
    public SweetPotatoStatus getStatus() {
        return SweetPotatoStatus.BAKED;
    }

    @Override
    public SweetPotatoType asType() {
        return this.sweetPotatoType;
    }
}
