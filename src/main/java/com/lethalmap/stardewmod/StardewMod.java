package com.lethalmap.stardewmod;

import com.lethalmap.stardewmod.client.renders.EntitiesRegistry;
import com.lethalmap.stardewmod.common.EntitiesList;
import com.lethalmap.stardewmod.common.blocks.*;
import com.lethalmap.stardewmod.common.blocks.CopperOre;
import com.lethalmap.stardewmod.common.blocks.GoldOre;
import com.lethalmap.stardewmod.common.blocks.IridiumOre;
import com.lethalmap.stardewmod.common.blocks.IronOre;
import com.lethalmap.stardewmod.common.blocks.Worms;
import com.lethalmap.stardewmod.common.config.Config;
import com.lethalmap.stardewmod.common.items.*;
import com.lethalmap.stardewmod.common.items.artifacts.*;
import com.lethalmap.stardewmod.common.items.dagger.CarvingKnife;
import com.lethalmap.stardewmod.common.items.foods.*;
import com.lethalmap.stardewmod.common.items.gems.*;
import com.lethalmap.stardewmod.common.items.ores.*;
import com.lethalmap.stardewmod.common.items.swords.*;
import com.lethalmap.stardewmod.common.items.armors.CombatBoots;
import com.lethalmap.stardewmod.common.items.tools.*;
import com.lethalmap.stardewmod.common.world.OreGeneration;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
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
public class StardewMod {
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

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");

