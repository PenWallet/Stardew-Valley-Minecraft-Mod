package com.lethalmap.stardewmod.common.items.swords;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.tools.ToolTiers;
import net.minecraft.item.SwordItem;

public class TemperedBroadSword extends SwordItem {
    public TemperedBroadSword() {
        super(ToolTiers.GENERICSWORD, 6,3f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.TEMPEREDBROADSWORD);
    }
}
