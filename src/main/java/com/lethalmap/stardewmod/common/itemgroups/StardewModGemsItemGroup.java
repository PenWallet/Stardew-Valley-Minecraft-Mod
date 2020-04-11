package com.lethalmap.stardewmod.common.itemgroups;

import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class StardewModGemsItemGroup extends ItemGroup {

    public StardewModGemsItemGroup(){
        super("stardewmodgemsitemgroup");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemList.svdiamond);
    }
}
