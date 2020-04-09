package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class GarlicSeeds extends BlockNamedItem {
    public GarlicSeeds() {
        super(BlockList.garlic, new Item.Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.GARLICSEEDS);
    }
}
