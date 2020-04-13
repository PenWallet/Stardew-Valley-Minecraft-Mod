package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;

public class EmeraldOre extends BlockItem {
    public EmeraldOre() {
        super(BlockList.emeraldore, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.EMERALDORE);
    }
}

