package io.github.haykam821.scalevacuum.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class PurificationComponent implements AutoSyncedComponent {
	private int purifiedLevel = 0;
		
	public PurificationComponent(World world) {
		return;
	}

	@Override
	public void readFromNbt(NbtCompound nbt) {
		this.purifiedLevel = nbt.getInt("PurifiedLevel");
	}

	@Override
	public void writeToNbt(NbtCompound nbt) {
		nbt.putInt("PurifiedLevel", this.purifiedLevel);
	}

	public int getPurifiedLevel() {
		return this.purifiedLevel;
	}

	public void setPurifiedLevel(int purifiedLevel) {
		this.purifiedLevel = purifiedLevel;
	}
}