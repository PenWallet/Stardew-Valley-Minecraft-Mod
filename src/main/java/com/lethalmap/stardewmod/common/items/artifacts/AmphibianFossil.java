package com.lethalmap.stardewmod.common.items.artifacts;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class AmphibianFossil extends Item {
    public AmphibianFossil() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.AMPHIBIANFOSSIL);
    }
}
