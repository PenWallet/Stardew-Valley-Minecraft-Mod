package com.lethalmap.stardewmod.common.items.artifacts;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class ChippedAmphora extends Item {
    public ChippedAmphora() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.CHIPPEDAMPHORA);
    }
}
