package io.github.haykam821.scalevacuum.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ScaleVacuumDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		dataGenerator.addProvider(ScaleVacuumBlockTagProvider::new);
	}
}