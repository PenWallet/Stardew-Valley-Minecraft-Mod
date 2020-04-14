package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class GoldOre extends BlockItem implements IStardewItem {
    public GoldOre() {
        super(BlockList.goldore, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.GOLDORE);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putInt(Constants.SELLINGPRICE, 25);
        stack.setTag(compoundNBT);
        return null;
    }

    @Override
    public boolean canBeSold() {
        return true;
    }
}