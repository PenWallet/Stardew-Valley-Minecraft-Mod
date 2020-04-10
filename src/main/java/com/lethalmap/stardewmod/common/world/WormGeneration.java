package com.lethalmap.stardewmod.common.world;


import com.lethalmap.stardewmod.common.blocks.BlockList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class WormGeneration {
    public static final BlockClusterFeatureConfig worms = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(BlockList.worms.getDefaultState(), 2), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();
    public static void WormGeneration(){
        for(Biome biome : ForgeRegistries.BIOMES)
        {
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227247_y_.func_225566_b_(worms).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(1))));
        }
    }
}
