package com.calemi.nexus.datagen;

import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.regsitry.NexusBlocks;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.calemi.nexus.main.NexusRef.ID;
import static com.calemi.nexus.main.NexusRef.rl;

public class NexusBlockStateProvider extends BlockStateProvider {

    public NexusBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        NexusLists.NEXUS_PORTAL_CORE_BLOCKS.forEach(this::nexusPortalCoreBlock);
        NexusLists.NEXUS_PORTAL_BLOCKS.forEach(this::nexusPortalBlock);

        grassBlock(NexusBlocks.CHRONOWARPED_GRASS, "chronowarped_dirt");
        allBlock(NexusBlocks.CHRONOWARPED_DIRT);
        deepslateBlock(NexusBlocks.WARPSLATE);
        allBlock(NexusBlocks.COBBLED_WARPSLATE);
    }

    private void allBlock(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();

        String blockName = BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath();
        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeAll(blockName, rl(blockPath));

        simpleBlockWithItem(block, model);
    }

    private void deepslateBlock(DeferredBlock<?> deferredBlock) {

        RotatedPillarBlock block = (RotatedPillarBlock) deferredBlock.get();
        String blockName = name(block);

        String blockSidePath = "block/" + blockName;
        String blockTopPath = "block/" + blockName + "_top";

        String parent = "minecraft:block/cube_column_mirrored";

        ModelFile model = models().cubeColumn(blockName, rl(blockSidePath), rl(blockTopPath));
        ModelFile mirroredModel = models().
                withExistingParent(blockName + "_mirrored", parent)
                .texture("end", blockTopPath)
                .texture("side", blockSidePath);

        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState()
                .modelFile(model).rotationX(90).rotationY(90).nextModel()
                .modelFile(mirroredModel).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState()
                .modelFile(model).nextModel()
                .modelFile(mirroredModel).nextModel()
                .modelFile(model).rotationY(180).nextModel()
                .modelFile(mirroredModel).rotationY(180).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState()
                .modelFile(model).rotationX(90).nextModel()
                .modelFile(mirroredModel).rotationX(90).nextModel()
                .modelFile(model).rotationX(90).rotationY(180).nextModel()
                .modelFile(mirroredModel).rotationX(90).rotationY(180).addModel();

        simpleBlockItem(block, model);
    }

    private void grassBlock(DeferredBlock<?> deferredBlock, String dirtName) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        String dirtPath = "block/" + dirtName;

        ModelFile model = models().cubeBottomTop(blockName, rl(blockPath + "_side"), rl(dirtPath), rl(blockPath + "_top"));

        simpleBlockWithItem(block, model);
    }

    private void nexusPortalCoreBlock(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeTop(blockName, rl(blockPath + "_side"), rl(blockPath + "_top"));

        getVariantBuilder(block)
                .forAllStates(state -> {

                    Direction facing = state.getValue(BlockStateProperties.FACING);
                    Direction.Axis axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);

                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationX(facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 90 : 0)
                            .rotationY(facing.getAxis().isVertical() ? (axis == Direction.Axis.Z ? 90 : 0) : (((int) facing.toYRot() + 180)) % 360)
                            .build();
                });

        simpleBlockItem(block, model);
    }

    private void nexusPortalBlock(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();
        String blockName = name(block);

        String blockPath = "block/" + blockName;
        String parent = ID + ":block/nexus_portal";

        ModelFile model = models().withExistingParent(blockName, parent)
                .texture("particle", rl(blockPath))
                .texture("portal", rl(blockPath))
                .renderType("translucent");

        getVariantBuilder(block)
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel();
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
