package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class AmaranthSeeds extends BlockNamedItem {
    public AmaranthSeeds() {
        super(BlockList.amaranth, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.AMARANTHSEEDS);
    }
}