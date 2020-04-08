package com.lethalmap.stardewmod.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OregenConfig
{
    public static ForgeConfigSpec.IntValue copperore_chance;
    public static ForgeConfigSpec.BooleanValue copperore_spawn;
    public static ForgeConfigSpec.IntValue copperore_veinsize;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Ore generation configuration");

        copperore_chance = server
                .comment("Max number of ore veins of copper per chunk. (Value between 5 and 10)")
                .defineInRange("oregen.copperore_chance", 5, 5, 10);

        copperore_veinsize = server
                .comment("Max size of blocks per vein of copper")
                .defineInRange("oregen.copperore_veinsize", 5, 5, 10);

        copperore_spawn = server
                .comment("Choose whether you want copper to spawn in your world or not")
                .define("oregen.copperore_spawn", true);
    }
}
