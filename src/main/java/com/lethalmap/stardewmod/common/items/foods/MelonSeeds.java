package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class MelonSeeds extends BlockNamedItem {
    public MelonSeeds() {
        super(BlockList.melon, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.MELONSEEDS);
    }
}