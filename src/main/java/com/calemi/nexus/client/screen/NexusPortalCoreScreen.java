package com.calemi.nexus.client.screen;

import com.calemi.ccore.api.client.screen.BaseScreen;
import com.calemi.ccore.api.client.screen.ScreenHelper;
import com.calemi.nexus.block.entity.NexusPortalCoreBlockEntity;
import com.calemi.nexus.capability.UnlockedDimensionsList;
import com.calemi.nexus.config.NexusConfig;
import com.calemi.nexus.packet.*;
import com.calemi.nexus.world.dimension.NexusDimensionHelper;
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

    private final NexusPortalCoreBlockEntity originBlockEntity;

    private CycleButton<ResourceLocation> dimensionSelectButton;
    private Button unlinkButton;
    private Button lightPortalButton;
    private Button teleportButton;
    private Button findLinkButton;
    private Button generateLinkButton;

    private String destinationName;

    public NexusPortalCoreScreen(NexusPortalCoreBlockEntity portalCoreBlockEntity) {
        super(Component.translatable("screen.nexus.nexus_portal_core.title"));
        this.originBlockEntity = portalCoreBlockEntity;
        this.destinationName = "";
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    @Override
    protected void init() {
        super.init();

        PacketDistributor.sendToServer(new NexusPortalCoreDestinationNameSyncPayload(originBlockEntity.getBlockPos(), ""));

        List<ResourceLocation> dimensions = new ArrayList<>(UnlockedDimensionsList.get(player).getUnlockedDimensions());

        dimensions.removeIf((dimension) -> !NexusConfig.isDestinationDimensionAllowed(dimension));
        dimensions.remove(player.level().dimension().location());

        dimensionSelectButton = addRenderableWidget(
                CycleButton.<ResourceLocation>builder((dimension) -> Component.literal(dimension.getPath().toUpperCase().replaceAll("_", " ")))
                        .withValues(dimensions)
                        .withInitialValue(originBlockEntity.getDestinationDimensionRL())
                        .create(getScreenX() - 100, getScreenY() - 10 - 26, 200, 20,
                                Component.translatable("screen.nexus.nexus_portal_core.button.dimension_select.title"),
                                (btn, selected) -> PacketDistributor.sendToServer(new NexusPortalCoreDestinationDimensionSyncPayload(originBlockEntity.getBlockPos(), selected)))
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
        PacketDistributor.sendToServer(new NexusPortalCoreLightPortalPayload(originBlockEntity.getBlockPos()));
        onClose();
    }

    private void teleportButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreTeleportPayload(originBlockEntity.getBlockPos()));
        onClose();
    }

    private void unlinkButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreUnlinkPayload(originBlockEntity.getBlockPos(), NexusPortalCoreScreen.hasShiftDown()));
        onClose();
    }

    private void findLinkButtonPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreFindLinkPayload(originBlockEntity.getBlockPos()));
        onClose();
    }

    private void generateLinkPress() {
        PacketDistributor.sendToServer(new NexusPortalCoreGenerateLinkPayload(originBlockEntity.getBlockPos()));
        onClose();
    }

    private void buttonUpdate() {

        //NO DESTINATION POSITION
        if (originBlockEntity.getDestinationPos() == null) {
            dimensionSelectButton.active = NexusDimensionHelper.isInNexus(player);
            lightPortalButton.active = false;
            teleportButton.active = false;
            unlinkButton.active = false;
            findLinkButton.active = true;
            generateLinkButton.active = player.getInventory().countItem(originBlockEntity.getBlockState().getBlock().asItem()) >= 1 || player.isCreative();
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

        ResourceLocation destinationDimResourceLocation = originBlockEntity.getDestinationDimensionRL();
        BlockPos destinationPos = originBlockEntity.getDestinationPos();

        if (destinationPos == null) {

            graphics.drawCenteredString(mc.font, Component.translatable("screen.nexus.nexus_portal_core.text.no_destination").withStyle(ChatFormatting.RED), getScreenX(), getScreenY() - 50, 0xFFFFFF);

            if (!NexusDimensionHelper.isInNexus(player)) {
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
                            .append(originBlockEntity.getBlockState().getBlock().getName())
                            .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
        }

        else if (destinationDimResourceLocation != null) {
            graphics.drawCenteredString(mc.font, NexusPortalCoreBlockEntity.getFormattedCurrentDestinationText().withStyle(ChatFormatting.UNDERLINE), getScreenX(), getScreenY() - 76, 0xFFFFFF);
            graphics.drawCenteredString(mc.font, NexusPortalCoreBlockEntity.getFormattedDestinationNameText(destinationName, destinationDimResourceLocation.getPath(), ChatFormatting.GOLD, ChatFormatting.WHITE), getScreenX(), getScreenY() - 64, 0xFFFFFF);
            graphics.drawCenteredString(mc.font, NexusPortalCoreBlockEntity.getFormattedDestinationPositionText(destinationPos, ChatFormatting.GOLD, ChatFormatting.WHITE), getScreenX(), getScreenY() - 52, 0xFFFFFF);
        }

        Item lightPortalItem = Items.FLINT_AND_STEEL;
        int xOffset = 1;
        Component lightPortalInfo = Component.translatable("screen.nexus.nexus_portal_core.button.light_portal.light_info");

        if (originBlockEntity.isPortalActive()) {
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
