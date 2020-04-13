package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class Furnace extends BlockItem {
    public Furnace() {
        super(BlockList.blockfurnace, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.FURNACE);
    }
}
