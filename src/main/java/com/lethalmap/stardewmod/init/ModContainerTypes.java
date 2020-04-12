package com.lethalmap.stardewmod.init;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.inventory.BackpackContainer;
import com.lethalmap.stardewmod.inventory.BackpackContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;



public final class ModContainerTypes {
    public static ContainerType<BackpackContainer> backpack;

    private ModContainerTypes(){

    }
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> event){
        backpack = register("backpack", new ContainerType<>(BackpackContainer::new));
    }

    public static void registerScreens(FMLClientSetupEvent event){
       ScreenManager.registerFactory(backpack, BackpackContainerScreen::new);
    }


    private static <T extends Container> ContainerType<T> register(String name, ContainerType<T> type) {

        type.setRegistryName(Constants.MODID);
        ForgeRegistries.CONTAINERS.register(type);
        return type;
    }
}
