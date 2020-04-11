package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class CornSeeds extends BlockNamedItem {
    public CornSeeds() {
        super(BlockList.corn, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.CORNSEEDS);
    }
}