package com.lethalmap.stardewmod.common.items.dagger;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.SwordItem;

public class CarvingKnife extends SwordItem {
    public CarvingKnife() {
        super(ToolTiers.GENERICDAGGER, 6,3f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.CARVINGKNIFE);
    }
}
