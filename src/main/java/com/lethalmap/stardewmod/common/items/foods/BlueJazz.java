package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class BlueJazz extends Item {
    public BlueJazz() {
        super(new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.BLUEJAZZ));
        setRegistryName(Constants.BLUEJAZZ);
    }
}
