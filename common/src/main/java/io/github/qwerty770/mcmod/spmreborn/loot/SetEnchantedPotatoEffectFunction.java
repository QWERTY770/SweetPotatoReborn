package io.github.qwerty770.mcmod.spmreborn.loot;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.items.EnchantedSweetPotatoItem;
import io.github.qwerty770.mcmod.spmreborn.items.SweetPotatoProperties;
import io.github.qwerty770.mcmod.spmreborn.util.effects.StatusEffectInstances;
import io.github.qwerty770.mcmod.spmreborn.util.sweetpotato.SweetPotatoStatus;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Stream;

@ParametersAreNonnullByDefault
public class SetEnchantedPotatoEffectFunction extends LootItemConditionalFunction {
    private final List<Pair<MobEffectInstance, Float>> effects;  // deserialize to verify
    private final @Nullable Integer displayIndex;
    private final boolean displayRandomly;

    protected SetEnchantedPotatoEffectFunction(LootItemCondition[] lootItemConditions,
                                               List<Pair<MobEffectInstance, Float>> effects,
                                               @Nullable Integer displayIndex,
                                               boolean displayRandomly) {
        super(lootItemConditions);
        this.effects = effects;
        this.displayIndex = displayIndex;
        this.displayRandomly = displayRandomly;
    }

    @Override
    protected @NotNull ItemStack run(ItemStack itemStack, LootContext lootContext) {
        if (!((itemStack.getItem() instanceof SweetPotatoProperties spp) &&
                spp.getStatus().equals(SweetPotatoStatus.ENCHANTED)))
            return itemStack;
        Stream<MobEffectInstance> stream = effects.stream().flatMap(p -> {
            var source = lootContext.getRandom();
            if (source.nextFloat() < p.getRight())
                return Stream.of(p.getLeft());
            return Stream.empty();
        });
        EnchantedSweetPotatoItem.applyEffects(itemStack, stream, displayIndex);
        return itemStack;
    }

    @Override
    public @NotNull LootItemFunctionType getType() {
        return SPRMain.SET_ENCHANTED_POTATO_EFFECT.get();
    }

    public static class Serializer
            extends LootItemConditionalFunction.Serializer<SetEnchantedPotatoEffectFunction> {

        @Override
        public void serialize(JsonObject json, SetEnchantedPotatoEffectFunction function, JsonSerializationContext ctx) {
            super.serialize(json, function, ctx);
            var l = new JsonArray();
            for (var effect : function.effects) {
                JsonObject object = StatusEffectInstances.writeJson(effect.getLeft());
                object.addProperty("chance", effect.getRight());
                l.add(object);
            }
            json.add("effects", l);
            if (function.displayRandomly)
                json.addProperty("displayIndex", "random");
            else if (function.displayIndex != null)
                json.addProperty("displayIndex", function.displayIndex);
        }

        @Override
        public @NotNull SetEnchantedPotatoEffectFunction deserialize(JsonObject json,
                                                                     JsonDeserializationContext ctx,
                                                                     LootItemCondition[] lootItemConditions) {
            JsonArray arr = GsonHelper.getAsJsonArray(json, "effects");

            ImmutableList.Builder<Pair<MobEffectInstance, Float>> effectsBuilder = ImmutableList.builder();
            for (int i = 0; i < arr.size(); i++) {
                var e = arr.get(0);
                JsonObject obj = GsonHelper.convertToJsonObject(e, "effects[" + i + ']');
                MobEffectInstance effectInstance = StatusEffectInstances.readJson(obj);
                if (effectInstance != null)
                    effectsBuilder.add(Pair.of(effectInstance, GsonHelper.getAsFloat(obj, "chance", 1.0F)));
            }
            ImmutableList<Pair<MobEffectInstance, Float>> effects = effectsBuilder.build();

            @Nullable Integer displayIndex = null;
            var displayRandomly = false;
            if (GsonHelper.isNumberValue(json, "displayIndex")) {
                int i = GsonHelper.getAsInt(json, "displayIndex");
                if (i != -2) displayIndex = i;
            } else if (GsonHelper.isStringValue(json, "displayIndex")) {
                String s = GsonHelper.getAsString(json, "displayIndex");
                if ("random".equals(s)) {
                    //displayIndex = randomSource.nextInt(effects.size());
                    displayRandomly = true;
                }
            }
            return new SetEnchantedPotatoEffectFunction(lootItemConditions, effects, displayIndex, displayRandomly);
        }
    }
}
