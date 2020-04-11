package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class StarfruitSeeds extends BlockNamedItem {
    public StarfruitSeeds() {
        super(BlockList.starfruit, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.STARFRUITSEEDS);
    }
}