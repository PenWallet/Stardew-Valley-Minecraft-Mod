package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class GrapeStarter extends BlockNamedItem {
    public GrapeStarter() {
        super(BlockList.grape, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.GRAPESTARTER);
    }
}