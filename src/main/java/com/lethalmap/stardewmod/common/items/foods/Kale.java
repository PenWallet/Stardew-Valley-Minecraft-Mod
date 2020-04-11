package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class Kale extends Item {
    public Kale() {
        super(new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.KALE));
        setRegistryName(Constants.KALE);
    }
}
