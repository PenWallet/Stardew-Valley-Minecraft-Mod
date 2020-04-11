package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ItemList;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class GarlicBlock extends CropsBlock {
    public static final IntegerProperty GARLIC_AGE = IntegerProperty.create("age", 0, 4);
    private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public GarlicBlock()
    {
        super(Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .hardnessAndResistance(0.0F)
                .tickRandomly()
                .sound(SoundType.CROP));
        setRegistryName(Constants.GARLIC);
    }

    protected IItemProvider getSeedsItem() {
        return ItemList.garlicseeds;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES[state.get(this.getAgeProperty())];

    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GARLIC_AGE);
    }

    public IntegerProperty getAgeProperty() {
        return GARLIC_AGE;
    }

}
