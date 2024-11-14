package io.github.qwerty770.mcmod.spmreborn.advancement;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class BalancedDietHelper {
    private BalancedDietHelper() {}

    // Update to Minecraft 1.21 -- 2024/10/19 Modify the advancement holder
    public static AdvancementHolder setupCriteria(AdvancementHolder holder) {
        Advancement advancement = holder.value();
        List<Item> items = SPRMain.ALL_SWEET_POTATOES.stream().toList();
        Map<String, Criterion<?>> criteria = advancement.criteria();
        List<List<String>> requirements = advancement.requirements().requirements();
        for (Item item : items) {
            String name = "spmreborn:balanced_diet_food_" + item.toString();
            ItemPredicate.Builder builder = ItemPredicate.Builder.item();
            builder.items = Optional.of(HolderSet.direct(Holder.direct(item)));
            criteria.put(name, ConsumeItemTrigger.TriggerInstance.usedItem(builder);
            ArrayList<String> requirement = new ArrayList<>();
            requirement.add(name);
            requirements.add(requirement);
        }
        return new AdvancementHolder(holder.id(), new Advancement(advancement.parent(), advancement.display(),
                advancement.rewards(), criteria, new AdvancementRequirements(requirements), advancement.sendsTelemetryEvent()));
    }
}
