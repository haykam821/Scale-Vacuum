package io.github.haykam821.scalevacuum;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import io.github.haykam821.scalevacuum.item.ScaleVacuumItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.render.RenderLayer;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0x9E5EFF;
		}, ScaleVacuumItems.ENDER_PURIFIER);

		BlockRenderLayerMap.INSTANCE.putBlock(ScaleVacuumBlocks.DRAGON_FIRE.getBlock(), RenderLayer.getCutout());

		// Change sky to end
		DimensionRenderingRegistry.registerDimensionEffects(Main.SCALE_VACUUM_ID, new DimensionEffects.End());
	}
}