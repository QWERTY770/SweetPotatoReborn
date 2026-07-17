package io.github.qwerty770.mcmod.spmreborn.mixin;

import io.github.qwerty770.mcmod.spmreborn.blocks.GrinderBlock;
import io.github.qwerty770.mcmod.spmreborn.client.KeepPlayingSoundInstance;
import io.github.qwerty770.mcmod.spmreborn.sound.SweetPotatoSoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelEventHandler.class)
@Environment(EnvType.CLIENT)
public class WorldEventMixinC {
    public WorldEventMixinC() {
    }

    /**
     * When {@link ClientLevel#levelEvent(Entity, int, BlockPos, int)} calls.
     */
    @Inject(at = @At("HEAD"), method = "levelEvent", cancellable = true)
    private void sprSounds(int eventType, BlockPos pos, int data, CallbackInfo ci) {
        if (eventType == 1132119 && data == 805) {
            Minecraft minecraft = Minecraft.getInstance();
            ClientLevel level = minecraft.level;
            if (level == null || minecraft.player == null) {
                return;
            }
            minecraft.getSoundManager().play(new KeepPlayingSoundInstance(SweetPotatoSoundEvents.GRINDER_GRIND.get(),
                    1.0F, level, pos, minecraft.player, (world1, blockPos1) -> {
                BlockState state = world1.getBlockState(blockPos1);
                return state.getBlock() instanceof GrinderBlock // important
                        && state.getValue(GrinderBlock.GRINDING);
            }));
            ci.cancel();
        }
    }
}
