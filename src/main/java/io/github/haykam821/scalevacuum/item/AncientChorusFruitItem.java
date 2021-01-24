package io.github.haykam821.scalevacuum.item;

import io.github.haykam821.scalevacuum.Main;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

class AncientChorusFruitItem extends Item {
	public AncientChorusFruitItem(Settings settings) {
		super(settings);
	}

	public boolean hasGlint(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
		ItemStack newStack = super.finishUsing(stack, world, entity);

		if (!world.isClient) {
			if (Main.isScaleVacuum(entity.getEntityWorld())) {
				if (!(entity instanceof ServerPlayerEntity)) return stack;
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
	
				ServerWorld respawnWorld = player.getServer().getWorld(player.getSpawnPointDimension());
				FabricDimensions.teleport(player, respawnWorld, Main.EXIT_SURFACE_PLACER);
			} else {
				// Ensure the scale vacuum world exists
				ServerWorld scaleVacuum = world.getServer().getWorld(Main.SCALE_VACUUM_KEY);
				if (scaleVacuum == null) return stack;

				FabricDimensions.teleport(entity, scaleVacuum, Main.SCALE_VACUUM_PLACER);
			}
		}

		entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 80));
		entity.setHealth(Math.min(entity.getHealth(), entity.getMaxHealth()));

		return newStack;
	}
}