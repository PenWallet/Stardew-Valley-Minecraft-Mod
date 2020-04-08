package com.lethalmap.stardewmod.common;

import com.lethalmap.stardewmod.Constants;
import com.lethalmap.stardewmod.StardewMod;
import com.lethalmap.stardewmod.common.entity.IridiumBat;
import com.lethalmap.stardewmod.common.items.ItemList;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class EntitiesList
{
    public static EntityType<?> IRIDIUM_BAT = EntityType.Builder.create(IridiumBat::new, EntityClassification.MONSTER).build(Constants.MODID+":"+Constants.IRIDIUMBAT).setRegistryName(Constants.MODID, Constants.IRIDIUMBAT);

    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                ItemList.iridiumbat_egg = registerEntitySpawnEgg(IRIDIUM_BAT, 0x641675, 0x000000, Constants.IRIDIUMBAT_EGG)
        );
    }

    public static void registerEntityWorldSpawns()
    {
        registerEntityWorldSpawn(IRIDIUM_BAT);
    }

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
    {
        SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(Constants.SMITEMGROUP));
        item.setRegistryName(new ResourceLocation(Constants.MODID, Constants.IRIDIUMBAT_EGG));
        return item;
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, Biome... biomes)
    {
        if(biomes.length == 0)
        {
            for(Biome biome : ForgeRegistries.BIOMES)
            {
                if(biome != null)
                {
                    biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, 10, 10, 20));
                }
            }
        }
        else
        {
            for(Biome biome : biomes)
            {
                if(biome != null)
                {
                    biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, 10, 10, 20));
                }
            }
        }

    }
}
