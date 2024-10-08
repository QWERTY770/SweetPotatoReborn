package io.github.qwerty770.mcmod.spmreborn.util.registries;

import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Codec;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import io.github.qwerty770.mcmod.spmreborn.util.annotation.StableApi;
import io.github.qwerty770.mcmod.spmreborn.util.tag.TagContainer;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatFormatter;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@StableApi
public abstract class RegistryHelper {
    // Update to Minecraft 1.20 -- 2023/10/30  net.minecraft.core.Registry -> net.minecraft.core.registries.BuiltInRegistries.XXX
    // 2023/12/16: Use DeferredRegister for cross-platform support
    // 2023/12/16: Rewrite the whole class (mark PlatformRegister, RegistryContainer and RegistryImpl as deprecated)

    private static final List<DeferredRegister<?>> modRegistries = new ArrayList<>();
    public static final DeferredRegister<Block> blockRegistry = ofModRegistry(Registries.BLOCK);
    public static final DeferredRegister<Item> itemRegistry = ofModRegistry(Registries.ITEM);
    public static final DeferredRegister<BlockEntityType<?>> blockEntityRegistry = ofModRegistry(Registries.BLOCK_ENTITY_TYPE);
    public static final DeferredRegister<RecipeType<?>> recipeTypeRegistry = ofModRegistry(Registries.RECIPE_TYPE);
    public static final DeferredRegister<RecipeSerializer<?>> recipeSerializerRegistry = ofModRegistry(Registries.RECIPE_SERIALIZER);
    public static final DeferredRegister<MenuType<?>> menuRegistry = ofModRegistry(Registries.MENU);
    public static final DeferredRegister<SoundEvent> soundRegistry = ofModRegistry(Registries.SOUND_EVENT);
    public static final DeferredRegister<ParticleType<?>> particleTypeRegistry = ofModRegistry(Registries.PARTICLE_TYPE);
    public static final DeferredRegister<EntityType<?>> entityTypeRegistry = ofModRegistry(Registries.ENTITY_TYPE);
    public static final DeferredRegister<ResourceLocation> statRegistry = ofModRegistry(Registries.CUSTOM_STAT);
    public static final DeferredRegister<TreeDecoratorType<?>> treeDecoratorTypeRegistry = ofModRegistry(Registries.TREE_DECORATOR_TYPE);
    public static final DeferredRegister<PoiType> poiTypeRegistry = ofModRegistry(Registries.POINT_OF_INTEREST_TYPE);
    public static final DeferredRegister<LootItemFunctionType> lootFunctionRegistry = ofModRegistry(Registries.LOOT_FUNCTION_TYPE);
    public static final DeferredRegister<CreativeModeTab> creativeTabRegistry = ofModRegistry(Registries.CREATIVE_MODE_TAB);

    public static ResourceLocation id(String id) {
        return ResourceLocationTool.create(SPRMain.MODID, id);
    }

    public static RegistrySupplier<Block> block(String id, Block block2) {
        return blockRegistry.register(id, () -> block2);
    }

    public static RegistrySupplier<Block> block(String id, Supplier<Block> supplier) {
        return blockRegistry.register(id, supplier);
    }

    public static RegistrySupplier<Item> item(String id, Item item2) {
        return itemRegistry.register(id, () -> item2);
    }

    public static RegistrySupplier<Item> item(String id, Supplier<Item> supplier) {
        return itemRegistry.register(id, supplier);
    }

    public static RegistrySupplier<Item> defaultItem(String id, @NotNull Item.Properties settings) {
        return itemRegistry.register(id, () -> new Item(settings));
    }

    public static RegistrySupplier<BlockItem> blockItem(String id, Supplier<Block> block2, @NotNull Item.Properties settings) {
        return itemRegistry.register(id, () -> new BlockItem(block2.get(), settings));
    }

