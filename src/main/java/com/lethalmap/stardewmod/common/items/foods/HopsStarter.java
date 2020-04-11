package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class HopsStarter extends BlockNamedItem {
    public HopsStarter() {
        super(BlockList.hops, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.HOPSSTARTER);
    }
}