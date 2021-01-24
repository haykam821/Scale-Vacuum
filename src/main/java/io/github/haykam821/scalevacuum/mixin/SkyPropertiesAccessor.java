package io.github.haykam821.scalevacuum.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.Identifier;

@Mixin(SkyProperties.class)
public interface SkyPropertiesAccessor {
	@Accessor("BY_IDENTIFIER")
	public static Object2ObjectMap<Identifier, SkyProperties> getPropertiesMap() {
		throw new UnsupportedOperationException("Accessor");
	}
}