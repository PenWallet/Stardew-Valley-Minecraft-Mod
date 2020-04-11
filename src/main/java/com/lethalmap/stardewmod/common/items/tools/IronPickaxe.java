package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.PickaxeItem;

public class IronPickaxe extends PickaxeItem {
    public IronPickaxe() {
        super(ToolTiers.IRON, 0, 6.0f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRONPICKAXE);
    }
}
