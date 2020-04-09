package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;

public class CauliflowerSeeds extends BlockNamedItem {
    public CauliflowerSeeds() {
        super(BlockList.cauliflower, new Properties().group(Constants.SMSEEDSITEMGROUP));
        setRegistryName(Constants.CAULIFLOWERSEEDS);
    }
}
