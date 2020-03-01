package io.github.haykam821.scalevacuum;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class ScaleVacuumVoidBiome extends Biome {
	public ScaleVacuumVoidBiome() {
		super(new Biome.Settings()
			.configureSurfaceBuilder(SurfaceBuilder.NOPE, SurfaceBuilder.STONE_CONFIG)
			.precipitation(Biome.Precipitation.NONE)
			.category(Biome.Category.THEEND)
			.depth(0.01F)
			.scale(0.2F)
			.temperature(0.5F)
			.downfall(0.5F)
			.waterColor(4159204)
			.waterFogColor(329011)
			.parent((String) null)
		);

		this.addFeature(
			GenerationStep.Feature.TOP_LAYER_MODIFICATION,
			Feature.VOID_START_PLATFORM
				.configure(FeatureConfig.DEFAULT)
				.createDecoratedFeature(Decorator.NOPE.configure(DecoratorConfig.DEFAULT))
		);
	}
}
