package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.common.capabilities.currency.CurrencyCapability;
import com.lethalmap.stardewmod.common.capabilities.currency.ICurrency;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.networking.C2SCurrencyPacket;
import com.lethalmap.stardewmod.common.networking.S2CCurrencyPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;

public class CopperIngot extends Item implements IStardewItem {

    public CopperIngot() {
        super(new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERINGOT);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 60);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        //TODO: Quitar
        if(!worldIn.isRemote)
        {
            ICurrency currency = playerIn.getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);
            currency.setAmount(0);
            StardewMod.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)playerIn), new S2CCurrencyPacket(currency.getAmount()));
            playerIn.sendStatusMessage(new StringTextComponent("Cleared your currency to 0, enjoy being poor :)"), false);
        }
        
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}
