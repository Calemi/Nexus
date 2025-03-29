package com.calemi.nexus.client.render;

import com.calemi.nexus.block.NexusPortalCoreBlock;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Matrix4f;

public class RenderNexusPortalCoreWorldOverlay {

    @SubscribeEvent
    public void onRenderLevelStageEvent(RenderLevelStageEvent event) {

        if (!NexusConfig.client.portalCoreWorldOverlay.get()) return;

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {

            Minecraft mc = Minecraft.getInstance();
            LocalPlayer player = mc.player;

            if (player == null) return;

            if (NexusDimensionHelper.isInNexus(player)) return;

            if (!(player.getMainHandItem().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof NexusPortalCoreBlock originBlock)) return;

            BlockPos destinationPosition = NexusDimensionHelper.getDynamicBlockDestination(player.level(), player.blockPosition(), originBlock.getCoordinateScale());
            int coordinateScale = originBlock.getCoordinateScale();

            if (coordinateScale == 1) return;

            PoseStack poseStack = event.getPoseStack();
            Camera activeRenderInfo = mc.getEntityRenderDispatcher().camera;
            Vec3 projectedView = activeRenderInfo.getPosition();

            poseStack.pushPose();
            poseStack.translate(destinationPosition.getX() * coordinateScale - projectedView.x, 0, destinationPosition.getZ() * coordinateScale - projectedView.z);

            renderLine(0, 0, 1F, RenderType.debugLineStrip(2), poseStack, mc);

            renderLine(0, coordinateScale, 1F, RenderType.debugLineStrip(2), poseStack, mc);

            renderLine(coordinateScale, 0, 1F, RenderType.debugLineStrip(2), poseStack, mc);

            renderLine(coordinateScale, coordinateScale, 1F, RenderType.debugLineStrip(2), poseStack, mc);

            poseStack.popPose();
        }
    }

    private void renderLine(float x, float z, float alpha, RenderType lineRenderType, PoseStack poseStack, Minecraft mc) {

        VertexConsumer buffer = mc.renderBuffers().bufferSource().getBuffer(lineRenderType);
        Matrix4f matrix = poseStack.last().pose();

        buffer.addVertex(matrix, x, -100, z).setColor(1, 1, 1, alpha);
        buffer.addVertex(matrix, x, 100, z).setColor(1, 1, 1, alpha);

        mc.renderBuffers().bufferSource().endBatch(lineRenderType);
    }
}
