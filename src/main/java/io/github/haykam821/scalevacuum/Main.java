package io.github.haykam821.scalevacuum;

import java.util.List;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import io.github.haykam821.scalevacuum.item.ScaleVacuumItems;
import io.github.haykam821.scalevacuum.world.ScaleVacuumBiomes;
import io.github.haykam821.scalevacuum.world.gen.ScaleChunkGenerator;
import io.github.haykam821.scalevacuum.world.gen.ScalePlatformFeature;
import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;

public class Main implements ModInitializer {
	public static final String MOD_ID = "scalevacuum";
	public static final Identifier SCALE_VACUUM_ID = new Identifier(MOD_ID, "scale_vacuum");

	// Dimension
	private static final Identifier SCALE_PLATFORM_ID = new Identifier(MOD_ID, "scale_platform");
	private static final Feature<DefaultFeatureConfig> SCALE_PLATFORM = new ScalePlatformFeature(DefaultFeatureConfig.CODEC);
	private static final RegistryEntry<ConfiguredFeature<?, ?>> CONFIGURED_SCALE_PLATFORM = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, SCALE_PLATFORM_ID, new ConfiguredFeature<>(SCALE_PLATFORM, FeatureConfig.DEFAULT));
	public static final RegistryEntry<PlacedFeature> PLACED_SCALE_PLATFORM = BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, SCALE_PLATFORM_ID, new PlacedFeature(CONFIGURED_SCALE_PLATFORM, List.of(BiomePlacementModifier.of())));

	// Sound events
	private static final Identifier BLOCK_DRAGON_EGG_SHEAR_ID = new Identifier(MOD_ID, "block.dragon_egg.shear");
	public static final SoundEvent BLOCK_DRAGON_EGG_SHEAR = new SoundEvent(BLOCK_DRAGON_EGG_SHEAR_ID);

	@Override
	public void onInitialize() {
		ScaleVacuumItems.register();
		ScaleVacuumBlocks.register();
		ScaleVacuumBiomes.register();

		// Dimension
		Registry.register(Registry.FEATURE, SCALE_PLATFORM_ID, SCALE_PLATFORM);
		Registry.register(Registry.CHUNK_GENERATOR, SCALE_VACUUM_ID, ScaleChunkGenerator.CODEC);
		
		// Sound events
		Registry.register(Registry.SOUND_EVENT, BLOCK_DRAGON_EGG_SHEAR_ID, BLOCK_DRAGON_EGG_SHEAR);
	}
}