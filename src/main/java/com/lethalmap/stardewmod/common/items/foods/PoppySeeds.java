package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class PoppySeeds extends BlockNamedItem {
    public PoppySeeds() {
        super(BlockList.poppy, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.POPPYSEEDS);
    }
}