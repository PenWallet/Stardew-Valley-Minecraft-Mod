package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class MixedSeeds extends BlockNamedItem {
    public MixedSeeds() {
        super(BlockList.mixed, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.MIXEDSEEDS);
    }
}