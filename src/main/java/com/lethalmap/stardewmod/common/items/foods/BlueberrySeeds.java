package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class BlueberrySeeds extends BlockNamedItem {
    public BlueberrySeeds() {
        super(BlockList.blueberry, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.BLUEBERRYSEEDS);
    }
}