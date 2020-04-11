package com.lethalmap.stardewmod.common.items.tools;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import net.minecraft.item.PickaxeItem;

public class IridiumPickaxe extends PickaxeItem {
    public IridiumPickaxe() {
        super(ToolTiers.IRIDIUM, 6, 3f, new Properties().group(Constants.SMITEMGROUP));
        setRegistryName(Constants.MODID, Constants.IRIDIUMPICKAXE);
    }
}
