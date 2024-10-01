package io.github.qwerty770.mcmod.spmreborn.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.GrinderScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.util.registries.ResourceLocationTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class GrinderScreen extends AbstractContainerScreen<GrinderScreenHandler> {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocationTool.create(SPRMain.MODID, "textures/gui/container/grinder.png");

    public GrinderScreen(GrinderScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.renderLabels(guiGraphics, mouseX, mouseY);
        double ingredientData = this.menu.getIngredientData();
        guiGraphics.drawString(this.font, Component.translatable(
                        "container.grinding.ingredientData",
                        ingredientData),
                8, 59, 0);
    }

    // Update to Minecraft 1.20 -- 2023/06/29
    @Override
    public void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(BACKGROUND_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int l = this.menu.getGrindProgress();
        guiGraphics.blit(BACKGROUND_TEXTURE, i + 74, j + 35, 176, 0, l + 1, 16);  // arrow

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}