package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class RiceShoot extends BlockNamedItem {
    public RiceShoot() {
        super(BlockList.unmilledrice, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.RICESHOOT);
    }
}