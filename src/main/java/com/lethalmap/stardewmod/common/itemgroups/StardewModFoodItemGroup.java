package com.lethalmap.stardewmod.common.itemgroups;

import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class StardewModFoodItemGroup extends ItemGroup {

    public StardewModFoodItemGroup(){
        super("stardewmodfooditemgroup");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemList.garlic);
    }
}
