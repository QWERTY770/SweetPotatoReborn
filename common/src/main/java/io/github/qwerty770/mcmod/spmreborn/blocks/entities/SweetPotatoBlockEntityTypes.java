package io.github.qwerty770.mcmod.spmreborn.blocks.entities;

import dev.architectury.registry.registries.RegistrySupplier;
import io.github.qwerty770.mcmod.spmreborn.blocks.SweetPotatoBlocks;
import io.github.qwerty770.mcmod.spmreborn.api.InternalRegistryLogWrapper;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static io.github.qwerty770.mcmod.spmreborn.util.registries.RegistryHelper.blockEntity;

public class SweetPotatoBlockEntityTypes {
    public static final InternalRegistryLogWrapper LOG_WRAPPER = InternalRegistryLogWrapper.of("block_entities");
    // Block Entities
    public static final RegistrySupplier<BlockEntityType<GrinderBlockEntity>> GRINDER_BLOCK_ENTITY_TYPE;
    public static final RegistrySupplier<BlockEntityType<MagicCubeBlockEntity>> MAGIC_CUBE_BLOCK_ENTITY_TYPE;
    static {
        GRINDER_BLOCK_ENTITY_TYPE = blockEntity("grinder", GrinderBlockEntity::new, SweetPotatoBlocks.GRINDER);
        MAGIC_CUBE_BLOCK_ENTITY_TYPE = blockEntity("magic_cube", MagicCubeBlockEntity::new, SweetPotatoBlocks.MAGIC_CUBE);
    }
}
