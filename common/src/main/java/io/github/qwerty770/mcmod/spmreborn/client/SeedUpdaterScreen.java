package io.github.qwerty770.mcmod.spmreborn.client;

import io.github.qwerty770.mcmod.spmreborn.client.handlers.SeedUpdaterScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class SeedUpdaterScreen extends ItemCombinerScreen<SeedUpdaterScreenHandler> {
    private static final Identifier TEXTURE = ResourceLocationTool.create("spmreborn:textures/gui/container/seed_updating.png");

    public SeedUpdaterScreen(SeedUpdaterScreenHandler handler, Inventory playerInventory, Component title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
        super.extractLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void extractErrorIcon(GuiGraphicsExtractor guiGraphics, int i, int j) {
        if ((this.menu.getSlot(0).hasItem() || this.menu.getSlot(1).hasItem()) && !this.menu.getSlot(this.menu.getResultSlot()).hasItem()) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, ResourceLocationTool.withDefaultNamespace("container/anvil/error"), i + 99, j + 45, 28, 21);
        }
    }
}