    // Update to Minecraft 1.20 -- 2023/12/16
    @SafeVarargs
    public static <E extends BlockEntity> RegistrySupplier<BlockEntityType<E>> blockEntity(String id, BlockEntityType.BlockEntitySupplier<E> supplier, Supplier<Block>... blocks) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, id);
        assert type != null;
        return blockEntityRegistry.register(id, () -> BlockEntityType.Builder.of(supplier,
                Arrays.stream(blocks).map(Supplier::get).toArray(Block[]::new)).build(type));
    }

    public static <T extends Recipe<Container>> RegistrySupplier<RecipeType<T>> recipeType(String id) {
        ResourceLocation id2 = id(id);
        return recipeTypeRegistry.register(id, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return id2.toString();
            }
        });
    }

    public static <S extends RecipeSerializer<?>> RegistrySupplier<S> recipeSerializer(String id, Supplier<S> serializerSupplier) {
        return recipeSerializerRegistry.register(id, serializerSupplier);
    }

    public static <H extends AbstractContainerMenu> RegistrySupplier<MenuType<H>> simpleMenuType(String id, MenuType.MenuSupplier<H> factory) {
        return menuRegistry.register(id, () -> new MenuType<>(factory, FeatureFlags.VANILLA_SET));
    }

    // Update to Minecraft 1.20 -- 2023/12/3
    public static <H extends AbstractContainerMenu> RegistrySupplier<MenuType<H>> extendedMenuType(String id, MenuRegistry.ExtendedMenuTypeFactory<H> factory) {
        return menuRegistry.register(id, () -> MenuRegistry.ofExtended(factory));
    }

    public static Supplier<SoundEvent> sound(String id) {
        return () -> SoundEvent.createVariableRangeEvent(id(id));
    }

    public <P extends ParticleType<?>> RegistrySupplier<P> particleType(String id, Supplier<P> particleTypeSup) {
        return particleTypeRegistry.register(id, particleTypeSup);
    }

    public <E extends Entity> RegistrySupplier<EntityType<E>> entityType(String id, Supplier<EntityType.Builder<E>> builder) {
        return entityTypeRegistry.register(id, () -> builder.get().build(id(id).toString()));
    }

    public static TagContainer<Item> itemTag(String id) {
        return TagContainer.register(id(id), BuiltInRegistries.ITEM);
    }

    public static ResourceLocation stat(String id, StatFormatter statFormatter) {
        ResourceLocation id2 = id(id);
        statRegistry.register(id, () -> id2);
        // TODO: Update the next line
        // Stats.CUSTOM.get(id2, statFormatter);
        return id2;
    }

    public static ResourceLocation stat(String id) { return stat(id, StatFormatter.DEFAULT); }

    public <P extends TreeDecorator> RegistrySupplier<TreeDecoratorType<P>> treeDecoratorType(String id, Supplier<Codec<P>> codecGetter) {
        return treeDecoratorTypeRegistry.register(id, () -> new TreeDecoratorType<>(codecGetter.get()));
    }

    public RegistrySupplier<PoiType> poiType(String id, int maxTickets, int validRange, Supplier<Set<BlockState>> matchingStatesSup) {
        return poiTypeRegistry.register(id, () -> new PoiType(matchingStatesSup.get(), maxTickets, validRange));
    }

    public static RegistrySupplier<LootItemFunctionType> lootFunction(String id, Serializer<? extends LootItemFunction> serializer) {
        return lootFunctionRegistry.register(id, () -> new LootItemFunctionType(serializer));
    }

    public static RegistrySupplier<CreativeModeTab> creativeModeTab(String id, CreativeModeTab tab){
        return creativeTabRegistry.register(id, () -> tab);
    }

    private static <T> DeferredRegister<T> ofModRegistry(ResourceKey<Registry<T>> resourceKey) {
        var reg = DeferredRegister.create(SPRMain.MODID, resourceKey);
        modRegistries.add(reg);
        return reg;
    }

    public static void registerAll(){
        for (var reg : modRegistries) {
            reg.register();
        }
    }
}