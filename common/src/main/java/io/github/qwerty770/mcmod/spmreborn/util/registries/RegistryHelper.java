package io.github.qwerty770.mcmod.spmreborn.util.registries;

import com.mojang.datafixers.types.Type;
import dev.architectury.registry.menu.MenuRegistry;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.util.tag.TagContainer;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.Container;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public interface RegistryHelper {
    // net.minecraft.core.Registry -> net.minecraft.core.registries.BuiltInRegistries.XXX
    // Update to Minecraft 1.20 -- 2023/10/30
    static ResourceLocation id(String id) {
        return new ResourceLocation(SPRMain.MODID, id);
    }

    static Item item(String id, Item item2) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.ITEM, id2, item2);
    }

    static Item defaultItem(String id, @NotNull Item.Properties settings) {
        return item(id, new Item(settings));
    }

    static Block block(String id, Block block2) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.BLOCK, id2, block2);
    }

    static BlockItem blockItem(String id, Block block2, @NotNull Item.Properties settings) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.ITEM, id2, new BlockItem(block2, settings));
    }

    // Update to Minecraft 1.20 -- 2023/12/16
    static <E extends BlockEntity> BlockEntityType<E> blockEntity(String id, BlockEntityType.BlockEntitySupplier<E> supplier, Block... blocks) {
        ResourceLocation id2 = id(id);
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, id);
        assert type != null;
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id2, BlockEntityType.Builder.of(supplier, blocks).build(type));
    }

    //@Environment(EnvType.CLIENT)
    static SoundEvent sound(String id) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id2, SoundEvent.createVariableRangeEvent(id2));
    }

    static <T extends Recipe<Container>> RecipeType<T> recipeType(String id) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, id2, new RecipeType<T>() {
            @Override
            public String toString() {
                return id2.toString();
            }
        });
    }

    static <S extends RecipeSerializer<?>> S recipeSerializer(String id, Supplier<S> serializerSupplier) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, id2, serializerSupplier.get());
    }

    static <H extends AbstractContainerMenu> MenuType<H> simpleMenuType(String id, MenuType.MenuSupplier<H> factory) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.MENU, id2, new MenuType<>(factory, FeatureFlags.VANILLA_SET));
    }

    // Update to Minecraft 1.20 -- 2023/12/3
    static <H extends AbstractContainerMenu> MenuType<H> extendedMenuType(String id, MenuRegistry.ExtendedMenuTypeFactory<H> factory) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.MENU, id2, MenuRegistry.ofExtended(factory));
    }

    static TagContainer<Item> itemTag(String id) {
        ResourceLocation id2 = id(id);
        return TagContainer.register(id2, BuiltInRegistries.ITEM);
    }

    static ResourceLocation stat(String id, StatFormatter statFormatter) {
        ResourceLocation id2 = id(id);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, id2, id2);
        Stats.CUSTOM.get(id2, statFormatter);
        return id2;
    }

    static ResourceLocation stat(String id) { return stat(id, StatFormatter.DEFAULT); }

    static LootItemFunctionType lootFunction(String id,
                net.minecraft.world.level.storage.loot.Serializer<? extends LootItemFunction> serializer) {
        ResourceLocation id2 = id(id);
        return Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, id2, new LootItemFunctionType(serializer));
    }
}