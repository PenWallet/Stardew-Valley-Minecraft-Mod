package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class KaleSeeds extends BlockNamedItem {
    public KaleSeeds() {
        super(BlockList.kale, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.KALESEEDS);
    }
}