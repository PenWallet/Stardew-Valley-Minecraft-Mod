package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class StrawberrySeeds extends BlockNamedItem {
    public StrawberrySeeds() {
        super(BlockList.strawberry, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.STRAWBERRYSEEDS);
    }
}