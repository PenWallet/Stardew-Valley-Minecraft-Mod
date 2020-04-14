package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.IStardewItem;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.PickaxeItem;

public class StarterPickaxe extends PickaxeItem implements IStardewItem {
    public StarterPickaxe() {
        super(ToolTiers.STARTER, 0, 6.0f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.STARTERPICKAXE);
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}
