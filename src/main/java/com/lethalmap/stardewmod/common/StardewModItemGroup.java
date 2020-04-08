package com.lethalmap.stardewmod.common;

import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class StardewModItemGroup extends ItemGroup {

    public StardewModItemGroup(){
        super("stardewmoditemgroup");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemList.icon);
    }
}
