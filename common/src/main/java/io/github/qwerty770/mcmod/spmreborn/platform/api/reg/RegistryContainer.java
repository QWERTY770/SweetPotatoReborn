package io.github.qwerty770.mcmod.spmreborn.platform.api.reg;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

@ApiStatus.Internal
public final class RegistryContainer {
    // Update to Minecraft 1.20 -- 2023/12/10
    private RegistryContainer(String modId) {
        this.modId = Objects.requireNonNull(modId);

        block = ofModRegistry(Registries.BLOCK);
        item = ofModRegistry(Registries.ITEM);
        blockEntity = ofModRegistry(Registries.BLOCK_ENTITY_TYPE);
        recipeSerializer = ofModRegistry(Registries.RECIPE_SERIALIZER);
        menu = ofModRegistry(Registries.MENU);
        sound = ofModRegistry(Registries.SOUND_EVENT);
        particleType = ofModRegistry(Registries.PARTICLE_TYPE);
        entityType = ofModRegistry(Registries.ENTITY_TYPE);
        stat = ofModRegistry(Registries.CUSTOM_STAT);
        recipeType = ofModRegistry(Registries.RECIPE_TYPE);
        treeDecoratorType = ofModRegistry(Registries.TREE_DECORATOR_TYPE);
        poiType = ofModRegistry(Registries.POINT_OF_INTEREST_TYPE);
    }

    public final String modId;

    private final List<DeferredRegister<?>> modBusRegistries = new ArrayList<>();

    public final DeferredRegister<Block> block;
    public final DeferredRegister<Item> item;
    public final DeferredRegister<BlockEntityType<?>> blockEntity;
    public final DeferredRegister<RecipeSerializer<?>> recipeSerializer;
    public final DeferredRegister<MenuType<?>> menu;
    public final DeferredRegister<SoundEvent> sound;
    public final DeferredRegister<ParticleType<?>> particleType;
    public final DeferredRegister<EntityType<?>> entityType;

    public final DeferredRegister<ResourceLocation> stat;
    public final DeferredRegister<RecipeType<?>> recipeType;
    public final DeferredRegister<TreeDecoratorType<?>> treeDecoratorType;
    public final DeferredRegister<PoiType> poiType;

    public void subscribeModBus() {
        for (var reg : modBusRegistries) {
            reg.register();
        }
    }

    private <T> DeferredRegister<T> ofModRegistry(ResourceKey<Registry<T>> resourceKey) {
        var reg = DeferredRegister.create(modId, resourceKey);
        modBusRegistries.add(reg);
        return reg;
    }

    public static RegistryContainer of(String modId) {
        return MAP.computeIfAbsent(modId, RegistryContainer::new);
    }

    public static final Map<String, RegistryContainer> MAP = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RegistryContainer that)) return false;
        return Objects.equals(modId, that.modId);
    }

    @Override
    public int hashCode() {
        return (modId.hashCode() << 1) + 85;
    }
}
