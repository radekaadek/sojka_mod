package net.radekaadek.sojka_mod;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SojkaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {
    @SubscribeEvent
    public static void entityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent event) {
        event.put(SojkaInnit.SOJKA.get(), SojkaEntity.createSojkaAttributes().build());
    }

    @SubscribeEvent
    public static void registerEntities(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SojkaInnit.SOJKA.get(), SojkaEntityRenderer::new);
    }
}