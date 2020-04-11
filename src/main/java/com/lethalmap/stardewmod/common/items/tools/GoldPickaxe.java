package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.AxeItem;
import net.minecraft.item.PickaxeItem;

public class GoldPickaxe extends PickaxeItem {
    public GoldPickaxe() {
        super(ToolTiers.GOLD, 6, 3f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.GOLDPICKAXE);
    }
}
