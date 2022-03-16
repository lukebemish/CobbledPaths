package com.github.lukebemish.cobbled_paths;

import com.github.lukebemish.cobbled_paths.item.DurabilityTransformItem;
import com.github.lukebemish.cobbled_paths.item.SettItem;
import com.github.lukebemish.cobbled_paths.item.SpadeItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.*;
import java.util.function.Supplier;

public class CobbledPathsItems {
    public static Map<Block, Block> hammerTransforms = new HashMap<>();
    public static Map<Block, Item> spadeRemoval = new HashMap<>();
    public static Map<Item, Block> spadePlacement = new HashMap<>();
    public static List<Supplier<Block>> SOUL_SAND_LIKE = Arrays.asList(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_soil")), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand")));
    public static ArrayList<Supplier<Block>> pathLike = new ArrayList<>(Arrays.asList(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_soil")), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand")), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt_path")), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(CobbledPaths.MOD_ID, Registry.ITEM_REGISTRY);
    public static final RegistrySupplier<Item> COBBLE = ITEMS.register("cobble", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.COBBLED_PATH, RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt_path"))));
    public static final RegistrySupplier<Item> STONE_SETT = ITEMS.register("stone_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.STONE_PATH, RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt_path"))));
    public static final RegistrySupplier<Item> DEEPSLATE_SETT = ITEMS.register("deepslate_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.DEEPSLATE_PATH, RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt_path"))));
    public static final RegistrySupplier<Item> ICE_SETT = ITEMS.register("ice_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.ICE_PATH, RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));
    public static final RegistrySupplier<Item> BLUE_ICE_SETT = ITEMS.register("blue_ice_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.BLUE_ICE_PATH, RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));
    public static final RegistrySupplier<Item> NETHER_BRICK_SETT = ITEMS.register("nether_brick_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.NETHER_BRICK_PATH, SOUL_SAND_LIKE));
    public static final RegistrySupplier<Item> BLACKSTONE_SETT = ITEMS.register("blackstone_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.BLACKSTONE_PATH, SOUL_SAND_LIKE));
    public static final RegistrySupplier<Item> OBSIDIAN_SETT = ITEMS.register("obsidian_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.OBSIDIAN_PATH, CobbledPathsBlocks.END_STONE_GRAVEL));
    public static final RegistrySupplier<Item> PURPUR_SETT = ITEMS.register("purpur_sett", () ->
            new SettItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS), CobbledPathsBlocks.PURPUR_PATH, CobbledPathsBlocks.END_STONE_GRAVEL));

    //hammer
    public static final RegistrySupplier<Item> SLEDGEHAMMER = ITEMS.register("sledgehammer", () ->
            new DurabilityTransformItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1).defaultDurability(512), hammerTransforms, RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","copper_ingot"))));

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
        pathLike.add(CobbledPathsBlocks.END_STONE_GRAVEL);
        hammerTransforms.put(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","end_stone")).get(), CobbledPathsBlocks.END_STONE_GRAVEL.get());
        hammerTransforms.put(CobbledPathsBlocks.STONE_PATH.get(), CobbledPathsBlocks.CRACKED_STONE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.DEEPSLATE_PATH.get(), CobbledPathsBlocks.CRACKED_DEEPSLATE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.ICE_PATH.get(), CobbledPathsBlocks.CRACKED_ICE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.BLUE_ICE_PATH.get(), CobbledPathsBlocks.CRACKED_BLUE_ICE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.NETHER_BRICK_PATH.get(), CobbledPathsBlocks.CRACKED_NETHER_BRICK_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.BLACKSTONE_PATH.get(), CobbledPathsBlocks.CRACKED_BLACKSTONE_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.OBSIDIAN_PATH.get(), CobbledPathsBlocks.CRACKED_OBSIDIAN_PATH.get());
        hammerTransforms.put(CobbledPathsBlocks.PURPUR_PATH.get(), CobbledPathsBlocks.CRACKED_PURPUR_PATH.get());
        spadePlacement.put(RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","dirt")).get(), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt_path")).get());
        spadePlacement.put(RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","soul_sand")).get(), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand")).get());
        spadePlacement.put(END_STONE_GRAVEL_ITEM.get(), CobbledPathsBlocks.END_STONE_GRAVEL.get());
        spadePlacement.put(RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","snow_block")).get(), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block")).get());
        spadeRemoval.put(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt_path")).get(), RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","dirt")).get());
        spadeRemoval.put(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand")).get(), RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","soul_sand")).get());
        spadeRemoval.put(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_soil")).get(), RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","soul_soil")).get());
        spadeRemoval.put(CobbledPathsBlocks.END_STONE_GRAVEL.get(), END_STONE_GRAVEL_ITEM.get());
        spadeRemoval.put(RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block")).get(), RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","snow_block")).get());
    }
}
