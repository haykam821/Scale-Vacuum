package io.github.haykam821.scalevacuum;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0x9E5EFF;
		}, Main.ENDER_PURIFIER);
	}
}