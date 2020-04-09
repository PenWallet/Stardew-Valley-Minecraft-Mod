package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class IronNugget extends Item {
    public IronNugget() {
        super(new Item.Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRONNUGGET);
    }
}
