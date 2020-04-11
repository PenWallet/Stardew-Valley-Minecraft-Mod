package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class ArtichokeSeeds extends BlockNamedItem {
    public ArtichokeSeeds() {
        super(BlockList.artichoke, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.ARTICHOKESEEDS);
    }
}