        OreGeneration.setupOreGeneration();
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        RenderTypeLookup.setRenderLayer(BlockList.garlic, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.bluejazz, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.cauliflower, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.parsnip, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.worms, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.beanstarter, RenderType.func_228643_e_());
        EntitiesRegistry.registryEntityRenders();
    }

    @SubscribeEvent
    public void lootLoad(LootTableLoadEvent evt) {
        switch (evt.getName().toString()) {
            case "minecraft:chests/buried_treasure":
                evt.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Constants.MODID, "inject/buried_treasure"))).build());
                break;

            case "minecraft:gameplay/fishing":
                evt.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Constants.MODID, "inject/fishing"))).build());
                break;

            case "minecraft:entities/phantom":
                evt.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Constants.MODID, "inject/phantomdarksword"))).build());
                break;

            case "minecraft:entities/blaze":
            case "minecraft:entities/zombie":
            case "minecraft:entities/spider":
            case "minecraft:entities/skeleton":
            case "minecraft:entities/husk":
            case "minecraft:entities/creeper":
            case "minecraft:entities/slime":
            case "minecraft:entities/vex":
            case "minecraft:entities/ghast":
            case "minecraft:entities/guardian":

                evt.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Constants.MODID, "inject/mobdrops"))).build());
                break;
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
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
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");

            blockRegistryEvent.getRegistry().registerAll(
                    BlockList.copperore = new CopperOre(),
                    BlockList.goldore = new GoldOre(),
                    BlockList.iridiumore = new IridiumOre(),
                    BlockList.garlic = new GarlicBlock(),
                    BlockList.bluejazz = new BlueJazzBlock(),
                    BlockList.cauliflower = new CauliflowerBlock(),
                    BlockList.worms = new Worms(),
                    BlockList.ironore = new IronOre(),
                    BlockList.parsnip = new ParsnipBlock(),
                    BlockList.beanstarter = new BeanStarterBlock()
            );
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Item");

            itemRegistryEvent.getRegistry().registerAll(
                    ItemList.copperingot = new CopperIngot(),

                    ItemList.copperore = new com.lethalmap.stardewmod.common.items.ores.CopperOre(),
                    ItemList.ironore = new com.lethalmap.stardewmod.common.items.ores.IronOre(),
                    ItemList.goldore = new com.lethalmap.stardewmod.common.items.ores.GoldOre(),
                    ItemList.iridiumore = new com.lethalmap.stardewmod.common.items.ores.IridiumOre(),
                    ItemList.icon = new Item(new Item.Properties()).setRegistryName(new ResourceLocation(Constants.MODID, Constants.ICON)),
                    ItemList.copperaxe = new CopperAxe(),
                    ItemList.copperpickaxe = new CopperPickaxe(),
                    ItemList.copperhoe = new CopperHoe(),
                    ItemList.combatboots = new CombatBoots(),
                    ItemList.templarblade = new TemplarBlade(),
                    ItemList.rustysword = new RustySword(),
                    ItemList.woodenblade = new WoodenBlade(),
                    ItemList.steelsmallsword = new SteelSmallSword(),
                    ItemList.silversaber = new SilverSaber(),
                    ItemList.piratesword = new PirateSword(),
                    ItemList.cutlass = new Cutlass(),
                    ItemList.ironedge = new IronEdge(),
                    ItemList.forestsword = new ForestSword(),
                    ItemList.holyblade = new HolyBlade(),
                    ItemList.insecthead = new InsectHead(),
                    ItemList.claymore = new Claymore(),
                    ItemList.darksword = new DarkSword(),
                    ItemList.neptuneglaive = new NeptuneGlaive(),
                    ItemList.temperedbroadsword = new TemperedBroadSword(),
                    ItemList.obsidianedge = new ObsidianEdge(),
                    ItemList.bonesword = new BoneSword(),
                    ItemList.steelfalchion = new SteelFalchion(),
                    ItemList.lavakatana = new LavaKatana(),
                    ItemList.galaxysword = new GalaxySword(),
                    ItemList.coppernugget = new CopperNugget(),
                    ItemList.ironnugget = new IronNugget(),
                    ItemList.iridiumnugget = new IridiumNugget(),
                    ItemList.goldnugget = new GoldNugget(),
                    ItemList.garlic = new Garlic(),
                    ItemList.carvingknife = new CarvingKnife(),
                    ItemList.garlicseeds = new GarlicSeeds(),
                    ItemList.bluejazzseeds = new BlueJazzSeeds(),
                    ItemList.cauliflowerseeds = new CauliflowerSeeds(),
                    ItemList.bluejazz = new BlueJazz(),
                    ItemList.worms = new com.lethalmap.stardewmod.common.items.ores.Worms(),
                    ItemList.cauliflower = new Cauliflower(),
                    ItemList.parsnip = new Parsnip(),
                    ItemList.beanstarter = new BeanStarter(),
                    ItemList.parsnipseeds = new ParsnipSeeds(),
                    ItemList.dwarfscrolli = new DwarfScrollI(),
                    ItemList.dwarfscrollii = new DwarfScrollII(),
                    ItemList.dwarfscrolliii = new DwarfScrollIII(),
                    ItemList.dwarfscrolliv = new DwarfScrollIV(),
                    ItemList.chippedamphora = new ChippedAmphora(),
                    ItemList.arrowhead = new ArrowHead(),
                    ItemList.ancientdoll = new AncientDoll(),
                    ItemList.elvishjewelry = new ElvishJewelry(),
                    ItemList.chewingstick = new ChewingStick(),
                    ItemList.ornamentalfan = new OrnamentalFan(),
                    ItemList.dinosauregg = new DinosaurEgg(),
                    ItemList.raredisc = new RareDisc(),
                    ItemList.ancientsword = new AncientSword(),
                    ItemList.rustyspoon = new RustySpoon(),
                    ItemList.rustyspur = new RustySpur(),
                    ItemList.rustycog = new RustyCog(),
                    ItemList.chickenstatue = new ChickenStatue(),
                    ItemList.ancientseed = new AncientSeed(),
                    ItemList.prehistorictool = new PrehistoricTool(),
                    ItemList.driedstarfish = new DriedStarfish(),
                    ItemList.anchor = new Anchor(),
                    ItemList.glassshards = new GlassShards(),
                    ItemList.boneflute = new BoneFlute(),
                    ItemList.prehistorichandaxe = new PrehistoricHandaxe(),
                    ItemList.dwarvishhelm = new DwarvishHelm(),
                    ItemList.dwarfgadget = new DwarfGadget(),
                    ItemList.ancientdrum = new AncientDrum(),
                    ItemList.goldenmask = new GoldenMask(),
                    ItemList.goldenrelic = new GoldenRelic(),
                    ItemList.strangedoll = new StrangeDoll(),
                    ItemList.strangedollpink = new StrangeDollPink(),
                    ItemList.prehistoricscapula = new PrehistoricScapula(),
                    ItemList.prehistorictibia = new PrehistoricTibia(),
                    ItemList.prehistoricskull = new PrehistoricSkull(),
                    ItemList.skeletalhand = new SkeletalHand(),
                    ItemList.prehistoricrib = new PrehistoricRib(),
                    ItemList.prehistoricvertebra = new PrehistoricVertebra(),
                    ItemList.skeletaltail = new SkeletalTail(),
                    ItemList.nautilusfossil = new NautilusFossil(),
                    ItemList.amphibianfossil = new AmphibianFossil(),
                    ItemList.palmfossil = new PalmFossil(),
                    ItemList.trilobite = new Trilobite(),
                    ItemList.svemerald = new SVEmerald(),
                    ItemList.svdiamond = new SVDiamond(),
                    ItemList.aquamarine = new Aquamarine(),
                    ItemList.ruby = new Ruby(),
                    ItemList.amethyst = new Amethyst(),
                    ItemList.topaz = new Topaz(),
                    ItemList.jade = new Jade(),
                    ItemList.prismaticshard = new PrismaticShard(),
                    ItemList.geode = new Geode(),
                    ItemList.frozengeode = new FrozenGeode(),
                    ItemList.magmageode = new MagmaGeode(),
                    ItemList.omnigeode = new Omnigeode(),
                    ItemList.ironaxe = new IronAxe(),
                    ItemList.ironhoe = new IronHoe(),
                    ItemList.ironpickaxe = new IronPickaxe(),
                    ItemList.ironingot = new IronIngot(),
                    ItemList.greenbean = new GreenBean()
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
