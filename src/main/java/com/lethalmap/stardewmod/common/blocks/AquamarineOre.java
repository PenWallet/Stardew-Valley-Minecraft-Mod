package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AquamarineOre extends Block {

    public AquamarineOre() {
        super(Properties.create(Material.IRON)
                .hardnessAndResistance(3.0f, 3.0f)
                .sound(SoundType.STONE));
        setRegistryName(Constants.AQUAMARINEORE);
    }
}
