package io.github.haykam821.scalevacuum.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.haykam821.scalevacuum.ScaleVacuum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	abstract float getMaximumHealth();

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "getMaximumHealth", at = @At("RETURN"), cancellable = true, remap = false)
	void getMaximumHealthInScaleVacuum(CallbackInfoReturnable<Float> ci) {
		if ((Object) this instanceof PlayerEntity && this.world.dimension instanceof ScaleVacuum && !this.world.isClient) {
			int purifiedLevel = ((ScaleVacuum) this.world.dimension).getPurifiedLevel();

			float originalHealth = ci.getReturnValue();
			float unbreathableHealth = (float) Math.ceil((float) (4 - purifiedLevel) / 5 * 20);
			float breathableHealth = originalHealth - unbreathableHealth;

			ci.setReturnValue(breathableHealth);
		}
	}
}