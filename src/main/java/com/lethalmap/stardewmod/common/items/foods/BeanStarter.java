package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class BeanStarter extends BlockNamedItem {
    public BeanStarter() {
        super(BlockList.beanstarter, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.BEANSTARTER);
    }
}
