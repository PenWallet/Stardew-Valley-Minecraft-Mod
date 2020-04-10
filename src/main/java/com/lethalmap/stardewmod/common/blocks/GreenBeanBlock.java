package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class GreenBeanBlock extends DoublePlantBlock {

    public GreenBeanBlock() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
        setRegistryName(Constants.MODID, Constants.BEANSTARTER);
    }
}
