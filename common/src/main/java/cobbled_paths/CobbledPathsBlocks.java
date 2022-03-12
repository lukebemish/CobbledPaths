package cobbled_paths;

import cobbled_paths.block.BetterPathBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.HashMap;
import java.util.Map;

public class CobbledPathsBlocks {
    public static Map<Item, Block> cobbledPathTransforms = new HashMap<Item, Block>();
    public static Map<Item, Block> netherBrickPathTransforms = new HashMap<Item, Block>();
    public static Map<Item, Block> blackstoneTransforms = new HashMap<Item, Block>();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(CobbledPaths.MOD_ID, Registry.BLOCK_REGISTRY);

    //not a path
    public static final RegistrySupplier<Block> END_STONE_GRAVEL = BLOCKS.register("end_stone_gravel", () ->
            new GravelBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE).strength(0.6F).sound(SoundType.GRAVEL)));

    //normal paths
    public static final RegistrySupplier<Block> COBBLED_PATH = BLOCKS.register("cobbled_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.DIRT).sound(SoundType.GRASS).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.15f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt")), cobbledPathTransforms));
    public static final RegistrySupplier<Block> STONE_PATH = BLOCKS.register("stone_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f)));
    public static final RegistrySupplier<Block> DEEPSLATE_PATH = BLOCKS.register("deepslate_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.45f)));
    public static final RegistrySupplier<Block> ICE_PATH = BLOCKS.register("ice_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.35f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));
    public static final RegistrySupplier<Block> BLUE_ICE_PATH = BLOCKS.register("blue_ice_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.55f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));
    public static final RegistrySupplier<Block> NETHER_BRICK_PATH = BLOCKS.register("nether_brick_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.10f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand")), netherBrickPathTransforms));
    public static final RegistrySupplier<Block> BLACKSTONE_PATH = BLOCKS.register("blackstone_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.50f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand")), blackstoneTransforms));
    public static final RegistrySupplier<Block> OBSIDIAN_PATH = BLOCKS.register("obsidian_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(1.00f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f), END_STONE_GRAVEL));
    public static final RegistrySupplier<Block> PURPUR_PATH = BLOCKS.register("purpur_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.50f), END_STONE_GRAVEL));

    // cracked paths
    public static final RegistrySupplier<Block> CRACKED_STONE_PATH = BLOCKS.register("cracked_stone_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f)));
    public static final RegistrySupplier<Block> CRACKED_DEEPSLATE_PATH = BLOCKS.register("cracked_deepslate_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.45f)));
    public static final RegistrySupplier<Block> CRACKED_ICE_PATH = BLOCKS.register("cracked_ice_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.35f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));
    public static final RegistrySupplier<Block> CRACKED_BLUE_ICE_PATH = BLOCKS.register("cracked_blue_ice_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.55f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","snow_block"))));
    public static final RegistrySupplier<Block> CRACKED_NETHER_BRICK_PATH = BLOCKS.register("cracked_nether_brick_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.10f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand"))));
    public static final RegistrySupplier<Block> CRACKED_BLACKSTONE_PATH = BLOCKS.register("cracked_blackstone_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.50f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand"))));
    public static final RegistrySupplier<Block> CRACKED_OBSIDIAN_PATH = BLOCKS.register("cracked_obsidian_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(1.00f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.30f), END_STONE_GRAVEL));
    public static final RegistrySupplier<Block> CRACKED_PURPUR_PATH = BLOCKS.register("cracked_purpur_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.50f), END_STONE_GRAVEL));

    //decorated
    public static final RegistrySupplier<Block> MOSSY_COBBLED_PATH = BLOCKS.register("mossy_cobbled_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.DIRT).sound(SoundType.GRASS).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.15f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","dirt"))));
    public static final RegistrySupplier<Block> RED_NETHER_BRICK_PATH = BLOCKS.register("red_nether_brick_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.10f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand"))));
    public static final RegistrySupplier<Block> GILDED_BLACKSTONE_PATH = BLOCKS.register("gilded_blackstone_path", () ->
            new BetterPathBlock(Block.Properties.of(Material.STONE).strength(0.75f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.50f), RegUtil.getVanillaBlockSupplier(new ResourceLocation("minecraft","soul_sand"))));

    public static void init() {
        BLOCKS.register();
    }

    public static void initTransforms() {
        cobbledPathTransforms.put(CobbledPathsItems.MOSS_BALL.get(), MOSSY_COBBLED_PATH.get());
        netherBrickPathTransforms.put(RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","nether_wart")).get(), RED_NETHER_BRICK_PATH.get());
        blackstoneTransforms.put(RegUtil.getVanillaItemSupplier(new ResourceLocation("minecraft","gold_nugget")).get(), GILDED_BLACKSTONE_PATH.get());
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
