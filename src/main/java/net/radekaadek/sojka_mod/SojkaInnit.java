package net.radekaadek.sojka_mod;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// A class that contains creates a sójka - jay mob based on the Wither
public class SojkaInnit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SojkaMod.MODID);

    public static final RegistryObject<EntityType<SojkaEntity>> SOJKA = ENTITIES.register("sojka",
        () -> EntityType.Builder.of(SojkaEntity::new, MobCategory.MONSTER)
            .sized(0.9F, 3.5F)
            .clientTrackingRange(8)
            .updateInterval(3)
            .build(SojkaMod.MODID + ":sojka"));

}