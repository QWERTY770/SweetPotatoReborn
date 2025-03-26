package io.github.qwerty770.mcmod.spmreborn.util.advancements;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdvancementModification {
    public static void checkBalancedDiet(AdvancementHolder holder, Registry<Item> registry) {
        // Used in mixin classes
        ResourceLocation location = holder.id();
        if ("husbandry/balanced_diet".equals(location.getPath()) && "minecraft".equals(location.getNamespace())) {
            Advancement advancement = holder.value();
            Map<String, Criterion<?>> criteria = advancement.criteria();
            ImmutableMap.Builder<String, Criterion<?>> builder = ImmutableMap.builder();
            for (Map.Entry<String, Criterion<?>> entry : criteria.entrySet()){
                builder.put(entry);
            }
            ImmutableList.Builder<List<String>> builder2 = ImmutableList.builder();
            builder2.addAll(advancement.requirements().requirements());

            List<Item> items = SPRMain.ALL_SWEET_POTATOES.stream().toList();
            for (Item item : items) {
                String name = "spmreborn:balanced_diet_food_" + Objects.requireNonNull(registry.getKey(item)).getPath();
                if (advancement.criteria().containsKey(name)) continue;
                builder.put(name, ConsumeItemTrigger.TriggerInstance.usedItem(registry, item));
                builder2.add(List.of(name));
            }
            advancement.criteria = builder.buildKeepingLast();
            advancement.requirements = new AdvancementRequirements(builder2.build());
        }
    }
}
