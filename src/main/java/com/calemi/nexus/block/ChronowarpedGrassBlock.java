package com.calemi.nexus.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.MapColor;

import java.util.List;
import java.util.Optional;

public class ChronowarpedGrassBlock extends Block implements BonemealableBlock {

    public ChronowarpedGrassBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).mapColor(MapColor.WARPED_WART_BLOCK));
    }

    private static boolean canBeGrass(BlockState state, LevelReader levelReader, BlockPos pos) {

        BlockPos abovePos = pos.above();
        BlockState aboveState = levelReader.getBlockState(abovePos);

        if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        }

        else if (aboveState.getFluidState().getAmount() == 8) {
            return false;
        }

        else {
            int i = LightEngine.getLightBlockInto(levelReader, state, pos, aboveState, abovePos, Direction.UP, aboveState.getLightBlock(levelReader, abovePos));
            return i < levelReader.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(abovePos).is(FluidTags.WATER);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1)) return;
            level.setBlockAndUpdate(pos, NexusBlocks.CHRONOWARPED_DIRT.get().defaultBlockState());
            return;
        }

        if (!level.isAreaLoaded(pos, 3)) return;

        if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {

            BlockState defaultState = defaultBlockState();

            for (int i = 0; i < 4; i++) {

                BlockPos neighborPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

                if (level.getBlockState(neighborPos).is(NexusBlocks.CHRONOWARPED_DIRT) && canPropagate(defaultState, level, neighborPos)) {

                    level.setBlockAndUpdate(neighborPos, defaultState);
                }
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos pos, BlockState state) {
        return levelReader.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

        BlockPos abovePos = pos.above();
        BlockState shortGrassState = Blocks.SHORT_GRASS.defaultBlockState();

        Optional<Holder.Reference<PlacedFeature>> optionalFeature = level.registryAccess()
                .registryOrThrow(Registries.PLACED_FEATURE)
                .getHolder(VegetationPlacements.GRASS_BONEMEAL);

        baseLoop:
        for (int i = 0; i < 128; i++) {

            BlockPos neighborPos = abovePos;

            for (int j = 0; j < i / 16; j++) {
                neighborPos = neighborPos.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(neighborPos.below()).is(this) || level.getBlockState(neighborPos).isCollisionShapeFullBlock(level, neighborPos)) {
                    continue baseLoop;
                }
            }

            BlockState neighborState = level.getBlockState(neighborPos);

            if (neighborState.is(shortGrassState.getBlock()) && random.nextInt(10) == 0) {
                ((BonemealableBlock)shortGrassState.getBlock()).performBonemeal(level, random, neighborPos, neighborState);
            }

            if (neighborState.isAir()) {

                Holder<PlacedFeature> holder;

                if (random.nextInt(8) == 0) {

                    List<ConfiguredFeature<?, ?>> list = level.getBiome(neighborPos).value().getGenerationSettings().getFlowerFeatures();

                    if (list.isEmpty()) continue;

                    holder = ((RandomPatchConfiguration)list.getFirst().config()).feature();
                }

                else {
                    if (optionalFeature.isEmpty()) continue;
                    holder = optionalFeature.get();
                }

                holder.value().place(level, level.getChunkSource().getGenerator(), random, neighborPos);
            }
        }
    }

    @Override
    public BonemealableBlock.Type getType() {
        return BonemealableBlock.Type.NEIGHBOR_SPREADER;
    }
}
