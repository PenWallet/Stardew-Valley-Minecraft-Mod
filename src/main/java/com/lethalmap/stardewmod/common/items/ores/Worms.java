package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class Worms extends BlockItem implements IStardewItem {
    public Worms() {
        super(BlockList.worms, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.WORMS);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
