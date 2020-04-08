package com.lethalmap.stardewmod.common.entity;

import com.lethalmap.stardewmod.common.EntitiesList;
import com.lethalmap.stardewmod.common.entity.ai.goal.ZombieAttackModifiedGoal;
import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IridiumBat extends BatEntity
{
    private BlockPos spawnPosition;
    private static final EntityPredicate field_213813_c = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();

    @SuppressWarnings("unchecked")
    public IridiumBat(EntityType<? extends BatEntity> type, World worldIn) {
        super((EntityType<? extends BatEntity>) EntitiesList.IRIDIUM_BAT, worldIn);
        this.setIsBatHanging(false);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0f);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0f);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(0, new ZombieAttackModifiedGoal(this, 1.0D, true));
        //this.targetSelector.addGoal(1, new IridiumBatHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : SoundEvents.ENTITY_ENDER_DRAGON_FLAP;
    }

    @Override
    protected void updateAITasks()
    {
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.up();
        if (this.getIsBatHanging()) {
            if (this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos)) {
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = (float)this.rand.nextInt(360);
                }

                if (this.world.getClosestPlayer(field_213813_c, this) != null) {
                    this.setIsBatHanging(false);
                    this.world.playEvent(null, 1025, blockpos, 0);
                }
            } else {
                this.setIsBatHanging(false);
                this.world.playEvent(null, 1025, blockpos, 0);
            }
        } else {
            if(getAttackTarget() != null && canAttack(getAttackTarget()))
            {
                Vec3d currentMovement = this.getMotion();
                //Vec3d finalMovement = currentMovement.add(, (), ());
                this.setMotion(this.getMotion());
            }
            else
            {
                if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
                    this.spawnPosition = null;
                }

                if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
                    this.spawnPosition = new BlockPos(this.func_226277_ct_() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.func_226278_cu_() + (double)this.rand.nextInt(6) - 2.0D, this.func_226281_cx_() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
                }

                double d0 = (double)this.spawnPosition.getX() + 0.5D - this.func_226277_ct_();
                double d1 = (double)this.spawnPosition.getY() + 0.1D - this.func_226278_cu_();
                double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.func_226281_cx_();
                Vec3d vec3d = this.getMotion();
                Vec3d vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double)0.1F, (Math.signum(d1) * (double)0.7F - vec3d.y) * (double)0.1F, (Math.signum(d2) * 0.5D - vec3d.z) * (double)0.1F);
                float f = (float)(MathHelper.atan2(vec3d1.z, vec3d1.x) * (double)(180F / (float)Math.PI)) - 90.0F;
                float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
                this.moveForward = 0.5F;
                this.rotationYaw += f1;
                if (this.rand.nextInt(100) == 0 && this.world.getBlockState(blockpos1).isNormalCube(this.world, blockpos1)) {
                    this.setIsBatHanging(true);
                }
            }



        }

    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        if(spawnReasonIn != SpawnReason.NATURAL || !(worldIn.canBlockSeeSky(this.getPosition()) && this.func_226278_cu_() < 55))
            return true;

        return false;
    }

    /*@SubscribeEvent
    public void drop(LivingDeathEvent event)
    {
        if(!(event.getEntity() instanceof IridiumBat))
            return;

        if(!event.getEntity().world.isRemote)
        {
            event.getEntity().entityDropItem(ItemList.combatboots_armor);
        }
    }*/
}
