package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class SpangleSeeds extends BlockNamedItem {
    public SpangleSeeds() {
        super(BlockList.spangle, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.SPANGLESEEDS);
    }
}