package cobbled_paths;

import cobbled_paths.block.BetterPathBlock;
import dev.architectury.registry.block.BlockProperties;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class CobbledPathsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(CobbledPaths.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> COBBLED_PATH = BLOCKS.register("cobbled_path", () ->
            new BetterPathBlock(BlockProperties.of(Material.DIRT).sound(SoundType.GRASS).strength(0.70f)
                    .isViewBlocking(CobbledPathsBlocks::always).isSuffocating(CobbledPathsBlocks::always)
                    .speedFactor(1.15f)));
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

    public static void init() {
        BLOCKS.register();
    }

    private static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }
}
