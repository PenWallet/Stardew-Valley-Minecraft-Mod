package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.AxeItem;

public class IronAxe extends AxeItem implements IStardewItem {
    public IronAxe() {
        super(ToolTiers.IRON, -1.0f, 6.0f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRONAXE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
