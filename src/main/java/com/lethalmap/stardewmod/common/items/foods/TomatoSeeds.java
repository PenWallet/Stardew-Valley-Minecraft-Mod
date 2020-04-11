package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class TomatoSeeds extends BlockNamedItem {
    public TomatoSeeds() {
        super(BlockList.tomato, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.TOMATOSEEDS);
    }
}