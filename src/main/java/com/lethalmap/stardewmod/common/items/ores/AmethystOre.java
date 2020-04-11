package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;

public class AmethystOre extends BlockItem {
    public AmethystOre() {
        super(BlockList.amethystore, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.AMETHYSTORE);
    }
}

