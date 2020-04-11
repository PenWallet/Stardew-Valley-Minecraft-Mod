package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class PepperSeeds extends BlockNamedItem {
    public PepperSeeds() {
        super(BlockList.pepper, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.PEPPERSEEDS);
    }
}