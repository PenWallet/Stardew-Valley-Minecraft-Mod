package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class Parsnip extends Item {
    public Parsnip() {
        super(new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.PARSNIP));
        setRegistryName(Constants.PARSNIP);
    }
}
