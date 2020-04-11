package com.lethalmap.stardewmod.common.blocks;

import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class TallCropBlock extends BushBlock implements IGrowable {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    protected TallCropBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(this.getAgeProperty(), 0).with(HALF, DoubleBlockHalf.LOWER));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 7;
    }

    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    public BlockState withHalfAndAge(DoubleBlockHalf half, int age) {
        return this.getDefaultState().with(this.getAgeProperty(), Integer.valueOf(age)).with(HALF, half);
    }

    public boolean isMaxAge(BlockState state) {
        return state.get(this.getAgeProperty()) >= this.getMaxAge();
    }

    public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random p_225534_4_) {
        super.func_225534_a_(state, worldIn, pos, p_225534_4_);
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.func_226659_b_(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, p_225534_4_.nextInt((int)(25.0F / f) + 1) == 0)) {
                    worldIn.setBlockState(pos, this.getDefaultState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.LOWER), 2);
                    worldIn.setBlockState(pos.up(), this.getDefaultState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.UPPER), 2);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }

    }

    public void grow(World worldIn, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, this.getDefaultState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.LOWER), 2);
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.UPPER), 2);
        //worldIn.setBlockState(pos, this.withHalfAndAge(, i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.nextInt(worldIn.rand, 2, 5);
    }

    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), net.minecraft.util.Direction.UP, (net.minecraftforge.common.IPlantable)blockIn)) {
                    f1 = 1.0F;
                    if (blockstate.isFertile(worldIn, blockpos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof RavagerEntity && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, entityIn)) {
            worldIn.func_225521_a_(pos, true, entityIn);
        }

        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    protected IItemProvider getSeedsItem() {
        return Items.WHEAT_SEEDS;
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        //state.get(this.getAgeProperty()) == this.getMaxAge()+1 used to check if the crop is in the harvested state
        return !this.isMaxAge(state) || state.get(this.getAgeProperty()) == this.getMaxAge()+1;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public void func_225535_a_(ServerWorld worldIn, Random p_225535_2_, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        //If player clicked the bottom half, or the top
        if(state.get(HALF) == DoubleBlockHalf.LOWER)
        {
            worldIn.setBlockState(pos, this.getStateContainer().getBaseState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.LOWER), 2);
            worldIn.setBlockState(pos.up(), this.getStateContainer().getBaseState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.UPPER), 2);
        }
        else
        {
            worldIn.setBlockState(pos, this.getStateContainer().getBaseState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.UPPER), 2);
            worldIn.setBlockState(pos.down(), this.getStateContainer().getBaseState().with(this.getAgeProperty(), i).with(HALF, DoubleBlockHalf.LOWER), 2);
        }
    }


    /*
        ---------------- BEGINNING OF DOUBLEPLANTBLOCK ----------------
     */

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.get(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        return blockpos.getY() < context.getWorld().getDimension().getHeight() - 1 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context) ? super.getStateForPlacement(context) : null;
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        //This is the check for validPosition from the CropsBlock
        BlockPos positionGround = pos.down();
        Block blockGround = worldIn.getBlockState(positionGround).getBlock();

        //Used to avoid player being able to place crops on top of crops
        if(blockGround == this && state.get(HALF) == DoubleBlockHalf.LOWER && worldIn.getBlockState(positionGround).get(HALF) == DoubleBlockHalf.UPPER)
            return false;

        //isValidPositionCropsBlock checks for the light level of the block
        boolean isValidPositionCropsBlock = (worldIn.func_226659_b_(pos, 0) >= 8 || worldIn.func_226660_f_(pos));
        return (blockGround == Blocks.FARMLAND || blockGround == this) && isValidPositionCropsBlock;
    }


    public void placeAt(IWorld worldIn, BlockPos pos, int flags) {
        worldIn.setBlockState(pos, this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER), flags);
        worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER), flags);
    }

    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
    }

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleblockhalf = state.get(HALF);
        BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
            worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
            worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            if (!worldIn.isRemote && !player.isCreative()) {
                spawnDrops(state, worldIn, pos, (TileEntity)null, player, player.getHeldItemMainhand());
                spawnDrops(blockstate, worldIn, blockpos, (TileEntity)null, player, player.getHeldItemMainhand());
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(this.getAgeProperty());
        builder.add(HALF);
    }

    /**
     * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
     */
    public Block.OffsetType getOffsetType() {
        return OffsetType.NONE;
    }

    /**
     * Return a random long to be passed to IBakedModel#getQuads, used for random model rotations
     */
    @OnlyIn(Dist.CLIENT)
    public long getPositionRandom(BlockState state, BlockPos pos) {
        return MathHelper.getCoordinateRandom(pos.getX(), pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    //Gets the amount to drop when right clicked. Defaults to Dirt ¯\_(?)_/¯. OVERRIDE IN CHILD CLASS
    public Item getDropWhenHarvested()
    {
        return Items.DIRT;
    }

    //Gets the max amount of drops. Defaults to 1. OVERRIDE IN CHILD CLASS
    public int getMaxAmountOfDrops()
    {
        return 1;
    }

    //Sets if, when harvested, the crop should always drop the max amount. Defaults to false. OVERRIDE IN CHILD CLASS
    public boolean alwaysDropMaxAmountOfDrops()
    {
        return false;
    }

    //Method used to get what happens when clicked
    @Override
    public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        int age = state.get(getAgeProperty());
        boolean flag = age == getMaxAge();
        if (player.getHeldItem(hand).getItem() == Items.BONE_MEAL) {
            return ActionResultType.PASS;
        } else if (flag) {
            //If alwaysDropMaxAmountOfDrops is true, then the amount is the max. If not, then if the max is 1, drop one, if not, then do a random
            int amount = this.alwaysDropMaxAmountOfDrops() ? getMaxAmountOfDrops() : (this.getMaxAmountOfDrops() == 1 ? 1 : (1 + world.rand.nextInt(getMaxAmountOfDrops()-1)));
            spawnAsEntity(world, pos, new ItemStack(this.getDropWhenHarvested(), amount));
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
