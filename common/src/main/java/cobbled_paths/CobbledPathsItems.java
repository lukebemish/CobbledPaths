package cobbled_paths;

import cobbled_paths.item.DurabilityTransformItem;
import cobbled_paths.item.SettItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class CobbledPathsItems {
    public static Map<Block, Block> hammerTransforms = new HashMap<Block, Block>();

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
    public static final RegistrySupplier<Item> NETHER_BRICK_SETT = ITEMS.register("nether_brick_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.NETHER_BRICK_PATH.get(), Blocks.SOUL_SAND));
    public static final RegistrySupplier<Item> BLACKSTONE_SETT = ITEMS.register("blockstone_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.BLACKSTONE_PATH.get(), Blocks.SOUL_SAND));

    //hammer
    public static final RegistrySupplier<Item> SLEDGEHAMMER = ITEMS.register("sledgehammer", () ->
            new DurabilityTransformItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1).defaultDurability(512), hammerTransforms, Items.COPPER_INGOT));

    public static void init() {
        hammerTransforms.put(CobbledPathsBlocks.STONE_PATH.get(), CobbledPathsBlocks.CRACKED_STONE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.DEEPSLATE_PATH.get(), CobbledPathsBlocks.CRACKED_DEEPSLATE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.ICE_PATH.get(), CobbledPathsBlocks.CRACKED_ICE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.BLUE_ICE_PATH.get(), CobbledPathsBlocks.CRACKED_BLUE_ICE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.NETHER_BRICK_PATH.get(), CobbledPathsBlocks.CRACKED_NETHER_BRICK_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.BLACKSTONE_PATH.get(), CobbledPathsBlocks.CRACKED_BLACKSTONE_PATH.get());
        ITEMS.register();
    }
}
