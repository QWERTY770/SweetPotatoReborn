package io.github.qwerty770.mcmod.spmreborn.platform.api.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Play packets with no arguments. Should have a constructor
 * with ({@code Consumer<}{@link NetworkManager.PacketContext}{@code >}).
 * <br>
 * This sealed interface may be implemented by ASM generators only.
 */
public interface NoArgPlayPacket extends PlayPacket {
    @Override
    default void toPacket(FriendlyByteBuf buf) {}
}

interface NoArgPlayPacketWrapper extends NoArgPlayPacket {}
interface AlternativePlayPacketWrapper extends NoArgPlayPacket {}
