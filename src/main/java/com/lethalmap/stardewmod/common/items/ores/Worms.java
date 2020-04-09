package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;

public class Worms extends BlockItem {
    public Worms() {
        super(BlockList.worms, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.WORMS);
    }
}
