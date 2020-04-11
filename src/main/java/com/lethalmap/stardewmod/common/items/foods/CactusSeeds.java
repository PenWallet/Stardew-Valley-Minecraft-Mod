package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class CactusSeeds extends BlockNamedItem {
    public CactusSeeds() {
        super(BlockList.cactus, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.CACTUSSEEDS);
    }
}