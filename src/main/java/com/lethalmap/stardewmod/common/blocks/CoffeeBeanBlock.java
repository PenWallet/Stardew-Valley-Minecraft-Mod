package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/*public class GreenBeanBlock extends DoublePlantBlock {

    public GreenBeanBlock() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
        setRegistryName(Constants.MODID, Constants.BEANSTARTER);
    }
}*/

public class CoffeeBeanBlock extends TallCropBlock {
    public static final IntegerProperty COFFEE_STAGES = IntegerProperty.create("age", 0, 6);
    private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public CoffeeBeanBlock() {
        super(Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
        setRegistryName(Constants.MODID, Constants.COFFEEBEAN);
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return ItemList.coffeebean;
    }

    @Override
    public int getMaxAge() {
        return 5;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return COFFEE_STAGES;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public Item getDropWhenHarvested() {
        return ItemList.coffeebean;
    }

    @Override
    public int getMaxAmountOfDrops() {
        return 2;
    }

    @Override
    public boolean alwaysDropMaxAmountOfDrops() {
        return true;
    }
}