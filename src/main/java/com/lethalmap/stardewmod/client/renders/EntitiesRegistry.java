package com.lethalmap.stardewmod.client.renders;

import com.lethalmap.stardewmod.common.EntitiesList;
import com.lethalmap.stardewmod.common.entity.IridiumBat;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class EntitiesRegistry {
    public static void registryEntityRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<IridiumBat>) EntitiesList.IRIDIUM_BAT, new IridiumBatEntityRender.RenderFactory());
    }
}
