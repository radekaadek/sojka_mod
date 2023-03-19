package net.radekaadek.sojka_mod;

import java.util.EnumSet;
import java.util.function.Predicate;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.radekaadek.sounds.ModSounds;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;

public class SojkaEntity extends WitherBoss {

    public SojkaEntity(EntityType<? extends WitherBoss> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setHealth(this.getMaxHealth());
        this.xpReward = 100;
    }

    @Override
    public boolean canBeAffected(MobEffectInstance p_31495_) {
        return super.canBeAffected(p_31495_);
     }

    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (p_31504_) -> {
        return p_31504_.getMobType() != MobType.UNDEAD && p_31504_.attackable();
     };

    @Override
     protected void registerGoals() {
        this.goalSelector.addGoal(0, new SojkaDoNothingGoal());
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));
     }


    @Override
    protected void dropCustomDeathLoot(net.minecraft.world.damagesource.DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);

        // Drop 2-4 feathers and take looting into account
        int featherCount = this.random.nextInt(3) + 2 + this.random.nextInt(1 + looting);
        for (int i = 0; i < featherCount; i++) {
            this.spawnAtLocation(Items.FEATHER);
        }
    }

    public static AttributeSupplier.Builder createSojkaAttributes() {
        // add 2x speed
        return SojkaEntity.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.5D);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return SojkaEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 200.0D).add(Attributes.MOVEMENT_SPEED, (double)1.2F).add(Attributes.FLYING_SPEED, (double)1.2F).add(Attributes.FOLLOW_RANGE, 40.0D).add(Attributes.ARMOR, 4.0D);
    }

    // Make sojka powered if the player doesnt have a paper named "Notatki" in their hand
    @Override
    public boolean isPowered() {
        Player player = this.level.getNearestPlayer(this, 64.0D);
        if (player != null) {
            if ((player.getMainHandItem().getItem() == Items.PAPER && player.getMainHandItem().getHoverName().getString().equals("Notatki")) ||
                player.getOffhandItem().getItem() == Items.PAPER && player.getOffhandItem().getHoverName().getString().equals("Notatki")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    // custom sound
    @Override
    public SoundEvent getAmbientSound() {
        return ModSounds.SOJKA_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.SOJKA_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return ModSounds.SOJKA_DEATH;
    }

    class SojkaDoNothingGoal extends Goal {
        public SojkaDoNothingGoal() {
           this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
        }
  
        public boolean canUse() {
           return SojkaEntity.this.getInvulnerableTicks() > 0;
        }
     }

}
