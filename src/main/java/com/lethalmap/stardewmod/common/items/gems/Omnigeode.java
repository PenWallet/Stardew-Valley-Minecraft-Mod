package com.lethalmap.stardewmod.common.items.gems;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class Omnigeode extends Item implements IStardewItem {
    public Omnigeode() {
        super(new Properties().group(Constants.SMSGEMSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.OMNIGEODE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
