package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class CopperPickaxe extends PickaxeItem {
    public CopperPickaxe() {
        super(ToolTiers.COPPER, 0, 6.0f, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERPICKAXE);
    }
}
