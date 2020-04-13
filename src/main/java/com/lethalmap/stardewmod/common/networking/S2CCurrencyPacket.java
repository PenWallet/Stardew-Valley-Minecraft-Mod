package com.lethalmap.stardewmod.common.networking;

import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.common.capabilities.currency.CurrencyCapability;
import com.lethalmap.stardewmod.common.capabilities.currency.ICurrency;
import com.lethalmap.stardewmod.common.gui.CustomInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CCurrencyPacket {
    private int amount;

    public S2CCurrencyPacket(int amount) {
        this.amount = amount;
    }

    //This code only runs on the client
    public static void handle(final S2CCurrencyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity sender = ctx.get().getSender(); // the client that sent this packet
            //Set the amount in the screen
            Minecraft.getInstance().player.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new).setAmount(msg.amount);
        });
        ctx.get().setPacketHandled(true);
    }

    public static void encode(final S2CCurrencyPacket msg, final PacketBuffer packetBuffer) {
        packetBuffer.writeInt(msg.amount);
    }

    public static S2CCurrencyPacket decode(final PacketBuffer packetBuffer) {
        return new S2CCurrencyPacket(packetBuffer.readInt());
    }
}
