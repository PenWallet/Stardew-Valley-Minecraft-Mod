package com.lethalmap.stardewmod.common.world;

import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.config.OregenConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration
{
    public static void setupOreGeneration()
    {
        if(OregenConfig.copperore_spawn.get())
        {
            for(Biome biome : ForgeRegistries.BIOMES)
            {
                biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.copperore.getDefaultState(), OregenConfig.copperore_veinsize.get())).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(OregenConfig.copperore_chance.get(), 20, 0, 100))));
            }
        }
    }
}
