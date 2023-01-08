package net.fluffybumblebee.better_meadows.world.feature;

import net.fluffybumblebee.better_meadows.BetterMeadows;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

import static net.minecraft.world.gen.feature.PlacedFeatures.createCountExtraModifier;
import static net.minecraft.world.gen.feature.PlacedFeatures.register;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiers;
import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.modifiersWithWouldSurvive;

public class BMPlaced {
    private static RegistryEntry<PlacedFeature> registerToMod(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            List<PlacementModifier> modifiers
    ) {
        return register(BetterMeadows.getNamespaceAppendable() +  id, registryEntry, modifiers);
    }

    @SuppressWarnings("unused")
    private static RegistryEntry<PlacedFeature> registerToMod(
            String id,
            RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry,
            PlacementModifier... modifiers
    ) {
        return registerToMod(id, registryEntry, List.of(modifiers));
    }

    public static final RegistryEntry<PlacedFeature> MEADOW_SPRUCE = registerToMod(
            "meadow_spruce",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.25f, 2))
    );

    public static final RegistryEntry<PlacedFeature> MEADOW_SPRUCE_SPARSE = registerToMod(
            "meadow_spruce_sparse",
            VegetationConfiguredFeatures.TREES_TAIGA,
            modifiers(createCountExtraModifier(0, 0.5f, 1))
    );

    public static final RegistryEntry<PlacedFeature> MEADOW_BUSH = registerToMod(
            "meadow_bush",
            BMConfigured.MEADOW_BUSH,
            modifiersWithWouldSurvive(createCountExtraModifier(1, 0.5f, 2), Blocks.OAK_SAPLING)
    );


    public static final RegistryEntry<PlacedFeature> MEADOW_LAKE = registerToMod(
            "meadow_lake",
            BMConfigured.MEADOW_LAKE,
            modifiers(createCountExtraModifier(0, 0.1f, 20))
    );

    public static void addLargeMeadowFeatures(GenerationSettings.Builder generationBuilder) {
        generationBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, MEADOW_LAKE);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_SPRUCE);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_SPRUCE_SPARSE);
        generationBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MEADOW_BUSH);
    }
}
