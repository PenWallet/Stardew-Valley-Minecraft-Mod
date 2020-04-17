package com.lethalmap.stardewmod.common.items.interactable;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import net.minecraft.item.BlockItem;

public class ShippingChest extends BlockItem implements IStardewItem {
    public ShippingChest() {
        super(BlockList.shippingchest, new Properties().group(Constants.SMITEMGROUP).maxStackSize(1));
        setRegistryName(Constants.MODID, Constants.SHIPPINGCHEST);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
