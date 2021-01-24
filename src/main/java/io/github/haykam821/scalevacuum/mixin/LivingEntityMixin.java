package io.github.haykam821.scalevacuum.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.scalevacuum.component.ScaleVacuumComponents;
import io.github.haykam821.scalevacuum.world.ScaleVacuumDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "getMaxHealth", at = @At("RETURN"), cancellable = true)
	void getMaximumHealthInScaleVacuum(CallbackInfoReturnable<Float> ci) {
		if (this.world.isClient) return;
		if (!((Object) this instanceof PlayerEntity)) return;
		if (!ScaleVacuumDimensions.isScaleVacuum(this.world)) return;

		int purifiedLevel = ScaleVacuumComponents.PURIFICATION.get(this.world).getPurifiedLevel();

		float originalHealth = ci.getReturnValue();
		float unbreathableHealth = (float) Math.ceil((float) (4 - purifiedLevel) / 5 * 20);
		float breathableHealth = originalHealth - unbreathableHealth;

		ci.setReturnValue(breathableHealth);
	}
}