package com.lethalmap.stardewmod.common.items.gems;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class Amethyst extends Item implements IStardewItem
{
    public Amethyst() {
        super(new Properties().group(Constants.SMSGEMSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.AMETHYST);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 100);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }

    //TODO: Quitar esto
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(worldIn.isRemote)
            playerIn.sendStatusMessage(new StringTextComponent("The cost of this item is: "+playerIn.getHeldItem(handIn).getTag().getInt(Constants.SELLINGPRICE)), false);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
