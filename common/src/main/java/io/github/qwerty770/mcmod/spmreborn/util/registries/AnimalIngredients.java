package io.github.qwerty770.mcmod.spmreborn.util.registries;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.mixin.acc.ParrotEntityAccessor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

@ApiStatus.Internal
public final class AnimalIngredients {
    private AnimalIngredients() {}

    public static Stream<ItemStack> configurePig(ItemStack[] stacks) {
        ArrayList<ItemStack> stackList = new ArrayList<>(Arrays.asList(stacks));
        stackList.add(SPRMain.ENCHANTED_CARROT_ITEM.getDefaultInstance());
        stackList.add(SPRMain.ENCHANTED_VANILLA_POTATO_ITEM.getDefaultInstance());
        stackList.add(SPRMain.PEEL.getDefaultInstance());

        return stackList.stream();
    }

    public static void configureParrot() {
        Set<Item> parrotTamingIngredients = ParrotEntityAccessor.getTamingIngredients();
        parrotTamingIngredients.add(SPRMain.ENCHANTED_BEETROOT_SEEDS);
        parrotTamingIngredients.add(SPRMain.ENCHANTED_WHEAT_SEEDS);
    }

    public static Stream<ItemStack> configureChicken(ItemStack[] stacks) {
        ArrayList<ItemStack> stackList = new ArrayList<>(Arrays.asList(stacks));
        stackList.add(SPRMain.ENCHANTED_WHEAT_SEEDS.getDefaultInstance());
        stackList.add(SPRMain.ENCHANTED_BEETROOT_SEEDS.getDefaultInstance());

        return stackList.stream();
    }
}
