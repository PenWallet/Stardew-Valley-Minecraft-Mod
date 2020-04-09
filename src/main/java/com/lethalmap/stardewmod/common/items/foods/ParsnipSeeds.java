package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class ParsnipSeeds extends BlockNamedItem {
    public ParsnipSeeds() {
        super(BlockList.parsnip, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.PARSNIPSEEDS);
    }
}
