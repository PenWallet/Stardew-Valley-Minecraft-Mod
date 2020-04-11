package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class RedCabbageSeeds extends BlockNamedItem {
    public RedCabbageSeeds() {
        super(BlockList.redcabbage, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.REDCABBAGESEEDS);
    }
}