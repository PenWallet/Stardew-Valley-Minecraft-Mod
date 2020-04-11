package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class RhubarbSeeds extends BlockNamedItem {
    public RhubarbSeeds() {
        super(BlockList.rhubarb, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.RHUBARBSEEDS);
    }
}