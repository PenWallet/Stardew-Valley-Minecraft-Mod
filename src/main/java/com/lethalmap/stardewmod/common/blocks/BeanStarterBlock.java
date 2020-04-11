package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/*public class GreenBeanBlock extends DoublePlantBlock {

    public GreenBeanBlock() {
        super(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
        setRegistryName(Constants.MODID, Constants.BEANSTARTER);
    }
}*/

public class BeanStarterBlock extends TallCropBlock {
    public static final IntegerProperty BEAN_STAGES = IntegerProperty.create("age", 0, 6);
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
        builder.add(BEAN_STAGES);
        builder.add(HALF);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return BEAN_STAGES;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    //Method used to get what happens when clicked
    @Override
    public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        int age = state.get(getAgeProperty());
        boolean flag = age == getMaxAge();
        if (player.getHeldItem(hand).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (flag) {
            int amount = 1 + world.rand.nextInt(2);
            spawnAsEntity(world, pos, new ItemStack(ItemList.greenbean, amount + (flag ? 1 : 0)));
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
            if(state.get(HALF) == DoubleBlockHalf.LOWER)
            {
                world.setBlockState(pos.up(), this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.UPPER).with(getAgeProperty(), getMaxAge()+1));
                world.setBlockState(pos, this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(getAgeProperty(), getMaxAge()+1));
            }
            else
            {
                world.setBlockState(pos, this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.UPPER).with(getAgeProperty(), getMaxAge()+1));
                world.setBlockState(pos.down(), this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(getAgeProperty(), getMaxAge()+1));
            }

            return ActionResultType.SUCCESS;
        } else {
            return super.func_225533_a_(state, world, pos, player, hand, blockRayTraceResult);
        }
    }
}