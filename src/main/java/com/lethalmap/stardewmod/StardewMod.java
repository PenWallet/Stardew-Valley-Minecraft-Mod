package com.lethalmap.stardewmod;

import com.lethalmap.stardewmod.client.renders.EntitiesRegistry;
import com.lethalmap.stardewmod.common.EntitiesList;
import com.lethalmap.stardewmod.common.blocks.*;
import com.lethalmap.stardewmod.common.blocks.AmethystOre;
import com.lethalmap.stardewmod.common.blocks.AquamarineOre;
import com.lethalmap.stardewmod.common.blocks.CopperOre;
import com.lethalmap.stardewmod.common.blocks.EmeraldOre;
import com.lethalmap.stardewmod.common.blocks.GoldOre;
import com.lethalmap.stardewmod.common.blocks.IridiumOre;
import com.lethalmap.stardewmod.common.blocks.IronOre;
import com.lethalmap.stardewmod.common.blocks.Worms;
import com.lethalmap.stardewmod.common.capabilities.currency.CurrencyCapability;
import com.lethalmap.stardewmod.common.capabilities.currency.ICurrency;
import com.lethalmap.stardewmod.common.config.Config;
import com.lethalmap.stardewmod.common.furnace.*;
import com.lethalmap.stardewmod.common.gui.CustomInventoryScreen;
import com.lethalmap.stardewmod.common.items.*;
import com.lethalmap.stardewmod.common.items.artifacts.*;
import com.lethalmap.stardewmod.common.items.dagger.CarvingKnife;
import com.lethalmap.stardewmod.common.items.foods.*;
import com.lethalmap.stardewmod.common.items.gems.*;
import com.lethalmap.stardewmod.common.items.ores.*;
import com.lethalmap.stardewmod.common.items.swords.*;
import com.lethalmap.stardewmod.common.items.armors.CombatBoots;
import com.lethalmap.stardewmod.common.items.tools.*;
import com.lethalmap.stardewmod.common.networking.C2SCurrencyPacket;
import com.lethalmap.stardewmod.common.networking.S2CCurrencyPacket;
import com.lethalmap.stardewmod.common.tiles.TileEntityList;
import com.lethalmap.stardewmod.common.world.OreGeneration;
import com.lethalmap.stardewmod.init.ModContainerTypes;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.kinds.Const;
import net.minecraft.block.Block;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;


