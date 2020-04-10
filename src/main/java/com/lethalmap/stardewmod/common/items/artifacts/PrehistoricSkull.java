package com.lethalmap.stardewmod.common.items.artifacts;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class PrehistoricSkull extends Item {
    public PrehistoricSkull() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.PREHISTORICSKULL);
    }
}
