package net.radekaadek.sojka_mod;


import net.minecraft.client.renderer.entity.WitherBossRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.boss.wither.WitherBoss;

// import modid
import static net.radekaadek.sojka_mod.SojkaMod.MODID;

public class SojkaEntityRenderer extends WitherBossRenderer {
    // WITHER_LOCATION
    public static final ResourceLocation SOJKA_LOCATION = new ResourceLocation(MODID, "textures/entity/sojka.png");
    private static final ResourceLocation WITHER_INVULNERABLE_LOCATION = new ResourceLocation("textures/entity/sojka_invulnerable.png");
    public SojkaEntityRenderer(Context p_174445_) {
        super(p_174445_);
    }

    @Override
    public ResourceLocation getTextureLocation(WitherBoss p_116437_) {
        int i = p_116437_.getInvulnerableTicks();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? WITHER_INVULNERABLE_LOCATION : SOJKA_LOCATION;
     }
}
