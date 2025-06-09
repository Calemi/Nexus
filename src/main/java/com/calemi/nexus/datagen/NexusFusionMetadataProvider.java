package com.calemi.nexus.datagen;

import com.calemi.nexus.main.NexusRef;
import com.supermartijn642.fusion.api.provider.FusionTextureMetadataProvider;
import com.supermartijn642.fusion.api.texture.DefaultTextureTypes;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureData;
import com.supermartijn642.fusion.api.texture.data.ConnectingTextureLayout;
import net.minecraft.data.PackOutput;

public class NexusFusionMetadataProvider extends FusionTextureMetadataProvider {

    public NexusFusionMetadataProvider(PackOutput output) {
        super(NexusRef.ID, output);
    }

    @Override
    protected void generate() {
        pieced("road");
        horizontal("road_slab");
    }

    private void pieced(String blockName) {
        ConnectingTextureData textureData = ConnectingTextureData.builder()
                .layout(ConnectingTextureLayout.PIECED)
                .build();

        addTextureMetadata(
                NexusRef.rl("block/" + blockName),
                DefaultTextureTypes.CONNECTING,
                textureData
        );
    }

    private void horizontal(String blockName) {
        ConnectingTextureData textureData = ConnectingTextureData.builder()
                .layout(ConnectingTextureLayout.HORIZONTAL)
                .build();

        addTextureMetadata(
                NexusRef.rl("block/" + blockName),
                DefaultTextureTypes.CONNECTING,
                textureData
        );
    }
}