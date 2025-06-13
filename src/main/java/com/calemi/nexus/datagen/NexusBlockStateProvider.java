package com.calemi.nexus.datagen;

import com.calemi.ccore.api.datagen.CBlockStateProvider;
import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.block.family.NexusBlockFamilies;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.util.NexusLists;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NexusBlockStateProvider extends CBlockStateProvider {

    public NexusBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(NexusRef.ID, output, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        NexusBlockFamilies.ALL.forEach(family -> family.getMembers().forEach((type, member) -> family(family, type, member)));

        NexusLists.NEXUS_PORTAL_CORE_BLOCKS.forEach(this::nexusPortalCore);
        NexusLists.NEXUS_PORTAL_BLOCKS.forEach(this::nexusPortal);

        roadBlock(NexusBlocks.ROAD.get());
        roadBlockSlab(NexusBlocks.ROAD_SLAB.get(), NexusBlocks.ROAD.get());
        roadBlock(NexusBlocks.JUMP_PAD.get());

        grass(NexusBlocks.CHRONOWARPED_GRASS.get(), NexusBlocks.CHRONOWARPED_DIRT.get());
        all(NexusBlocks.CHRONOWARPED_DIRT.get());
        all(NexusBlocks.CHRONOWARPED_SAND.get());

        all(NexusBlocks.CHRONO_BLOCK.get());
        all(NexusBlocks.BUDDING_CHRONO.get());
        crystal(NexusBlocks.SMALL_CHRONO_BUD.get());
        crystal(NexusBlocks.MEDIUM_CHRONO_BUD.get());
        crystal(NexusBlocks.LARGE_CHRONO_BUD.get());
        crystal(NexusBlocks.CHRONO_CLUSTER.get());

        deepslate((RotatedPillarBlock) NexusBlocks.WARPSLATE.get());

        all(NexusBlocks.WARPSLATE_ACCELERITE_ORE.get());
        all(NexusBlocks.RAW_ACCELERITE_BLOCK.get());
        all(NexusBlocks.DORMANT_ACCELERITE_BLOCK.get());
        all(NexusBlocks.CHARGED_ACCELERITE_BLOCK.get());

        allCutout(NexusBlocks.WARPBLOSSOM_LEAVES.get());

        sapling(NexusBlocks.WARPBLOSSOM_SAPLING.get());
        flowerPot(NexusBlocks.POTTED_WARPBLOSSOM_SAPLING.get(), NexusBlocks.WARPBLOSSOM_SAPLING.get());

        petal(NexusBlocks.PURPLE_PETALS.get());
    }

    private void roadBlock(Block block) {

        String blockName = name(block);

        String blockPath = "block/" + blockName;

        ModelFile model = new ModelFile.UncheckedModelFile(rl(blockPath));

        getVariantBuilder(block)
                .partialState().setModels(new ConfiguredModel(model));

        simpleBlockItem(block, model);
    }

    private void roadBlockSlab(Block block, Block doubleSlabBlock) {

        String blockName = name(block);
        String doubleSlabBlockName = name(doubleSlabBlock);

        String blockPath = "block/" + blockName;
        String doubleSlabBlockPath = "block/" + doubleSlabBlockName;

        ModelFile bottomModel = new ModelFile.UncheckedModelFile(rl(blockPath));
        ModelFile topModel = new ModelFile.UncheckedModelFile(rl(blockPath + "_top"));
        ModelFile doubleModel = new ModelFile.UncheckedModelFile(rl(doubleSlabBlockPath));

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM)
                .modelForState().modelFile(bottomModel).addModel()
                .partialState().with(BlockStateProperties.SLAB_TYPE, SlabType.TOP)
                .modelForState().modelFile(topModel).addModel()
                .partialState().with(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                .modelForState().modelFile(doubleModel).addModel();

        simpleBlockItem(block, bottomModel);
    }

    private void nexusPortalCore(Block block) {

        String blockName = name(block);

        ResourceLocation modelTextureSide = texture(block, "block/", "_side");
        ResourceLocation modelTextureTop = texture(block, "block/", "_top");

        ModelFile model = models().cubeTop(blockName, modelTextureSide, modelTextureTop);

        getVariantBuilder(block).forAllStates(state -> {

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

    private void nexusPortal(Block block) {

        String blockName = name(block);

        String modelParent = NexusRef.ID + ":block/nexus_portal";
        ResourceLocation modelTexture = texture(block, "block/");

        ModelFile model = models().withExistingParent(blockName, modelParent)
                .texture("particle", modelTexture)
                .texture("portal", modelTexture)
                .renderType("translucent");

        getVariantBuilder(block)
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel();
    }
}
