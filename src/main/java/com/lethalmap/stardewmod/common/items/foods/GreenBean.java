package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class GreenBean extends Item {
    public GreenBean() {
        super(new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.GREENBEAN));
        setRegistryName(Constants.GREENBEAN);
    }
}
