package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.HoeItem;

public class IronHoe extends HoeItem implements IStardewItem {
    public IronHoe() {
        super(ToolTiers.IRON, 6.0f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRONHOE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
