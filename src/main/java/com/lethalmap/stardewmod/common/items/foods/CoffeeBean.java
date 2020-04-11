package com.lethalmap.stardewmod.common.items.foods;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class CoffeeBean extends BlockNamedItem {
    public CoffeeBean() {
        super(BlockList.coffeebean, new Properties().group(Constants.SMFOODITEMGROUP).food(Foods.COFFEEBEAN));
        setRegistryName(Constants.COFFEEBEAN);
    }
}
