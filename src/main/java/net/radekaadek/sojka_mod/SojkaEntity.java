package net.radekaadek.sojka_mod;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.radekaadek.sounds.ModSounds;
import net.minecraft.world.damagesource.DamageSource;

public class SojkaEntity extends WitherBoss {

    public SojkaEntity(EntityType<? extends WitherBoss> type, Level level) {
        super(type, level);
        this.xpReward = 100;
    }


    @Override
    protected void dropCustomDeathLoot(net.minecraft.world.damagesource.DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);

        // Drop 3 feathers
        for (int i = 0; i < 3; i++) {
            ItemEntity itemEntity2 = this.spawnAtLocation(Items.FEATHER);
            if (itemEntity2 != null) {
                itemEntity2.setExtendedLifetime();
            }
        }
    }

    public static AttributeSupplier.Builder createSojkaAttributes() {
        return WitherBoss.createAttributes();
    }

    // Make sojka powered if the player doesnt have a paper named "Notatki" in their hand
    @Override
    public boolean isPowered() {
        Player player = this.level.getNearestPlayer(this, 64.0D);
        if (player != null) {
            if ((player.getMainHandItem().getItem() == Items.PAPER && player.getMainHandItem().getHoverName().getString().equals("Notatki")) ||
                player.getOffhandItem().getItem() == Items.PAPER && player.getMainHandItem().getHoverName().getString().equals("Notatki")) {
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

}
