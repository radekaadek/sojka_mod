package net.radekaadek.sounds;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import static net.radekaadek.sojka_mod.SojkaMod.MODID;;

public class ModSounds {
    public static SoundEvent SOJKA_AMBIENT = new SoundEvent(new ResourceLocation(MODID, "entity.sojka.ambient"));
    public static SoundEvent SOJKA_HURT = new SoundEvent(new ResourceLocation(MODID, "entity.sojka.hurt"));
    public static SoundEvent SOJKA_DEATH = new SoundEvent(new ResourceLocation(MODID, "entity.sojka.death"));

}
