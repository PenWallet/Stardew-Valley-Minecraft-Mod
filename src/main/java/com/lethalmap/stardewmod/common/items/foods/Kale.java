package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class Kale extends Item implements IStardewItem {
    public Kale() {
        super(new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.KALE));
        setRegistryName(Constants.KALE);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 110);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }
}
