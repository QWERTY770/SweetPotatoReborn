package io.github.qwerty770.mcmod.spmreborn.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.qwerty770.mcmod.spmreborn.SPRMain;
import io.github.qwerty770.mcmod.spmreborn.client.handlers.MagicCubeScreenHandler;
import io.github.qwerty770.mcmod.spmreborn.api.ResourceLocationTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class MagicCubeScreen extends AbstractContainerScreen<MagicCubeScreenHandler> {
    private static final ResourceLocation BACKGROUND = ResourceLocationTool.create(SPRMain.MODID, "textures/gui/container/magic_cube.png");

    public MagicCubeScreen(MagicCubeScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
        this.imageWidth = 176;
        this.imageHeight = 181;    // overwrite
        this.inventoryLabelY = imageHeight - 94;
    }

    // Update to Minecraft 1.21.3 -- 2024/11/22
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(CoreShaders.POSITION_TEX);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // GuiGraphics.blit added 2 paramaters
        guiGraphics.blit(RenderType::guiTextured, BACKGROUND, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        short m = this.menu.getMainFuelTime(), v = this.menu.getViceFuelTime();
        int md = mainFuelDisplayHeight(m), vd = viceFuelDisplayHeight(v);
        if (m >= 0) guiGraphics.blit(RenderType::guiTextured, BACKGROUND, x + 55,  y + 59, 176, 29, 18, 4, 256, 256);
        if (v >  0) guiGraphics.blit(RenderType::guiTextured, BACKGROUND, x + 101, y + 59, 176, 29, 17, 4, 256, 256);
        guiGraphics.blit(RenderType::guiTextured, BACKGROUND, x + 57 , y + 58 - md, 199, 13 - md, 13, md, 256, 256);
        guiGraphics.blit(RenderType::guiTextured, BACKGROUND, x + 105, y + 57 - vd, 176, 28 - vd, 10, vd, 256, 256);
    }

    private static int mainFuelDisplayHeight(short mfTime) {
        // Height total: 13
        // Range: 0..199
        return mfTime >= 0 ? mfTime * 13 / 200 : 0;
    }

    private static int viceFuelDisplayHeight(short vfTime) {
        return vfTime > 0 ? vfTime * 19 / 400 : 0;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
