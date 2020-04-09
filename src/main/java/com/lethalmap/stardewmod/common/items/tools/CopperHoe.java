package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;

public class CopperHoe extends HoeItem {
    public CopperHoe() {
        super(ToolTiers.COPPER, 6.0f, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.COPPERHOE);
    }
}
