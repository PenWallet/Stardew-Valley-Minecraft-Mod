package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class RadishSeeds extends BlockNamedItem {
    public RadishSeeds() {
        super(BlockList.radish, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.RADISHSEEDS);
    }
}