package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import javax.annotation.Nullable;

public class BlueJazzSeeds extends BlockNamedItem implements IStardewItem {
    public BlueJazzSeeds() {
        super(BlockList.bluejazz, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.BLUEJAZZSEEDS);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 15);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }
}
