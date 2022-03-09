package cobbled_paths;

import cobbled_paths.item.DurabilityTransformItem;
import cobbled_paths.item.SettItem;
import cobbled_paths.item.SpadeItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.*;

public class CobbledPathsItems {
    public static Map<Block, Block> hammerTransforms = new HashMap<>();
    public static Map<Block, Item> spadeRemoval = new HashMap<>();
    public static Map<Item, Block> spadePlacement = new HashMap<>();
    public static List<Block> SOUL_SAND_LIKE = Arrays.asList(Blocks.SOUL_SOIL, Blocks.SOUL_SAND);
    public static ArrayList<Block> pathLike = new ArrayList<>(Arrays.asList(Blocks.SOUL_SOIL, Blocks.SOUL_SAND, Blocks.DIRT_PATH, Blocks.SNOW_BLOCK));

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
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.NETHER_BRICK_PATH.get(), SOUL_SAND_LIKE));
    public static final RegistrySupplier<Item> BLACKSTONE_SETT = ITEMS.register("blackstone_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.BLACKSTONE_PATH.get(), SOUL_SAND_LIKE));
    public static final RegistrySupplier<Item> OBSIDIAN_SETT = ITEMS.register("obsidian_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.OBSIDIAN_PATH.get(), CobbledPathsBlocks.END_STONE_GRAVEL.get()));
    public static final RegistrySupplier<Item> PURPUR_SETT = ITEMS.register("purpur_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.PURPUR_PATH.get(), CobbledPathsBlocks.END_STONE_GRAVEL.get()));

    //hammer
    public static final RegistrySupplier<Item> SLEDGEHAMMER = ITEMS.register("sledgehammer", () ->
            new DurabilityTransformItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1).defaultDurability(512), hammerTransforms, Items.COPPER_INGOT));

    //spade

    public static final RegistrySupplier<Item> SPADE = ITEMS.register("spade", () ->
            new SpadeItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1).defaultDurability(512), spadePlacement, spadeRemoval));

    //other
    public static final RegistrySupplier<Item> MOSS_BALL = ITEMS.register("moss_ball", () ->
            new Item(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistrySupplier<Item> END_STONE_GRAVEL_ITEM = ITEMS.register("end_stone_gravel", () ->
            new BlockItem(CobbledPathsBlocks.END_STONE_GRAVEL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static void init() {
        ITEMS.register();
    }

    public static void initTransforms() {
        pathLike.add(CobbledPathsBlocks.END_STONE_GRAVEL.get());
        hammerTransforms.put(Blocks.END_STONE, CobbledPathsBlocks.END_STONE_GRAVEL.get());
        hammerTransforms.put(CobbledPathsBlocks.STONE_PATH.get(), CobbledPathsBlocks.CRACKED_STONE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.DEEPSLATE_PATH.get(), CobbledPathsBlocks.CRACKED_DEEPSLATE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.ICE_PATH.get(), CobbledPathsBlocks.CRACKED_ICE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.BLUE_ICE_PATH.get(), CobbledPathsBlocks.CRACKED_BLUE_ICE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.NETHER_BRICK_PATH.get(), CobbledPathsBlocks.CRACKED_NETHER_BRICK_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.BLACKSTONE_PATH.get(), CobbledPathsBlocks.CRACKED_BLACKSTONE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.OBSIDIAN_PATH.get(), CobbledPathsBlocks.CRACKED_OBSIDIAN_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.PURPUR_PATH.get(), CobbledPathsBlocks.CRACKED_PURPUR_PATH.get());
        spadePlacement.put(Items.DIRT, Blocks.DIRT_PATH);
        spadePlacement.put(Items.SOUL_SAND, Blocks.SOUL_SAND);
        spadePlacement.put(END_STONE_GRAVEL_ITEM.get(), CobbledPathsBlocks.END_STONE_GRAVEL.get());
        spadePlacement.put(Items.SNOW_BLOCK, Blocks.SNOW_BLOCK);
        spadeRemoval.put(Blocks.DIRT_PATH, Items.DIRT);
        spadeRemoval.put(Blocks.SOUL_SAND, Items.SOUL_SAND);
        spadeRemoval.put(Blocks.SOUL_SOIL, Items.SOUL_SOIL);
        spadeRemoval.put(CobbledPathsBlocks.END_STONE_GRAVEL.get(), END_STONE_GRAVEL_ITEM.get());
        spadeRemoval.put(Blocks.SNOW_BLOCK, Items.SNOW_BLOCK);
    }
}
