package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class TulipBulb extends BlockNamedItem {
    public TulipBulb() {
        super(BlockList.tulip, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.TULIPBULB);
    }
}