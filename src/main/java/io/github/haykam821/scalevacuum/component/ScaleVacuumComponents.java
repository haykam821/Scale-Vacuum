package io.github.haykam821.scalevacuum.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import io.github.haykam821.scalevacuum.Main;
import net.minecraft.util.Identifier;

public final class ScaleVacuumComponents implements WorldComponentInitializer {
	private static final Identifier PURIFICATION_ID = new Identifier(Main.MOD_ID, "purification");
	public static final ComponentKey<PurificationComponent> PURIFICATION = ComponentRegistryV3.INSTANCE.getOrCreate(PURIFICATION_ID, PurificationComponent.class);

	@Override
	public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
		registry.register(PURIFICATION, PurificationComponent::new);
	}
}
