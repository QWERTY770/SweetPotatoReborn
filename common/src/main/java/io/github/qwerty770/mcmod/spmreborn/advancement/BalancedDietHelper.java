package io.github.qwerty770.mcmod.spmreborn.advancement;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.mixin.acc.AdvancementTaskAccessor;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.world.item.Item;

import java.util.List;

public final class BalancedDietHelper {
    private BalancedDietHelper() {}

    public static void setupCriteria(Advancement.Builder task) {
        List<Item> itemList = SPRMain.ALL_SWEET_POTATOES.stream().toList();
        int itemListSize = itemList.size();

        AdvancementTaskAccessor taskAccessor = (AdvancementTaskAccessor) task;
        String[][] requirementsOld = taskAccessor.getRequirements();
        String[][] requirementsNew = new String[requirementsOld.length + itemListSize][];
        System.arraycopy(requirementsOld, 0, requirementsNew, itemListSize, requirementsOld.length);

        // Update to Minecraft 1.20 -- 2023/06/29
        int i = 0;
        for (Item item : itemList) {
            String requirementName = "spmreborn:balanced_diet_food_" + item.toString();
            task.addCriterion(requirementName, ConsumeItemTrigger.TriggerInstance.usedItem(item));
            requirementsNew[i] = new String[] {requirementName};
            ++i;
        }
        taskAccessor.setRequirements(requirementsNew);
    }
}
