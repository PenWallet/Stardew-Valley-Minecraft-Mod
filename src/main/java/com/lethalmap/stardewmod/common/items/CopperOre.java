package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CopperOre extends BlockItem {
    public CopperOre() {
        super(BlockList.copperore, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERORE);
    }
}
