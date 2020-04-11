package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class BokChoySeeds extends BlockNamedItem {
    public BokChoySeeds() {
        super(BlockList.bokchoy, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.BOKCHOYSEEDS);
    }
}