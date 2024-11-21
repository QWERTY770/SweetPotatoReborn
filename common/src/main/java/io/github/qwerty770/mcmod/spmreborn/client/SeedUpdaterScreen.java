package io.github.qwerty770.mcmod.spmreborn.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.SeedUpdaterScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class SeedUpdaterScreen extends ItemCombinerScreen<SeedUpdaterScreenHandler> {
    private static final ResourceLocation TEXTURE = ResourceLocationTool.create("spmreborn:textures/gui/container/seed_updating.png");

    public SeedUpdaterScreen(SeedUpdaterScreenHandler handler, Inventory playerInventory, Component title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderErrorIcon(GuiGraphics guiGraphics, int i, int j) {
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
            guiGraphics.blitSprite(RenderType::guiTextured, ResourceLocationTool.withDefaultNamespace("container/anvil/error"), i + 99, j + 45, 28, 21);
        }
    }
}
