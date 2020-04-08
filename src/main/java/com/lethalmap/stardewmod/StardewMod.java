package com.lethalmap.stardewmod;

import com.lethalmap.stardewmod.client.renders.EntitiesRegistry;
import com.lethalmap.stardewmod.common.EntitiesList;
import com.lethalmap.stardewmod.common.StardewModItemGroup;
import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.blocks.CopperOre;
import com.lethalmap.stardewmod.common.config.Config;
import com.lethalmap.stardewmod.common.items.ArmorTiers;
import com.lethalmap.stardewmod.common.items.ToolTiers;
import com.lethalmap.stardewmod.common.items.ItemList;
import com.lethalmap.stardewmod.common.world.OreGeneration;
import com.mojang.datafixers.kinds.Const;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("stardewmod")
public class StardewMod
{
    // Directly reference a log4j logger.
    public static StardewMod instance;
    private static final Logger LOGGER = LogManager.getLogger(Constants.MODID);

    public StardewMod() {
        instance = this;

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("stardewmod-client.toml").toString());
        Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("stardewmod-server.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");

        OreGeneration.setupOreGeneration();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        EntitiesRegistry.registryEntityRenders();
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");

            blockRegistryEvent.getRegistry().registerAll(
                    BlockList.copperore_block = new CopperOre()
            );
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Item");

            itemRegistryEvent.getRegistry().registerAll(
                    ItemList.copperingot_item = new Item(new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.COPPERINGOT)),

                    ItemList.copperore_block = new BlockItem(BlockList.copperore_block, new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(Constants.COPPERORE),
                    ItemList.icon = new Item(new Item.Properties()).setRegistryName(new ResourceLocation(Constants.MODID, Constants.ICON)),
                    ItemList.copperaxe_tool = new AxeItem(ToolTiers.COPPER, -1.0f, 6.0f, new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.COPPERAXE)),
                    ItemList.copperpickaxe_tool = new PickaxeItem(ToolTiers.COPPER, 0, 6.0f, new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.COPPERPICKAXE)),
                    ItemList.copperhoe_tool = new HoeItem(ToolTiers.COPPER, 6.0f, new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.COPPERHOE)),
                    ItemList.coppernugget = new Item(new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.COPPERNUGGET)),
                    ItemList.combatboots_armor = new ArmorItem(ArmorTiers.COMBATBOOTS, EquipmentSlotType.FEET, new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.COMBATBOOTS)),
                    ItemList.templarblade = new SwordItem(ToolTiers.GENERICSWORD, 6,3f, new Item.Properties().group(Constants.SMITEMGROUP)).setRegistryName(new ResourceLocation(Constants.MODID, Constants.TEMPLARBLADE))
            );

            EntitiesList.registerEntitySpawnEggs(itemRegistryEvent);
        }

        @SubscribeEvent
        public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> entityRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Entity");

            entityRegistryEvent.getRegistry().registerAll(
                    EntitiesList.IRIDIUM_BAT
            );

            EntitiesList.registerEntityWorldSpawns();
        }
    }
}
