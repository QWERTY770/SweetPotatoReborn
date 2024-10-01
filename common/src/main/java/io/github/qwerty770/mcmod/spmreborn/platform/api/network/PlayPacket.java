package io.github.qwerty770.mcmod.spmreborn.platform.api.network;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Should have constructors with {@link FriendlyByteBuf} and other essential
 * parameters.
 */
// TODO: Is it necessary?
public interface PlayPacket {
    void toPacket(FriendlyByteBuf buf);

    void handle(NetworkManager.PacketContext ctx);
}
