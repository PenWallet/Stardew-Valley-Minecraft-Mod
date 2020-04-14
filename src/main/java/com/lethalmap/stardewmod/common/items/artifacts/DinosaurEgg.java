package com.lethalmap.stardewmod.common.items.artifacts;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import javax.annotation.Nullable;

public class DinosaurEgg extends Item implements IStardewItem {
    public DinosaurEgg() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.DINOSAUREGG);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 350);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }
}
