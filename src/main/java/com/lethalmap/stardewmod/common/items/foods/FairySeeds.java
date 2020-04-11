package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class FairySeeds extends BlockNamedItem {
    public FairySeeds() {
        super(BlockList.fairy, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.FAIRYSEEDS);
    }
}