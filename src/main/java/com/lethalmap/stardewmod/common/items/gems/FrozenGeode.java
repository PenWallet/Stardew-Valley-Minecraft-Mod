package com.lethalmap.stardewmod.common.items.gems;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class FrozenGeode extends Item {
    public FrozenGeode() {
        super(new Properties().group(Constants.SMSGEMSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.FROZENGEODE);
    }
}
