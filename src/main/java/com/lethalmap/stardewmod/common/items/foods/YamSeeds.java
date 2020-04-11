package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class YamSeeds extends BlockNamedItem {
    public YamSeeds() {
        super(BlockList.yam, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.YAMSEEDS);
    }
}