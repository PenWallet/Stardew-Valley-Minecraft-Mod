package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class Cauliflower extends Item {
    public Cauliflower() {
        super(new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.CAULIFLOWER));
        setRegistryName(Constants.CAULIFLOWER);
    }
}
