package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class RareSeed extends BlockNamedItem {
    public RareSeed() {
        super(BlockList.sweetgemberry, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.RARESEED);
    }
}