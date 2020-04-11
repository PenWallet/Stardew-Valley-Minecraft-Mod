package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class AncientSeeds extends BlockNamedItem {
    public AncientSeeds() {
        super(BlockList.ancient, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.ANCIENTSEEDS);
    }
}