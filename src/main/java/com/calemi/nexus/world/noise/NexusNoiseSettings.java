package com.calemi.nexus.world.noise;

import com.calemi.nexus.block.NexusBlocks;
import com.calemi.nexus.main.NexusRef;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class NexusNoiseSettings {

    public static final ResourceKey<NoiseGeneratorSettings> NEXUS = NexusRef.createKey("the_nexus", Registries.NOISE_SETTINGS);

    public static void init(BootstrapContext<NoiseGeneratorSettings> context) {

        HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> noise = context.lookup(Registries.NOISE);

        context.register(NEXUS, nexusNoiseSettings(densityFunctions, noise));
    }

    private static NoiseGeneratorSettings nexusNoiseSettings(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise) {

        return new NoiseGeneratorSettings(
                new NoiseSettings(0, 80, 1, 1),
                NexusBlocks.WARPSLATE.get().defaultBlockState(), // Default block
                Blocks.WATER.defaultBlockState(), //Default fluid
                createNoiseRouter(densityFunctions, noise, createFinalDensity(densityFunctions, noise)), // Noise router
                createSurfaceRules(), // Surface rules
                List.of(), // Spawn target
                63,       // Sea level
                true,      // Disable mob generation
                false,     // Enable aquifers
                false,     // Enable ore veins
                false      // Use legacy random source
        );
    }

    private static NoiseRouter createNoiseRouter(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noise, DensityFunction finalDensity) {
        DensityFunction depthFunction = getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("overworld/depth")));
        DensityFunction erosionFunction = getFunction(densityFunctions, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.withDefaultNamespace("overworld/erosion")));
        return new NoiseRouter(
                DensityFunctions.zero(),  // Barrier noise
                DensityFunctions.zero(),  // Fluid level floodedness noise
                DensityFunctions.zero(),  // Fluid level spread noise
                DensityFunctions.zero(),  // Lava noise
                DensityFunctions.zero(),  // Temperature
                DensityFunctions.zero(),  // Vegetation
                DensityFunctions.zero(),  // Continentalness noise
                erosionFunction,          // Erosion noise
                depthFunction,            // Depth
                DensityFunctions.zero(),  // Ridges
                DensityFunctions.zero(),  // Initial density without jaggedness, not sure if this is needed. Some vanilla dimensions use this while others don't.
                finalDensity,             // Final density
                DensityFunctions.zero(),  // Vein Toggle
                DensityFunctions.zero(),  // Vein Ridged
                DensityFunctions.zero()); // Vein Gap
    }

    /*
         DENSITY
     */


    private static DensityFunction createFinalDensity(HolderGetter<DensityFunction> densityFunctions, HolderGetter<NormalNoise.NoiseParameters> noiseParameters) {
        DensityFunction flatDensity = DensityFunctions.yClampedGradient(55, 80, 1, -1);
        DensityFunction noiseDensity = DensityFunctions.noise(noiseParameters.getOrThrow(Noises.SURFACE), 1, 0);
        return DensityFunctions.add(flatDensity, noiseDensity);
    }

    private static DensityFunction getFunction(HolderGetter<DensityFunction> densityFunctions, ResourceKey<DensityFunction> key) {
        return new DensityFunctions.HolderHolder(densityFunctions.getOrThrow(key));
    }

    /*
        SURFACE RULES
     */

    private static SurfaceRules.RuleSource createSurfaceRules() {

        // BEDROCK FLOOR
        SurfaceRules.RuleSource floorBedrock = createBedrockLayerRuleSource(Blocks.BEDROCK, 0, 5);

        // SEA LEVEL
        SurfaceRules.RuleSource transitionSeaDirt = createBedrockLayerRuleSource(NexusBlocks.CHRONOWARPED_DIRT.get(), 57, 63);

        SurfaceRules.ConditionSource seaFloorDirtCondition = SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(57), 1));
        SurfaceRules.RuleSource seaFloorDirtResult = SurfaceRules.state(NexusBlocks.CHRONOWARPED_DIRT.get().defaultBlockState());
        SurfaceRules.RuleSource seaFloorDirt = SurfaceRules.ifTrue(seaFloorDirtCondition, seaFloorDirtResult);

        SurfaceRules.RuleSource transitionSeaSand = createBedrockLayerRuleSource(NexusBlocks.CHRONOWARPED_SAND.get(), 62, 65);

        SurfaceRules.ConditionSource seaCondition = SurfaceRules.stoneDepthCheck(0, false, 5, CaveSurface.FLOOR);
        SurfaceRules.RuleSource seaResult = SurfaceRules.sequence(transitionSeaDirt, seaFloorDirt, transitionSeaSand);
        SurfaceRules.RuleSource sea = SurfaceRules.ifTrue(seaCondition, seaResult);

        // TOP GRASS LAYER
        SurfaceRules.RuleSource layerTopGrass = createSurfaceLayerRuleSource(NexusBlocks.CHRONOWARPED_GRASS.get(), false, 0);

        // TOP DIRT LAYER
        SurfaceRules.RuleSource layerSurfaceDirt = createSurfaceLayerRuleSource(NexusBlocks.CHRONOWARPED_DIRT.get(), true, 0);

        // TOP CALCITE LAYER
        SurfaceRules.RuleSource layerSurfaceCalcite = createSurfaceLayerRuleSource(Blocks.CALCITE, true, 10);

        return SurfaceRules.sequence(floorBedrock, sea, layerTopGrass, layerSurfaceDirt, layerSurfaceCalcite);
    }

    private static SurfaceRules.ConditionSource createBedrockLayerCondition(int absoluteMin, int absoluteMax) {
        return SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.absolute(absoluteMin), VerticalAnchor.absolute(absoluteMax));
    }

    private static SurfaceRules.RuleSource createBedrockLayerRuleSource(Block block, int absoluteMin, int absoluteMax) {
        SurfaceRules.ConditionSource condition = createBedrockLayerCondition(absoluteMin, absoluteMax);
        SurfaceRules.RuleSource result = SurfaceRules.state(block.defaultBlockState());
        return SurfaceRules.ifTrue(condition, result);
    }

    private static SurfaceRules.RuleSource createSurfaceLayerRuleSource(Block block, boolean addSurfaceDepth, int secondaryDepthRange) {
        SurfaceRules.ConditionSource condition = SurfaceRules.stoneDepthCheck(0, addSurfaceDepth, secondaryDepthRange, CaveSurface.FLOOR);
        SurfaceRules.RuleSource result = SurfaceRules.state(block.defaultBlockState());
        return SurfaceRules.ifTrue(condition, result);
    }
}
