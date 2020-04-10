package com.lethalmap.stardewmod.common.items.gems;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class MagmaGeode extends Item {
    public MagmaGeode() {
        super(new Properties().group(Constants.SMSGEMSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.MAGMAGEODE);
    }
}
