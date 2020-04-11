package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;

public class AquamarineOre extends BlockItem {
    public AquamarineOre() {
        super(BlockList.aquamarineore, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.AQUAMARINEORE);
    }
}

