package com.calemi.nexus.datagen;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.block.RoadBlock;
import com.calemi.nexus.block.RoadSlabBlock;
import com.calemi.nexus.main.NexusRef;
import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.ModelInstance;
import com.supermartijn642.fusion.api.model.data.ConnectingModelData;
import com.supermartijn642.fusion.api.model.data.ConnectingModelDataBuilder;
import com.supermartijn642.fusion.api.predicate.DefaultConnectionPredicates;
import com.supermartijn642.fusion.api.provider.FusionModelProvider;
import com.supermartijn642.fusion.api.util.Pair;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NexusFusionModelProvider extends FusionModelProvider {

    public NexusFusionModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(NexusRef.ID, output, existingFileHelper);
    }

    @Override
    protected void generate() {
        road(NexusBlocks.ROAD.get(), "road");
        roadSlab("road_slab", "road", "road_slab", "road");
        road(NexusBlocks.JUMP_PAD.get(), "jump_pad");
    }

    private void road(Block block, String blockName) {

        ConnectingModelData modelData = ConnectingModelDataBuilder.builder()
                .parent(ResourceLocation.withDefaultNamespace("block/cube_all"))
                .texture("all", NexusRef.rl("block/" + blockName))
                .connection(
                        DefaultConnectionPredicates.and
                                (
                                        DefaultConnectionPredicates.isSameBlock(),
                                        DefaultConnectionPredicates.or
                                                (
                                                        DefaultConnectionPredicates.isSameState(),
                                                        DefaultConnectionPredicates.matchState(block, Pair.of(RoadBlock.CHARGED, true)),
                                                        DefaultConnectionPredicates.matchState(block, Pair.of(RoadBlock.CHARGED, false))
                                                )
                                )
                )
                .build();

        ModelInstance<ConnectingModelData> modelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData);

        addModel(NexusRef.rl("block/" + blockName), modelInstance);
    }

    private void roadSlab(String blockName, String bottomTexture, String sideTexture, String topTexture) {

        ConnectingModelData bottomModelData = roadSlabModel(NexusBlocks.ROAD_SLAB.get(), "slab", bottomTexture, sideTexture, topTexture, SlabType.BOTTOM);
        ConnectingModelData topModelData = roadSlabModel(NexusBlocks.ROAD_SLAB.get(), "slab_top", bottomTexture, sideTexture, topTexture, SlabType.TOP);

        ModelInstance<ConnectingModelData> bottomModelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, bottomModelData);
        ModelInstance<ConnectingModelData> topModelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, topModelData);

        addModel(NexusRef.rl("block/" + blockName), bottomModelInstance);
        addModel(NexusRef.rl("block/" + blockName + "_top"), topModelInstance);
    }

    private ConnectingModelData roadSlabModel(Block block, String parentName, String bottomTexture, String sideTexture, String topTexture, SlabType type) {
        return ConnectingModelDataBuilder.builder()
                .parent(ResourceLocation.withDefaultNamespace("block/" + parentName))
                .texture("bottom", NexusRef.rl("block/" + bottomTexture))
                .texture("side", NexusRef.rl("block/" + sideTexture))
                .texture("top", NexusRef.rl("block/" + topTexture))
                .connection(
                        DefaultConnectionPredicates.and
                                (
                                        DefaultConnectionPredicates.isSameBlock(),
                                        DefaultConnectionPredicates.matchState(block, Pair.of(RoadSlabBlock.TYPE, type))
                                )
                )
                .build();
    }
}