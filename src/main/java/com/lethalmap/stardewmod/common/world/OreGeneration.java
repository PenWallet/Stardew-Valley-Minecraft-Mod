package com.lethalmap.stardewmod.common.world;

import com.lethalmap.stardewmod.common.blocks.BlockList;
import com.lethalmap.stardewmod.common.config.OregenConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
    public static final BlockClusterFeatureConfig prueba = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).func_227407_a_(BlockList.worms.getDefaultState(), 2), new SimpleBlockPlacer())).func_227315_a_(64).func_227322_d_();

    public static void setupOreGeneration() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.copperore.getDefaultState(), 5)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 50))));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.amethystore.getDefaultState(), 5)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 55))));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.aquamarineore.getDefaultState(), 5)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 55))));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.ironore.getDefaultState(), 5)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 35))));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.goldore.getDefaultState(), 5)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 25))));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockList.iridiumore.getDefaultState(), 5)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 15))));
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.field_227247_y_.func_225566_b_(prueba).func_227228_a_(Placement.COUNT_HEIGHTMAP_32.func_227446_a_(new FrequencyConfig(1))));
        }
    }
}
