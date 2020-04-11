package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*public class GreenBeanBlock extends DoublePlantBlock {

    public GreenBeanBlock() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
        setRegistryName(Constants.MODID, Constants.BEANSTARTER);
    }
}*/

public class BeanStarterBlock extends TallCropBlock {
    public static final IntegerProperty BEAN_AGE = IntegerProperty.create("age", 0, 5);
    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public BeanStarterBlock() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
        setRegistryName(Constants.MODID, Constants.BEANSTARTER);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemList.beanstarter;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BEAN_AGE);
        builder.add(HALF);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return BEAN_AGE;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}