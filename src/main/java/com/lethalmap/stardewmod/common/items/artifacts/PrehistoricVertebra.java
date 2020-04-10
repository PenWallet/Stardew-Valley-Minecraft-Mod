package com.lethalmap.stardewmod.common.items.artifacts;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class PrehistoricVertebra extends Item {
    public PrehistoricVertebra() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.PREHISTORICVERTEBRA);
    }
}
