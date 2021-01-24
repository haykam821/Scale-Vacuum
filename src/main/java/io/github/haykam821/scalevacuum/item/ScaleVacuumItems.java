package io.github.haykam821.scalevacuum.item;

import io.github.haykam821.scalevacuum.Main;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public enum ScaleVacuumItems implements ItemConvertible{
	ANCIENT_CHORUS_FRUIT("ancient_chorus_fruit", new AncientChorusFruitItem(new Item.Settings().group(ItemGroup.MATERIALS).food(FoodComponents.CHORUS_FRUIT))),
	DRAGON_SCALE("dragon_scale", new Item(new Item.Settings().rarity(Rarity.RARE).group(ItemGroup.MATERIALS))),
	ENDER_PURIFIER("ender_purifier", new PurifierItem(new Item.Settings().rarity(Rarity.UNCOMMON).group(ItemGroup.MISC).maxCount(1)));

	private final Identifier id;
	private final Item item;
	
	private ScaleVacuumItems(String path, Item item) {
		this.id = new Identifier(Main.MOD_ID, path);
		this.item = item;
	}

	@Override
	public Item asItem() {
		return this.item;
	}

	public static void register() {
		for (ScaleVacuumItems item : ScaleVacuumItems.values()) {
			Registry.register(Registry.ITEM, item.id, item.item);
		}
	}
}
