package cobbled_paths;

import cobbled_paths.item.SettItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

public class CobbledPathsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(CobbledPaths.MOD_ID, Registry.ITEM_REGISTRY);
    public static final RegistrySupplier<Item> COBBLE = ITEMS.register("cobble", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.COBBLED_PATH.get(), Blocks.DIRT_PATH));
    public static final RegistrySupplier<Item> STONE_SETT = ITEMS.register("stone_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.STONE_PATH.get(), Blocks.DIRT_PATH));
    public static final RegistrySupplier<Item> DEEPSLATE_SETT = ITEMS.register("deepslate_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.DEEPSLATE_PATH.get(), Blocks.DIRT_PATH));
    public static final RegistrySupplier<Item> ICE_SETT = ITEMS.register("ice_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.ICE_PATH.get(), Blocks.SNOW_BLOCK));
    public static final RegistrySupplier<Item> BLUE_ICE_SETT = ITEMS.register("blue_ice_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.BLUE_ICE_PATH.get(), Blocks.SNOW_BLOCK));

    public static void init() {
        ITEMS.register();
    }
}
