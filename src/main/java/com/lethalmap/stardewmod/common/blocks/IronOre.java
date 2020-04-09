package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class IronOre extends Block {
    public IronOre() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(4.0f, 4.0f)
                .sound(SoundType.STONE));
        setRegistryName(Constants.IRONORE);
    }
}
