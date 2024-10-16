package io.github.qwerty770.mcmod.spmreborn.blocks.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class EnchantedSaplings extends SaplingBlock {

    public EnchantedSaplings(TreeGrower generator, Properties settings) {
        super(generator, settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(STAGE, 0));
    }

    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(3) == 0) {
            this.advanceTree(world, pos, state, random);
        }
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.8D;
    }
}
