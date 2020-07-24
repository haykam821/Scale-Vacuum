package io.github.haykam821.scalevacuum.component;

import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public class PurificationComponent implements WorldSyncedComponent {
	private final World world;
	private int purifiedLevel = 0;
		
	public PurificationComponent(World world) {
		this.world = world;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		this.purifiedLevel = tag.getInt("PurifiedLevel");
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		tag.putInt("PurifiedLevel", this.purifiedLevel);
		return tag;
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	public int getPurifiedLevel() {
		return this.purifiedLevel;
	}

	public void setPurifiedLevel(int purifiedLevel) {
		this.purifiedLevel = purifiedLevel;
	}
}