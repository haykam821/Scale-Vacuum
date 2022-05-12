package io.github.haykam821.scalevacuum.world;

import java.util.Optional;

import io.github.haykam821.scalevacuum.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public final class ScaleVacuumDimensions {
	public static final RegistryKey<World> KEY = RegistryKey.of(Registry.WORLD_KEY, Main.SCALE_VACUUM_ID);

	private static final Vec3d ENTRY_POS = new Vec3d(8, 65, 8);

	public static TeleportTarget getEntryTarget(Entity entity) {
		return new TeleportTarget(ENTRY_POS, Vec3d.ZERO, entity.getYaw(), 0);
	}

	private static BlockPos getExactExitPos(ServerWorld destination, Entity entity) {
		if (entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			BlockPos exactSpawnPos = player.getSpawnPointPosition();
			if (exactSpawnPos != null) {
				Optional<Vec3d> spawnPos = PlayerEntity.findRespawnPosition(destination, exactSpawnPos, player.getSpawnAngle(), true, false);
				if (spawnPos.isPresent()) {
					return new BlockPos(spawnPos.get());
				}
			}
		}

		return destination.getSpawnPos();
	}

	public static TeleportTarget getExitTarget(ServerWorld destination, Entity entity) {
		BlockPos topPos = destination.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScaleVacuumDimensions.getExactExitPos(destination, entity));
		return new TeleportTarget(Vec3d.of(topPos), Vec3d.ZERO, entity.getYaw(), 0);
	}

	public static boolean isScaleVacuum(World world) {
		return world.getRegistryKey() == ScaleVacuumDimensions.KEY;
	}
}
