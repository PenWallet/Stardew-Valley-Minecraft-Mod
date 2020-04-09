package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class BlueJazzSeeds extends BlockNamedItem {
    public BlueJazzSeeds() {
        super(BlockList.bluejazz, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.BLUEJAZZSEEDS);
    }
}
