package com.lethalmap.stardewmod.common.items;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.SwordItem;

public class TemperedBroadSword extends SwordItem {
    public TemperedBroadSword() {
        super(ToolTiers.GENERICSWORD, 6,3f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.TEMPEREDBROADSWORD);
    }
}
