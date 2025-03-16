package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.calemi.nexus.regsitry.NexusLists;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class NexusBlockStateProvider extends BlockStateProvider {

    public NexusBlockStateProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, NexusRef.MOD_ID, fileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        NexusLists.NEXUS_PORTAL_CORE_BLOCKS.forEach(this::blockTopWithItem);
        NexusLists.NEXUS_PORTAL_BLOCKS.forEach(this::netherPortalBlock);
    }

    private void blockTopWithItem(DeferredBlock<?> deferredBlock) {

        String blockName = BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath();
        String blockPath = "block/" + blockName;

        ModelFile model = models().cubeTop(blockName, NexusRef.rl(blockPath + "_side"), NexusRef.rl(blockPath + "_top"));
        simpleBlockWithItem(deferredBlock.get(), model);
    }

    private void netherPortalBlock(DeferredBlock<?> deferredBlock) {

        String blockName = BuiltInRegistries.BLOCK.getKey(deferredBlock.get()).getPath();
        String blockPath = "block/" + blockName;

        String parentPathEastWest = "block/" + BuiltInRegistries.BLOCK.getKey(Blocks.NETHER_PORTAL).getPath() + "_ew";
        String parentPathNorthSouth = "block/" + BuiltInRegistries.BLOCK.getKey(Blocks.NETHER_PORTAL).getPath() + "_ns";

        ModelFile modelEastWest = models().withExistingParent(blockName + "_ew", parentPathEastWest)
                .texture("particle", NexusRef.rl(blockPath))
                .texture("portal", NexusRef.rl(blockPath))
                .renderType("translucent");

        ModelFile modelNorthSouth = models().withExistingParent(blockName + "_ns", parentPathNorthSouth)
                .texture("particle", NexusRef.rl(blockPath))
                .texture("portal", NexusRef.rl(blockPath))
                .renderType("translucent");

        getVariantBuilder(deferredBlock.get())
                .partialState().with(NetherPortalBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(modelEastWest).addModel()
                .partialState().with(NetherPortalBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(modelNorthSouth).addModel();
    }
}
