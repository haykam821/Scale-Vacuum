package io.github.haykam821.scalevacuum.world;

import io.github.haykam821.scalevacuum.Main;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public final class ScaleVacuumBiomes {
	public static final Biome BIOME = ScaleVacuumBiomes.createBiome();

	private static final int WATER_COLOR = 0x7647BE;
	private static final int SKY_COLOR = 0x000000;

	private static Biome createBiome() {
		GenerationSettings generationSettings = new GenerationSettings.Builder()
			.surfaceBuilder(SurfaceBuilder.NOPE.withConfig(SurfaceBuilder.STONE_CONFIG))
			.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Main.CONFIGURED_SCALE_PLATFORM)
			.build();

		BiomeEffects effects = new BiomeEffects.Builder()
			.waterColor(WATER_COLOR)
			.waterFogColor(WATER_COLOR)
			.skyColor(SKY_COLOR)
			.fogColor(SKY_COLOR)
			.build();

		return new Biome.Builder()
			.generationSettings(generationSettings)
			.effects(effects)
			.spawnSettings(SpawnSettings.INSTANCE)
			.precipitation(Biome.Precipitation.NONE)
			.category(Biome.Category.THEEND)
			.depth(0.01f)
			.scale(0.2f)
			.temperature(0.5f)
			.downfall(0.5f)
			.build();
	}

	public static void register() {
		Registry.register(BuiltinRegistries.BIOME, Main.SCALE_VACUUM_ID, ScaleVacuumBiomes.BIOME);
	}
}