@Mod(Constants.MODID)
public class StardewMod {
    public static StardewMod instance;
    private static int networkID = 0;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Constants.MODID, Constants.CURRENCYCHANNEL),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static final ResourceLocation CUSTOM_INVENTORY = new ResourceLocation(Constants.MODID, "textures/gui/container/custominventory.png");

    public StardewMod() {
        instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(ContainerType.class, ModContainerTypes::registerContainerTypes);

        //Loads configs
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);
        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("stardewmod-client.toml").toString());
        Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("stardewmod-server.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        //Register Networking packets
        CHANNEL.registerMessage(
                networkID++,
                C2SCurrencyPacket.class,
                C2SCurrencyPacket::encode,
                C2SCurrencyPacket::decode,
                C2SCurrencyPacket::handle
        );
        CHANNEL.registerMessage(
                networkID++,
                S2CCurrencyPacket.class,
                S2CCurrencyPacket::encode,
                S2CCurrencyPacket::decode,
                S2CCurrencyPacket::handle
        );
    }

    //It's also PreInit
    private void setup(final FMLCommonSetupEvent event) {
        //Ores are spawned in world
        OreGeneration.setupOreGeneration();

        //Register the Currency capability (users' money)
        CurrencyCapability.register();
    }


    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BlockList.garlic, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.bluejazz, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.cauliflower, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.parsnip, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.worms, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.beanstarter, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.coffeebean, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.kale, RenderType.func_228643_e_());
        RenderTypeLookup.setRenderLayer(BlockList.rhubarb, RenderType.func_228643_e_());
        ModContainerTypes.registerScreens(event);
        EntitiesRegistry.registryEntityRenders();
        ScreenManager.registerFactory(TileEntityList.furnaceContainer, ContainerScreenFurnace::new);
    }

    //Event used to attach the currency to all players
    @SubscribeEvent
    public void attachCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof PlayerEntity)
            event.addCapability(new ResourceLocation(Constants.MODID, Constants.CURRENCY), new CurrencyCapability());
    }

    //This changes the vanilla InventoryScreen for our own CustomInventoryScreen
    @SubscribeEvent
    public void changeInventoryGUI(GuiOpenEvent event)
    {
        if(event.getGui() instanceof InventoryScreen){}
            //event.setGui(new CustomInventoryScreen(Minecraft.getInstance().player));
    }

    //Event to add the string to the screen
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void guiScreenEvent(GuiScreenEvent.DrawScreenEvent.Post event)
    {
        if(!(event.getGui() instanceof InventoryScreen)) return;

        InventoryScreen screen = (InventoryScreen)event.getGui();
        screen.getMinecraft().fontRenderer.drawString(CurrencyCapability.getAmountFromPlayer(Minecraft.getInstance().player)+"g", screen.getGuiLeft() + 110, screen.height / 2 - 13, 0xffae00);

        for(Object object : screen.children())
        {
            if(object instanceof RecipeBookGui)
                ((RecipeBookGui)object).updateScreenPosition(screen.width < 379, screen.getGuiLeft() + 151, screen.height / 2 - 79);
            else if(object instanceof ImageButton)
                ((ImageButton)object).setPosition(screen.getGuiLeft() + 151, screen.height / 2 - 79);
        }

        //drawCurrencySlot(screen);
    }

    private void drawCurrencySlot(InventoryScreen screen)
    {
        screen.getMinecraft().textureManager.bindTexture(new ResourceLocation(Constants.MODID, "textures/gui/container/currencyslot.png"));
        screen.blit(screen.getGuiLeft() + 100, screen.height / 2, 0, 0, 144, 11);
    }

    //Event to change the background
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void guiBackground(GuiScreenEvent.InitGuiEvent.Post event)
    {
        if(!(event.getGui() instanceof InventoryScreen)) return;

        InventoryScreen screen = (InventoryScreen)event.getGui();
        screen.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Constants.MODID, "textures/gui/container/currencyslot.png"));
        screen.blit(screen.height/2, screen.width/2, 0 , 0, 72, 11);
    }

    //Event used to clone the current currency to the new one. If the event is not present, then it will start anew
    @SubscribeEvent
    public void playerDeath(PlayerEvent.Clone event)
    {
        ICurrency currencyOld = event.getOriginal().getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);
        ICurrency currencyNew = event.getEntity().getCapability(CurrencyCapability.CURRENCY_CAPABILITY).orElseThrow(IllegalStateException::new);

        currencyNew.setAmount(currencyOld.getAmount());
    }

    //Event currently used only to update the user's currency information
    @SubscribeEvent
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
        int amount = CurrencyCapability.getAmountFromPlayer(player);
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new S2CCurrencyPacket(amount));
    }

    //Event currently used only to update the user's currency information
    @SubscribeEvent
    public void playerRespawned(PlayerEvent.PlayerRespawnEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
        int amount = CurrencyCapability.getAmountFromPlayer(player);
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new S2CCurrencyPacket(amount));
    }

    //Event currently used only to update the user's currency information
    @SubscribeEvent
    public void playerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
        int amount = CurrencyCapability.getAmountFromPlayer(player);
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new S2CCurrencyPacket(amount));
    }

    //Loads all loots to vanilla entities, chests, fishing, etc.
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

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {


        //Register blocks
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().registerAll(
                    BlockList.copperore = new CopperOre(),
                    BlockList.aquamarineore = new AquamarineOre(),
                    BlockList.emeraldore = new EmeraldOre(),
                    BlockList.goldore = new GoldOre(),
                    BlockList.amethystore = new AmethystOre(),
                    BlockList.iridiumore = new IridiumOre(),
                    BlockList.garlic = new GarlicBlock(),
                    BlockList.bluejazz = new BlueJazzBlock(),
                    BlockList.cauliflower = new CauliflowerBlock(),
                    BlockList.worms = new Worms(),
                    BlockList.ironore = new IronOre(),
                    BlockList.parsnip = new ParsnipBlock(),
                    BlockList.beanstarter = new BeanStarterBlock(),
                    BlockList.coffeebean = new CoffeeBeanBlock(),
                    BlockList.kale = new KaleBlock(),
                    BlockList.rhubarb = new RhubarbBlock(),
                    BlockList.blockfurnace = new BlockInventoryFurnace()

            );
        }

        //Register items
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            itemRegistryEvent.getRegistry().registerAll(
                    ItemList.copperingot = new CopperIngot(),
                    ItemList.blockfurnaceitem = new FurnaceBlockItem(),
                    ItemList.copperore = new com.lethalmap.stardewmod.common.items.ores.CopperOre(),
                    ItemList.amethystore = new com.lethalmap.stardewmod.common.items.ores.AmethystOre(),
                    ItemList.aquamarineore = new com.lethalmap.stardewmod.common.items.ores.AquamarineOre(),
                    ItemList.emeraldore = new com.lethalmap.stardewmod.common.items.ores.EmeraldOre(),
                    ItemList.ironore = new com.lethalmap.stardewmod.common.items.ores.IronOre(),
                    ItemList.goldore = new com.lethalmap.stardewmod.common.items.ores.GoldOre(),
                    ItemList.iridiumore = new com.lethalmap.stardewmod.common.items.ores.IridiumOre(),
                    ItemList.icon = new Item(new Item.Properties()).setRegistryName(new ResourceLocation(Constants.MODID, Constants.ICON)),
                    ItemList.gunther = new Item(new Item.Properties()).setRegistryName(new ResourceLocation(Constants.MODID, Constants.GUNTHER)),
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
                    ItemList.greenbean = new GreenBean(),
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
                    ItemList.goldaxe = new GoldAxe(),
                    ItemList.goldhoe = new GoldHoe(),
                    ItemList.goldpickaxe = new GoldPickaxe(),
                    ItemList.iridiumaxe = new IridiumAxe(),
                    ItemList.iridiumhoe = new IridiumHoe(),
                    ItemList.iridiumpickaxe = new IridiumPickaxe(),
                    ItemList.iridiumingot = new IridiumIngot(),
                    ItemList.goldingot = new GoldIngot(),
                    ItemList.coffeebean = new CoffeeBean(),
                    ItemList.kaleseeds = new KaleSeeds(),
                    ItemList.kale = new Kale(),
                    ItemList.rhubarb = new Rhubarb(),
                    ItemList.rhubarbseeds = new RhubarbSeeds(),
                    ItemList.backpack = new Backpack(),
                    ItemList.starteraxe = new StarterAxe(),
                    ItemList.starterpickaxe = new StarterPickaxe(),
                    ItemList.starterhoe = new StarterHoe()
            );

            EntitiesList.registerEntitySpawnEggs(itemRegistryEvent);
        }

        //Register entities
        @SubscribeEvent
        public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> entityRegistryEvent) {
            entityRegistryEvent.getRegistry().registerAll(
                    EntitiesList.IRIDIUM_BAT
            );

            EntitiesList.registerEntityWorldSpawns();
        }

        @SubscribeEvent
        public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
            TileEntityList.furnaceTile = TileEntityType.Builder.create(TileEntityFurnace::new, BlockList.blockfurnace)
                    .build(null);
            // you probably don't need a datafixer --> null should be fine
            TileEntityList.furnaceTile.setRegistryName(Constants.MODID, Constants.FURNACETILEENTITY);
            event.getRegistry().register(TileEntityList.furnaceTile);
        }

        @SubscribeEvent
        public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event)
        {
            TileEntityList.furnaceContainer = IForgeContainerType.create(ContainerFurnace::createContainerClientSide);
            TileEntityList.furnaceContainer.setRegistryName(Constants.MODID, Constants.FURNACECONTAINER);
            event.getRegistry().register(TileEntityList.furnaceContainer);
        }
    }
}
