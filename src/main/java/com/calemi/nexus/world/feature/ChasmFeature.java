package com.calemi.nexus.world.feature;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.world.feature.configured.ChasmConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.Vec3;

public class ChasmFeature extends Feature<ChasmConfiguration> {

    public ChasmFeature(Codec<ChasmConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ChasmConfiguration> context) {

        int radiusXZ = context.random().nextIntBetweenInclusive(context.config().radiusMinXZ(), context.config().radiusMaxXZ());
        int radiusY = context.random().nextIntBetweenInclusive(context.config().radiusMinY(), context.config().radiusMaxY());

        int centerX = context.origin().getX();
        int centerZ = context.origin().getZ();

        for (int x = -radiusXZ; x <= radiusXZ; x++) {

            for (int z = -radiusXZ; z <= radiusXZ; z++) {

                for (int y = 10; y >= -radiusY; y--) {

                    Vec3 localPos = new Vec3(x - context.origin().getX(), y - context.origin().getY(), z - context.origin().getZ());

                    BlockPos pos = context.origin().offset(x, y, z);

                    if (checkRadius(x, y, z, radiusXZ, radiusY)) {

                        if (shouldReplaceWithStone(context.level(), pos)) {

                            if (context.random().nextDouble() <= 0.25D) {
                                context.level().setBlock(pos, Blocks.TUFF.defaultBlockState(), 3);
                            }
                        }
                    }

                    if (checkRadius(x, y, z, radiusXZ - 3, radiusY)) {

                        if (shouldReplaceWithStone(context.level(), pos)) {

                            Block wallBlock = Blocks.TUFF;

                            if (context.random().nextDouble() <= 0.25D) {
                                wallBlock = NexusBlocks.WARPSLATE.get();
                            }

                            else if (context.random().nextDouble() <= 0.25D) {
                                wallBlock = Blocks.CALCITE;
                            }

                            else if (context.random().nextDouble() <= 0.05D) {
                                wallBlock = NexusBlocks.WARPSLATE_ACCELERITE_ORE.get();
                            }

                            context.level().setBlock(pos, wallBlock.defaultBlockState(), 3);
                        }

                        if (checkRadius(x, y, z, radiusXZ - 4, radiusY)) {

                            Block airBlock = Blocks.AIR;

                            if (shouldReplaceWithStone(context.level(), pos)) {

                                if (context.random().nextDouble() <= 0.25D) {
                                    airBlock = Blocks.TUFF;
                                }
                            }

                            context.level().setBlock(pos, airBlock.defaultBlockState(), 3);

                            if (checkRadius(x, y, z, radiusXZ - 5, radiusY)) {

                                context.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean shouldReplaceWithStone(WorldGenLevel level, BlockPos pos) {

        Block block = level.getBlockState(pos).getBlock();

        return block.equals(NexusBlocks.CHRONOWARPED_GRASS.get()) ||
                block.equals(NexusBlocks.CHRONOWARPED_DIRT.get()) ||
                block.equals(NexusBlocks.CHRONOWARPED_SAND.get()) ||
                block.equals(Blocks.CALCITE) ||
                block.equals(NexusBlocks.WARPSLATE.get()) ||
                block.equals(Blocks.WATER);
    }

    private boolean checkRadius(int x, int y, int z, int radiusXZ, int radiusY) {
        return (x * x) / (double) (radiusXZ * radiusXZ) + (y * y) / (double) (radiusY * radiusY) + (z * z) / (double) (radiusXZ * radiusXZ) <= 1;
    }
}
