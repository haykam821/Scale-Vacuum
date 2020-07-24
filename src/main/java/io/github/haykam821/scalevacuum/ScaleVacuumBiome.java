package io.github.haykam821.scalevacuum;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class ScaleVacuumBiome extends Biome {
	public ScaleVacuumBiome() {
		super(new Biome.Settings()
			.configureSurfaceBuilder(SurfaceBuilder.NOPE, SurfaceBuilder.STONE_CONFIG)
			.precipitation(Biome.Precipitation.NONE)
			.category(Biome.Category.THEEND)
			.depth(0.01F)
			.scale(0.2F)
			.temperature(0.5F)
			.downfall(0.5F)
			.effects(new BiomeEffects.Builder()
				.waterColor(0x7647BE)
				.waterFogColor(0x7647BE)
				.fogColor(0x000000)
				.build())
			.parent(null)
		);

		this.addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, Main.CONFIGURED_SCALE_PLATFORM);
	}
}
