package io.github.haykam821.scalevacuum;

import io.github.haykam821.scalevacuum.block.ScaleVacuumBlocks;
import io.github.haykam821.scalevacuum.item.ScaleVacuumItems;
import io.github.haykam821.scalevacuum.mixin.SkyPropertiesAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.SkyProperties;

public class ClientMain implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			return 0x9E5EFF;
		}, ScaleVacuumItems.ENDER_PURIFIER);

		BlockRenderLayerMap.INSTANCE.putBlock(ScaleVacuumBlocks.DRAGON_FIRE.getBlock(), RenderLayer.getCutout());

		// Change sky to end
		SkyPropertiesAccessor.getPropertiesMap().put(Main.SCALE_VACUUM_ID, new SkyProperties.End());
	}
}