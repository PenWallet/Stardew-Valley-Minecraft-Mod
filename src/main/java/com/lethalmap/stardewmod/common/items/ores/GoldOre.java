package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class GoldOre extends BlockItem {
    public GoldOre() {
        super(BlockList.goldore, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.GOLDORE);
    }
}