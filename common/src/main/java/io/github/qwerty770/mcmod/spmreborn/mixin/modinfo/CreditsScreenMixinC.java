package io.github.qwerty770.mcmod.spmreborn.mixin.modinfo;

import io.github.qwerty770.mcmod.spmreborn.util.credits.CreditsPrinter;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WinScreen.class)
@Environment(EnvType.CLIENT)
public abstract class CreditsScreenMixinC extends Screen {
    @Shadow private int totalScrollLength;

    @Shadow private IntSet centeredLines;

    @Shadow private List<FormattedCharSequence> lines;

    private CreditsScreenMixinC() {
        super(Component.empty());
    }

    @Inject(at = @At("TAIL"), method = "init()V")
    private void printSPRCredits(CallbackInfo ci) {
        CreditsPrinter.print(minecraft, h -> this.totalScrollLength = h,
                centeredLines, lines);
    }
}
