package com.lethalmap.stardewmod.common.items.swords;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.tools.ToolTiers;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class TemplarBlade extends SwordItem {
    public TemplarBlade() {
        super(ToolTiers.GENERICSWORD, 6,3f, new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.TEMPLARBLADE);
    }
}
