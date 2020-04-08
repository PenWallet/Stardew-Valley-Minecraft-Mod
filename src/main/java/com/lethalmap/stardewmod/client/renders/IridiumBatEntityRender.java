package com.lethalmap.stardewmod.client.renders;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.client.models.IridiumBatModel;
import com.lethalmap.stardewmod.common.entity.IridiumBat;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class IridiumBatEntityRender extends MobRenderer<IridiumBat, IridiumBatModel> {

    public IridiumBatEntityRender(EntityRendererManager manager)
    {
        super(manager, new IridiumBatModel(), 0f);
    }

    @Override
    public ResourceLocation getEntityTexture(IridiumBat entity) {
        return new ResourceLocation(Constants.MODID, "textures/entity/iridiumbat.png");
    }

    public static class RenderFactory implements IRenderFactory<IridiumBat>
    {
        @Override
        public EntityRenderer<? super IridiumBat> createRenderFor(EntityRendererManager manager) {
            return new IridiumBatEntityRender(manager);
        }
    }
}
