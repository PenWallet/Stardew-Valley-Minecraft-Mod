package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.common.capabilities.currency.Currency;
import com.lethalmap.stardewmod.common.capabilities.currency.CurrencyCapability;
import com.lethalmap.stardewmod.common.capabilities.currency.ICurrency;
import com.lethalmap.stardewmod.common.networking.C2SCurrencyPacket;
import com.lethalmap.stardewmod.common.networking.S2CCurrencyPacket;
import com.mojang.datafixers.kinds.Const;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.NonNullConsumer;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;

public class CopperIngot extends Item {

    public CopperIngot() {
        super(new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERINGOT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        //TODO: Quitar
        if(!worldIn.isRemote)
        {
            ICurrency currency = playerIn.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);
            currency.addOrSubtractAmount(1);
            playerIn.sendStatusMessage(new StringTextComponent("Currency: "+currency.getAmount()), false);
        }
        else
            StardewMod.CHANNEL.sendToServer(new C2SCurrencyPacket());
        
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
