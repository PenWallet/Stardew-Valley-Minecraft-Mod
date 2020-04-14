package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import javax.annotation.Nullable;

public class CactusSeeds extends BlockNamedItem implements IStardewItem {
    public CactusSeeds() {
        super(BlockList.cactus, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.CACTUSSEEDS);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
