package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class Rhubarb extends Item {
    public Rhubarb() {
        super(new Properties().group(Constants.SMFOODITEMGROUP));
        setRegistryName(Constants.RHUBARB);
    }
}
