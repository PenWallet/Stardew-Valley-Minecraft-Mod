package com.lethalmap.stardewmod.common.networking;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.common.capabilities.currency.CurrencyCapability;
import com.lethalmap.stardewmod.common.capabilities.currency.ICurrency;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkInstance;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.IndexedMessageCodec;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.lwjgl.system.windows.MSG;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class C2SCurrencyPacket {

    public C2SCurrencyPacket() {

    }

    public static void handle(final C2SCurrencyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity sender = ctx.get().getSender(); // the client that sent this packet

            //Get the currency
            ICurrency currency = sender.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);
            //Send message back to the client to set the information
            StardewMod.CHANNEL.send(PacketDistributor.PLAYER.with(() -> sender), new S2CCurrencyPacket(currency.getAmount()));
        });
        ctx.get().setPacketHandled(true);
    }

    public static void encode(final C2SCurrencyPacket msg, final PacketBuffer packetBuffer) {

    }

    public static C2SCurrencyPacket decode(final PacketBuffer packetBuffer) {
        return new C2SCurrencyPacket();
    }
}
