package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.AxeItem;

public class IridiumAxe extends AxeItem implements IStardewItem {
    public IridiumAxe() {
        super(ToolTiers.IRIDIUM, -1.0f, 6.0f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRIDIUMAXE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
