package io.github.haykam821.scalevacuum;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0x9E5EFF;
		}, Main.ENDER_PURIFIER);

		BlockRenderLayerMap.INSTANCE.putBlock(ScaleVacuumBlocks.DRAGON_FIRE.getBlock(), RenderLayer.getCutout());
	}
}