package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class GoldIngot extends Item {

    public GoldIngot() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.GOLDINGOT);
    }

    
}
