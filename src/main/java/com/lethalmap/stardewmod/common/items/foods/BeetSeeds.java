package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class BeetSeeds extends BlockNamedItem {
    public BeetSeeds() {
        super(BlockList.beet, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.BEETSEEDS);
    }
}