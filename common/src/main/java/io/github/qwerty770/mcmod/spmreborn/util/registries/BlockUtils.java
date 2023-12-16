package io.github.qwerty770.mcmod.spmreborn.util.registries;

import io.github.qwerty770.mcmod.spmreborn.blocks.plants.EnchantedSaplings;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

// Added on 2023/11/26 after deleting io.github.qwerty770.mcmod.spmreborn.util.objsettings.BlockSettings
public class BlockUtils {
    public static final BlockBehaviour.Properties crop = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY);
    public static final BlockBehaviour.Properties grass = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY);

    public static BlockBehaviour.Properties createFunctionalBlock(float hardness, float blastResistance) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).destroyTime(hardness).explosionResistance(blastResistance).requiresCorrectToolForDrops();
    }
    public static EnchantedSaplings createEnchantedSapling(String id, Supplier<AbstractTreeGrower> saplingGeneratorSupplier) {
        return (EnchantedSaplings) RegistryHelper.block(id, new EnchantedSaplings(saplingGeneratorSupplier.get(),
                BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    }

    public static FlowerPotBlock createPotted(String id, Block inside) {
        return (FlowerPotBlock) RegistryHelper.block(id, new FlowerPotBlock(inside, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    }

    public static LeavesBlock createLeaves(String id) {
        return (LeavesBlock) RegistryHelper.block(id,
                new LeavesBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.PLANT)
                        .strength(0.2f)
                        .randomTicks()
                        .sound(SoundType.GRASS)
                        .noOcclusion()
                        .isValidSpawn(BlockUtils::ocelotOrParrot)
                        .isSuffocating(BlockUtils::never)
                        .isViewBlocking(BlockUtils::never)
                        .ignitedByLava()
                        .pushReaction(PushReaction.DESTROY)
                        .isRedstoneConductor(BlockUtils::never)));
    }

    // private methods from net.minecraft.world.level.block.Blocks
    private static Boolean ocelotOrParrot(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return entityType == EntityType.OCELOT || entityType == EntityType.PARROT;
    }
    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }
}
