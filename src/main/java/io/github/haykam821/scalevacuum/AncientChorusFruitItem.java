package io.github.haykam821.scalevacuum;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

class AncientChorusFruitItem extends Item {
	public AncientChorusFruitItem(Settings settings) {
		super(settings);
	}

	public boolean hasEnchantmentGlint(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
		ItemStack newStack = super.finishUsing(stack, world, entity);

		entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 80));

		if (!world.isClient) {
			if (entity.dimension == Main.SCALE_VACUUM) {
				FabricDimensions.teleport(entity, DimensionType.OVERWORLD, Main.OVERWORLD_SURFACE_PLACER);
			} else {
				FabricDimensions.teleport(entity, Main.SCALE_VACUUM);
			}
		}

		return newStack;
	}
}