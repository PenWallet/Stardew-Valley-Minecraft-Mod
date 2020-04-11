package com.lethalmap.stardewmod.common.blocks;


import com.lethalmap.stardewmod.Constants;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;


public class Worms extends FlowerBlock {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 5.0D, 11.0D);

    public Worms() {
        super(Effects.WEAKNESS,9,Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.SLIME));
        setRegistryName(Constants.MODID,Constants.WORMS);
    }


    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public Block.OffsetType getOffsetType() {
        return Block.OffsetType.XZ;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Vec3d vec3d = state.getOffset(worldIn, pos);
        return this.SHAPE.withOffset(vec3d.x, vec3d.y, vec3d.z);
    }
}
