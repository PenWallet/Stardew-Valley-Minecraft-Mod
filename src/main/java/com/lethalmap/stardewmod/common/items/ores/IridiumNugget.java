package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class IridiumNugget extends Item {
    public IridiumNugget() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRIDIUMNUGGET);
    }
}
