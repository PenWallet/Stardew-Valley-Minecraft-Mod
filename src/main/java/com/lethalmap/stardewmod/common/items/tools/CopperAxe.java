package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class CopperAxe extends AxeItem {
    public CopperAxe() {
        super(ToolTiers.COPPER, -1.0f, 6.0f, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERAXE);
    }
}
