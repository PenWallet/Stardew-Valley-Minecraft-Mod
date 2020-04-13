package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;

public class FurnaceBlockItem extends BlockItem {
    public FurnaceBlockItem() {
        super(BlockList.blockfurnace, new Properties().group(Constants.SMITEMGROUP).maxStackSize(1));
        setRegistryName(Constants.MODID, Constants.FURNACE);
    }
}