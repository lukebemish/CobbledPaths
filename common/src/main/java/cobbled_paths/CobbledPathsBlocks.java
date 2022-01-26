package cobbled_paths;

import cobbled_paths.block.BetterPathBlock;
import dev.architectury.registry.block.BlockProperties;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.HashMap;
import java.util.Map;

public class CobbledPathsBlocks {
    public static Map<Item, Block> cobbledPathTransforms = new HashMap<Item, Block>();
    public static Map<Item, Block> netherBrickPathTransforms = new HashMap<Item, Block>();
    public static Map<Item, Block> blackstoneTransforms = new HashMap<Item, Block>();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(CobbledPaths.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> COBBLED_PATH = BLOCKS.register("cobbled_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.DIRT).sound(SoundType.GRASS).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.15f), Blocks.DIRT.defaultBlockState(), cobbledPathTransforms));
    public static final RegistrySupplier<Block> STONE_PATH = BLOCKS.register("stone_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).sound(SoundType.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f)));
    public static final RegistrySupplier<Block> DEEPSLATE_PATH = BLOCKS.register("deepslate_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).sound(SoundType.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.45f)));
    public static final RegistrySupplier<Block> ICE_PATH = BLOCKS.register("ice_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.35f), Blocks.SNOW_BLOCK.defaultBlockState()));
    public static final RegistrySupplier<Block> BLUE_ICE_PATH = BLOCKS.register("blue_ice_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.55f), Blocks.SNOW_BLOCK.defaultBlockState()));
    public static final RegistrySupplier<Block> NETHER_BRICK_PATH = BLOCKS.register("nether_brick_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.00f), Blocks.SOUL_SAND.defaultBlockState(), netherBrickPathTransforms));
    public static final RegistrySupplier<Block> BLACKSTONE_PATH = BLOCKS.register("blackstone_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f), Blocks.SOUL_SAND.defaultBlockState(), blackstoneTransforms));

    // cracked paths
    public static final RegistrySupplier<Block> CRACKED_STONE_PATH = BLOCKS.register("cracked_stone_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).sound(SoundType.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f)));
    public static final RegistrySupplier<Block> CRACKED_DEEPSLATE_PATH = BLOCKS.register("cracked_deepslate_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).sound(SoundType.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.45f)));
    public static final RegistrySupplier<Block> CRACKED_ICE_PATH = BLOCKS.register("cracked_ice_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.35f), Blocks.SNOW_BLOCK.defaultBlockState()));
    public static final RegistrySupplier<Block> CRACKED_BLUE_ICE_PATH = BLOCKS.register("cracked_blue_ice_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.55f), Blocks.SNOW_BLOCK.defaultBlockState()));
    public static final RegistrySupplier<Block> CRACKED_NETHER_BRICK_PATH = BLOCKS.register("cracked_nether_brick_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.00f), Blocks.SOUL_SAND.defaultBlockState()));
    public static final RegistrySupplier<Block> CRACKED_BLACKSTONE_PATH = BLOCKS.register("cracked_blackstone_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f), Blocks.SOUL_SAND.defaultBlockState()));

    //decorated
    public static final RegistrySupplier<Block> MOSSY_COBBLED_PATH = BLOCKS.register("mossy_cobbled_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.DIRT).sound(SoundType.GRASS).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.15f), Blocks.DIRT.defaultBlockState()));
    public static final RegistrySupplier<Block> RED_NETHER_BRICK_PATH = BLOCKS.register("red_nether_brick_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.00f), Blocks.SOUL_SAND.defaultBlockState()));
    public static final RegistrySupplier<Block> GILDED_BLACKSTONE_PATH = BLOCKS.register("gilded_blackstone_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f), Blocks.SOUL_SAND.defaultBlockState()));

    public static void init() {
        BLOCKS.register();
    }

    public static void initTransforms() {
        cobbledPathTransforms.put(Items.MOSS_BLOCK, MOSSY_COBBLED_PATH.get());
        netherBrickPathTransforms.put(Items.NETHER_WART, RED_NETHER_BRICK_PATH.get());
        blackstoneTransforms.put(Items.GOLD_NUGGET, GILDED_BLACKSTONE_PATH.get());
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
