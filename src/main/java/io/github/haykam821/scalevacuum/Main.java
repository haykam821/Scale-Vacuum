package io.github.haykam821.scalevacuum;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

public class Main implements ModInitializer {
	public static final Biome SCALE_VACUUM_VOID = new ScaleVacuumVoidBiome();
	public static FabricDimensionType SCALE_VACUUM = FabricDimensionType.builder()
			.defaultPlacer((oldEntity, destinationWorld, portalDir, horizontalOffset, verticalOffset) -> {
				return new BlockPattern.TeleportTarget(
						new Vec3d(destinationWorld.getTopPosition(
							Heightmap.Type.WORLD_SURFACE,
							BlockPos.ORIGIN
						)).multiply(0.1),
						oldEntity.getVelocity(),
						(int) oldEntity.yaw
				);
			})
			.factory(ScaleVacuum::new)
			.buildAndRegister(new Identifier("scalevacuum", "scale_vacuum"));

	@Override
	public void onInitialize() {
		Registry.register(Registry.BIOME, new Identifier("scalevacuum", "scale_vacuum_void"), SCALE_VACUUM_VOID);
	}
}