package io.github.haykam821.scalevacuum.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class ScaleVacuumDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricTagProvider.BlockTagProvider blockTags = new ScaleVacuumBlockTagProvider(dataGenerator);
		dataGenerator.addProvider(blockTags);

		dataGenerator.addProvider(new ScaleVacuumItemTagProvider(dataGenerator, blockTags));
	}
}