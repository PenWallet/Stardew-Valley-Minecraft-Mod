package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.HoeItem;

public class IridiumHoe extends HoeItem implements IStardewItem {
    public IridiumHoe() {
        super(ToolTiers.IRIDIUM, 6.0f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRIDIUMOHOE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
