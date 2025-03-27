package com.calemi.nexus.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class NexusPortalCoreBakedModel extends BakedModelWrapper<BakedModel> {

    public static final ModelProperty<BlockState> CAMO_STATE = new ModelProperty<>();

    public NexusPortalCoreBakedModel(BakedModel originalModel) {
        super(originalModel);
    }

    @Nonnull
    @Override
    public TextureAtlasSprite getParticleIcon(@Nonnull ModelData data) {
        BlockState state = data.get(CAMO_STATE);
        if (state != null) {
            return Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getParticleIcon(data);
        }

        return super.getParticleIcon(data);
    }

    @NotNull
    @Override
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {

        BlockState heldState = data.get(CAMO_STATE);
        ChunkRenderTypeSet types;

        if (heldState != null) types = Minecraft.getInstance().getBlockRenderer().getBlockModel(heldState).getRenderTypes(heldState, rand, data);
        else types = super.getRenderTypes(state, rand, data);

        return ChunkRenderTypeSet.union(types, ChunkRenderTypeSet.of(RenderType.cutoutMipped()));
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(BlockState state, @Nullable Direction side, @Nonnull RandomSource rand, @Nonnull ModelData extraData, RenderType renderType) {

        List<BakedQuad> result = new ArrayList<>();
        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        BlockState heldState = extraData.get(CAMO_STATE);

        if (heldState != null) {
            var types = Minecraft.getInstance()
                    .getBlockRenderer()
                    .getBlockModel(heldState)
                    .getRenderTypes(heldState, rand, ModelData.EMPTY);

            if (renderType == null || types.contains(renderType)) {
                BakedModel model = dispatcher.getBlockModel(heldState);
                result.addAll(model.getQuads(heldState, side, rand, extraData, renderType));
            }

            return result;
        }

        // Fallback / original model
        result.addAll(originalModel.getQuads(state, side, rand, extraData, renderType));
        return result;
    }
}