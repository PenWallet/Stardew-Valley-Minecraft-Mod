package com.lethalmap.stardewmod.common.items.ores;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.item.Item;

public class IridiumIngot extends Item {

    public IridiumIngot() {
        super(new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRIDIUMINGOT);
    }


}
