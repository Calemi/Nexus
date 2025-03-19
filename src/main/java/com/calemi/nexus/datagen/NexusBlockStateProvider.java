package com.calemi.nexus.datagen;

import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class NexusBlockStateProvider extends BlockStateProvider {

    public NexusBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, NexusRef.ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        NexusLists.NEXUS_PORTAL_CORE_BLOCKS.forEach(this::blockTopWithItem);
        NexusLists.NEXUS_PORTAL_BLOCKS.forEach(this::nexusPortalBlock);
    }

    private void blockTopWithItem(DeferredBlock<?> deferredBlock) {

        Block block = deferredBlock.get();

        String blockName = BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath();
        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeTop(blockName, NexusRef.rl(blockPath + "_side"), NexusRef.rl(blockPath + "_top"));

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

        String blockName = BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath();
        String blockPath = "block/" + blockName;
        String parent = NexusRef.ID + ":block/nexus_portal";

        ModelFile model = models().withExistingParent(blockName, parent)
                .texture("particle", NexusRef.rl(blockPath))
                .texture("portal", NexusRef.rl(blockPath))
                .renderType("translucent");

        getVariantBuilder(deferredBlock.get())
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(NexusPortalBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel();
    }
}
