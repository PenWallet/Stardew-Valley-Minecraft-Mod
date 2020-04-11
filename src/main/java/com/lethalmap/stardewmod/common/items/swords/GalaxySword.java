package com.lethalmap.stardewmod.common.items.swords;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.SwordItem;

public class GalaxySword extends SwordItem {

    public GalaxySword() {
        super(ToolTiers.GENERICSWORD, 6, 3f, new Properties().group(Constants.SMSSWORDSITEMGROUP));
        setRegistryName(Constants.MODID, Constants.GALAXYSWORD);
    }


}
