package com.calemi.nexus.screen;

import com.calemi.ccore.api.screen.BaseScreen;
import com.calemi.ccore.api.screen.ScreenHelper;
import com.calemi.nexus.block.NexusPortalBlock;
import com.calemi.nexus.blockentity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.capability.UnlockedDimensionsList;
import com.calemi.nexus.packet.*;
import com.calemi.nexus.util.NexusHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class NexusPortalCoreScreen extends BaseScreen {

    private final NexusPortalCoreBlockEntity portalCoreBlockEntity;

    private CycleButton<ResourceLocation> dimensionSelectButton;
    private Button unlinkButton;
    private Button lightPortalButton;
    private Button teleportButton;
    private Button findLinkButton;
    private Button generateLinkButton;

    public NexusPortalCoreScreen(NexusPortalCoreBlockEntity portalCoreBlockEntity) {
        super(Component.translatable("screen.nexus.nexus_portal_core.title"));
        this.portalCoreBlockEntity = portalCoreBlockEntity;
    }

    @Override
    protected void init() {
        super.init();

        List<ResourceLocation> dimensions = new ArrayList<>(UnlockedDimensionsList.get(player).getUnlockedDimensions());

        dimensions.remove(player.level().dimension().location());

        dimensionSelectButton = addRenderableWidget(
                CycleButton.<ResourceLocation>builder((dimension) -> Component.literal(dimension.getPath().toUpperCase().replaceAll("_", " ")))
                        .withValues(dimensions)
                        .withInitialValue(portalCoreBlockEntity.getDestinationDimResourceLocation())
                        .create(getScreenX() - 100, getScreenY() - 10 - 26, 200, 20,
                                Component.translatable("screen.nexus.nexus_portal_core.button.dimension_select.title"),
                                (btn, selected) -> PacketDistributor.sendToServer(new NexusPortalCoreDestinationDimensionSyncPayload(portalCoreBlockEntity.getBlockPos(), selected)))
        );

        lightPortalButton = addRenderableWidget(
                Button.builder(Component.empty(), btn -> lightPortalButtonPress())
                        .bounds(getScreenX() - 100, getScreenY() - 10, 20, 20)
                        .build());

        teleportButton = addRenderableWidget(
                Button.builder(Component.translatable("screen.nexus.nexus_portal_core.button.teleport.title").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD), btn -> teleportButtonPress())
                        .bounds(getScreenX() - 100 + 26, getScreenY() - 10, 200 - (26 * 2), 20)
                        .build());

        unlinkButton = addRenderableWidget(
                Button.builder(Component.empty(), btn -> unlinkButtonPress())
                        .bounds(getScreenX() + 100 - 20, getScreenY() - 10, 20, 20)
                        .build());

        findLinkButton = addRenderableWidget(
                Button.builder(Component.translatable("screen.nexus.nexus_portal_core.button.find_link.title").withStyle(ChatFormatting.GOLD), btn -> findLinkButtonPress())
                        .bounds(getScreenX() - 100, getScreenY() - 10 + 26, 97, 20)
                        .build());

        generateLinkButton = addRenderableWidget(
                Button.builder(Component.translatable("screen.nexus.nexus_portal_core.button.generate_link.title").withStyle(ChatFormatting.GOLD), btn -> generateLinkPress())
                        .bounds(getScreenX() + 3, getScreenY() - 10 + 26, 97, 20)
                        .build());

        addRenderableWidget(
                Button.builder(Component.translatable("screen.nexus.nexus_portal_core.button.close.title"), btn -> onClose())
                        .bounds(getScreenX() - 100, getScreenY() - 10 + 26 + 26, 200, 20)
                        .build());

        buttonUpdate();
    }

    private void lightPortalButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreLightPortalPayload(portalCoreBlockEntity.getBlockPos().above()));
        onClose();
    }

    private void teleportButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreTeleportPayload(portalCoreBlockEntity.getBlockPos()));
        onClose();
    }

    private void unlinkButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreUnlinkPayload(portalCoreBlockEntity.getBlockPos(), NexusPortalCoreScreen.hasShiftDown()));
        onClose();
    }

    private void findLinkButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreFindLinkPayload(portalCoreBlockEntity.getBlockPos()));
        onClose();
    }

    private void generateLinkPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreGenerateLinkPayload(portalCoreBlockEntity.getBlockPos()));
        onClose();
    }

    private void buttonUpdate() {

        //NO DESTINATION POSITION
        if (portalCoreBlockEntity.getDestinationPos() == null) {
            dimensionSelectButton.active = NexusHelper.isInNexus(player);
            lightPortalButton.active = false;
            teleportButton.active = false;
            unlinkButton.active = false;
            findLinkButton.active = true;
            generateLinkButton.active = player.getInventory().countItem(portalCoreBlockEntity.getBlockState().getBlock().asItem()) >= 1 || player.isCreative();
            teleportButton.setMessage(teleportButton.getMessage().copy().withStyle(ChatFormatting.GRAY));
        }

        //HAS DESTINATION POSITION
        else {
            dimensionSelectButton.active = false;
            lightPortalButton.active = true;
            teleportButton.active = true;
            unlinkButton.active = true;
            findLinkButton.active = false;
            generateLinkButton.active = false;
            findLinkButton.setMessage(findLinkButton.getMessage().copy().withStyle(ChatFormatting.GRAY));
            generateLinkButton.setMessage(generateLinkButton.getMessage().copy().withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (player.level().getGameTime() % 10 == 0) {
            buttonUpdate();
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        BlockPos destinationPos = portalCoreBlockEntity.getDestinationPos();

        if (destinationPos == null) {

            graphics.drawCenteredString(mc.font, Component.translatable("screen.nexus.nexus_portal_core.text.no_link").withStyle(ChatFormatting.RED), getScreenX(), getScreenY() - 50, 0xFFFFFF);

            if (!NexusHelper.isInNexus(player)) {
                ScreenHelper.drawTooltipHoverRect(graphics, dimensionSelectButton.getRectangle(), mouseX, mouseY,
                        Component.translatable("screen.nexus.nexus_portal_core.button.dimension_select.cant_set")
                                .withStyle(ChatFormatting.GRAY)
                                .withStyle(ChatFormatting.ITALIC));
            }

            ScreenHelper.drawTooltipHoverRect(graphics, findLinkButton.getRectangle(), mouseX, mouseY,
                    Component.translatable("screen.nexus.nexus_portal_core.button.find_link.info"));

            ScreenHelper.drawTooltipHoverRect(graphics, generateLinkButton.getRectangle(), mouseX, mouseY,
                    Component.translatable("screen.nexus.nexus_portal_core.button.generate_link.info"),
                    Component.translatable("screen.nexus.nexus_portal_core.button.generate_link.requires")
                            .append(": ")
                            .append("1x ")
                            .append(portalCoreBlockEntity.getBlockState().getBlock().getName())
                            .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

        else {
            graphics.drawCenteredString(mc.font, Component.translatable("screen.nexus.nexus_portal_core.text.current_destination").withStyle(ChatFormatting.UNDERLINE).withStyle(ChatFormatting.LIGHT_PURPLE), getScreenX(), getScreenY() - 76, 0xFFFFFF);
            graphics.drawCenteredString(mc.font, Component.literal("[x" + ChatFormatting.GOLD + destinationPos.getX() + ChatFormatting.WHITE + ", y" + ChatFormatting.GOLD + destinationPos.getY() + ChatFormatting.WHITE + ", z" + ChatFormatting.GOLD + destinationPos.getZ() + ChatFormatting.WHITE + "]"), getScreenX(), getScreenY() - 64, 0xFFFFFF);
            graphics.drawCenteredString(mc.font, Component.literal(portalCoreBlockEntity.getDestinationDimResourceKey().location().getPath().toUpperCase().replaceAll("_", " ")).withStyle(ChatFormatting.GOLD), getScreenX(), getScreenY() - 52, 0xFFFFFF);
        }

        Item lightPortalItem = Items.FLINT_AND_STEEL;
        int xOffset = 1;
        Component lightPortalInfo = Component.translatable("screen.nexus.nexus_portal_core.button.light_portal.light_info");

        if (player.level().getBlockState(portalCoreBlockEntity.getBlockPos().above()).getBlock() instanceof NexusPortalBlock) {
            lightPortalItem = Items.WATER_BUCKET;
            xOffset = 2;
            lightPortalInfo = Component.translatable("screen.nexus.nexus_portal_core.button.light_portal.destroy_info");
        }

        graphics.renderItem(new ItemStack(lightPortalItem), lightPortalButton.getX() + xOffset, lightPortalButton.getY() + 2);
        graphics.renderItem(new ItemStack(Items.BARRIER), unlinkButton.getX() + 2, unlinkButton.getY() + 2);

        if (destinationPos != null) {
            ScreenHelper.drawTooltipHoverRect(graphics, lightPortalButton.getRectangle(), mouseX, mouseY, lightPortalInfo);
            ScreenHelper.drawTooltipHoverRect(graphics, unlinkButton.getRectangle(), mouseX, mouseY,
                    Component.translatable("screen.nexus.nexus_portal_core.button.unlink.info_1"),
                    Component.translatable("screen.nexus.nexus_portal_core.button.unlink.info_2").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC)
            );
        }
    }

    @Override
    protected boolean canCloseWithInvKey() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected int getGuiSizeX() {
        return 0;
    }

    @Override
    protected int getGuiSizeY() {
        return 0;
    }
}
