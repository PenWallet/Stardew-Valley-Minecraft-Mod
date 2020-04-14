package com.lethalmap.stardewmod.common.items.gems;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class SVEmerald extends Item implements IStardewItem {
    public SVEmerald() {
        super(new Properties().group(Constants.SMSGEMSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.SVEMERALD);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 250);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }
}
