package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.*;

public class Garlic extends Item {
    public Garlic() {
        super(new Item.Properties().group(Constants.SMFOODITEMGROUP).food(Foods.GARLIC));
        setRegistryName(Constants.GARLIC);
    }
}
