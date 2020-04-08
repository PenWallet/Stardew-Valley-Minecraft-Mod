package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class TemplarBlade extends SwordItem {
    public TemplarBlade() {
        super(ToolTiers.GENERICSWORD, 6,3f, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.TEMPLARBLADE);
    }
}
