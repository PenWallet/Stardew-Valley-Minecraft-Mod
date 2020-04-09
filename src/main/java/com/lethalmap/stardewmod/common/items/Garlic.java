package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.block.Block;
import net.minecraft.item.*;

public class Garlic extends BlockNamedItem {
    public Garlic() {
        super(BlockList.garlic, new Item.Properties().group(Constants.SMITEMGROUP).food(Foods.GARLIC));
        setRegistryName(Constants.GARLIC);
    }
}
