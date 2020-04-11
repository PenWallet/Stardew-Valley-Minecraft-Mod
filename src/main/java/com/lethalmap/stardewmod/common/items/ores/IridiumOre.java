package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockItem;
import net.minecraft.world.World;

public class IridiumOre extends BlockItem {
    public IridiumOre() {
        super(BlockList.iridiumore, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRIDIUMORE);
    }
}