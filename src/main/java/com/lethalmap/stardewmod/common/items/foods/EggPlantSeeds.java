package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class EggPlantSeeds extends BlockNamedItem {
    public EggPlantSeeds() {
        super(BlockList.eggplant, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.EGGPLANTSEEDS);
    }
}