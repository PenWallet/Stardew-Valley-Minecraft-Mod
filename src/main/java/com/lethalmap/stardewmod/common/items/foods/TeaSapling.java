package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class TeaSapling extends BlockNamedItem {
    public TeaSapling() {
        super(BlockList.tealeaves, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.TEASAPLING);
    }
}