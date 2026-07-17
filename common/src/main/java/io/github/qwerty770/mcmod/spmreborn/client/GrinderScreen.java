package io.github.qwerty770.mcmod.spmreborn.client;

import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.GrinderScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class GrinderScreen extends AbstractContainerScreen<GrinderScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = ResourceLocationTool.create(SPRMain.MODID, "textures/gui/container/grinder.png");

    public GrinderScreen(GrinderScreenHandler handler, Inventory inventory, Component title) {
        // Hardcoded in Minecraft 1.21.3+, correcting the translation string
        super(handler, inventory, title.getString().equals("item.spmreborn.grinder") ? Component.translatable("block.spmreborn.grinder") : title);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
        super.extractLabels(guiGraphics, mouseX, mouseY);
        double ingredientData = this.menu.getIngredientData();
        guiGraphics.text(this.font, Component.translatable(
                        "container.grinding.ingredientData",
                        ingredientData), 8, 59, 0xff404040, false);
    }

    // Update to Minecraft 1.21.3 -- 2024/11/22
    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
        int i = this.leftPos;
        int j = this.topPos;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
        int l = this.menu.getGrindProgress();
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, i + 74, j + 35, 176, 0, l + 1, 16, 256, 256);  // arrow
    }
}
