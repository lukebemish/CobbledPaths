package dev.lukebemish.cobbledpaths

import dev.lukebemish.cobbledpaths.block.BetterPathBlock
import dev.lukebemish.cobbledpaths.item.SettItem
import dev.lukebemish.cobbledpaths.item.SledgehammerItem
import dev.lukebemish.cobbledpaths.item.SpadeItem
import groovy.transform.CompileStatic
import io.github.groovymc.cgl.reg.RegistrationProvider
import io.github.groovymc.cgl.reg.RegistryObject
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Material

import java.util.function.Supplier

@CompileStatic
final class CobbledPathsCommon {

    static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID)
    static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, Constants.MOD_ID)

    static final TagKey<Item> SPADE_COMPATIBLE_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "spade_compatible"))
    static final TagKey<Item> SPADE_REPAIR_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "spade_repair"))
    static final TagKey<Item> SLEDGEHAMMER_REPAIR_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(Constants.MOD_ID, "sledgehammer_repair"))

    public static final UUID STICKY_SPEED_UUID = UUID.fromString("51c53134-f501-4d3b-897e-cc2af5b3bd26")

    static final BlockBehaviour.Properties COBBLED_PROPERTIES = BlockBehaviour.Properties.of(Material.DIRT).sound(SoundType.GRASS).strength(0.70f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties STONE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.70f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties DEEPSLATE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.75f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties ICE_PROPERTIES = BlockBehaviour.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties BLUE_ICE_PROPERTIES = BlockBehaviour.Properties.of(Material.ICE_SOLID).sound(SoundType.GLASS).strength(0.50f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties NETHER_BRICK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(0.70f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties BLACKSTONE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(0.75f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties OBSIDIAN_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(1.00f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)
    static final BlockBehaviour.Properties PURPUR_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(0.75f)
        .isViewBlocking(CobbledPathsCommon::always).isSuffocating(CobbledPathsCommon::always)

    static final RegistryObject<BetterPathBlock> COBBLED_PATH = BLOCKS.register("cobbled_path", () -> new BetterPathBlock(COBBLED_PROPERTIES, () -> Blocks.DIRT, 1.15F))
    static final RegistryObject<BetterPathBlock> MOSSY_COBBLED_PATH = BLOCKS.register("mossy_cobbled_path", () -> new BetterPathBlock(COBBLED_PROPERTIES, () -> Blocks.DIRT, 1.15F))

    static final RegistryObject<BetterPathBlock> STONE_PATH = BLOCKS.register("stone_path", () -> new BetterPathBlock(STONE_PROPERTIES, () -> Blocks.DIRT, 1.30F))
    static final RegistryObject<BetterPathBlock> CRACKED_STONE_PATH = BLOCKS.register("cracked_stone_path", () -> new BetterPathBlock(STONE_PROPERTIES, () -> Blocks.DIRT, 1.30F))

    static final RegistryObject<BetterPathBlock> DEEPSLATE_PATH = BLOCKS.register("deepslate_path", () -> new BetterPathBlock(DEEPSLATE_PROPERTIES, () -> Blocks.DIRT, 1.45F))
    static final RegistryObject<BetterPathBlock> CRACKED_DEEPSLATE_PATH = BLOCKS.register("cracked_deepslate_path", () -> new BetterPathBlock(DEEPSLATE_PROPERTIES, () -> Blocks.DIRT, 1.45F))

    static final RegistryObject<BetterPathBlock> ICE_PATH = BLOCKS.register("ice_path", () -> new BetterPathBlock(ICE_PROPERTIES, () -> Blocks.SNOW_BLOCK, 1.35F))
    static final RegistryObject<BetterPathBlock> CRACKED_ICE_PATH = BLOCKS.register("cracked_ice_path", () -> new BetterPathBlock(ICE_PROPERTIES, () -> Blocks.SNOW_BLOCK, 1.35F))

    static final RegistryObject<BetterPathBlock> BLUE_ICE_PATH = BLOCKS.register("blue_ice_path", () -> new BetterPathBlock(BLUE_ICE_PROPERTIES, () -> Blocks.SNOW_BLOCK, 1.55F))
    static final RegistryObject<BetterPathBlock> CRACKED_BLUE_ICE_PATH = BLOCKS.register("cracked_blue_ice_path", () -> new BetterPathBlock(BLUE_ICE_PROPERTIES, () -> Blocks.SNOW_BLOCK, 1.55F))

    static final RegistryObject<BetterPathBlock> NETHER_BRICK_PATH = BLOCKS.register("nether_brick_path", () -> new BetterPathBlock(NETHER_BRICK_PROPERTIES, () -> Blocks.SOUL_SAND, 1.10F))
    static final RegistryObject<BetterPathBlock> CRACKED_NETHER_BRICK_PATH = BLOCKS.register("cracked_nether_brick_path", () -> new BetterPathBlock(NETHER_BRICK_PROPERTIES, () -> Blocks.SOUL_SAND, 1.10F))
    static final RegistryObject<BetterPathBlock> RED_NETHER_BRICK_PATH = BLOCKS.register("red_nether_brick_path", () -> new BetterPathBlock(NETHER_BRICK_PROPERTIES, () -> Blocks.SOUL_SAND, 1.10F))

    static final RegistryObject<BetterPathBlock> BLACKSTONE_PATH = BLOCKS.register("blackstone_path", () -> new BetterPathBlock(BLACKSTONE_PROPERTIES, () -> Blocks.SOUL_SAND, 1.50F))
    static final RegistryObject<BetterPathBlock> CRACKED_BLACKSTONE_PATH = BLOCKS.register("cracked_blackstone_path", () -> new BetterPathBlock(BLACKSTONE_PROPERTIES, () -> Blocks.SOUL_SAND, 1.50F))
    static final RegistryObject<BetterPathBlock> GILDED_BLACKSTONE_PATH = BLOCKS.register("gilded_blackstone_path", () -> new BetterPathBlock(BLACKSTONE_PROPERTIES, () -> Blocks.SOUL_SAND, 1.50F))

    static final RegistryObject<BetterPathBlock> OBSIDIAN_PATH = BLOCKS.register("obsidian_path", () -> new BetterPathBlock(OBSIDIAN_PROPERTIES, () -> Blocks.END_STONE, 1.30F))
    static final RegistryObject<BetterPathBlock> CRACKED_OBSIDIAN_PATH = BLOCKS.register("cracked_obsidian_path", () -> new BetterPathBlock(OBSIDIAN_PROPERTIES, () -> Blocks.END_STONE, 1.30F))

    static final RegistryObject<BetterPathBlock> PURPUR_PATH = BLOCKS.register("purpur_path", () -> new BetterPathBlock(PURPUR_PROPERTIES, () -> Blocks.END_STONE, 1.50F))
    static final RegistryObject<BetterPathBlock> CRACKED_PURPUR_PATH = BLOCKS.register("cracked_purpur_path", () -> new BetterPathBlock(PURPUR_PROPERTIES, () -> Blocks.END_STONE, 1.50F))


    static final RegistryObject<Item> MOSS_BALL = ITEMS.register("moss_ball", () -> new Item(new Item.Properties()))
    static final RegistryObject<SpadeItem> SPADE = ITEMS.register("spade", () -> new SpadeItem(new Item.Properties().durability(1000)))
    static final RegistryObject<SledgehammerItem> SLEDGEHAMMER = ITEMS.register("sledgehammer", () -> new SledgehammerItem(new Item.Properties().durability(512)))

    static final RegistryObject<SettItem> COBBLE = registerSett("cobble", COBBLED_PATH, () -> Blocks.DIRT_PATH)
    static final RegistryObject<SettItem> STONE_SETT = registerSett("stone_sett", STONE_PATH, () -> Blocks.DIRT_PATH)
    static final RegistryObject<SettItem> DEEPSLATE_SETT = registerSett("deepslate_sett", DEEPSLATE_PATH, () -> Blocks.DIRT_PATH)

    static final RegistryObject<SettItem> ICE_SETT = registerSett("ice_sett", ICE_PATH, () -> Blocks.SNOW_BLOCK)
    static final RegistryObject<SettItem> BLUE_ICE_SETT = registerSett("blue_ice_sett", BLUE_ICE_PATH, () -> Blocks.SNOW_BLOCK)

    static final RegistryObject<SettItem> NETHER_BRICK_SETT = registerSett("nether_brick_sett", NETHER_BRICK_PATH, () -> Blocks.SOUL_SAND, () -> Blocks.SOUL_SOIL)
    static final RegistryObject<SettItem> BLACKSTONE_SETT = registerSett("blackstone_sett", BLACKSTONE_PATH, () -> Blocks.SOUL_SAND, () -> Blocks.SOUL_SOIL)

    static final RegistryObject<SettItem> OBSIDIAN_SETT = registerSett("obsidian_sett", OBSIDIAN_PATH, () -> Blocks.END_STONE)
    static final RegistryObject<SettItem> PURPUR_SETT = registerSett("purpur_sett", PURPUR_PATH, () -> Blocks.END_STONE)

    private CobbledPathsCommon() {}

    static void init() {
        register()
    }

    static void register() {
    }

    @SuppressWarnings('unused')
    static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
        return true
    }

    static void setupTransforms() {
        COBBLED_PATH.get().transforms[MOSS_BALL.get()] = MOSSY_COBBLED_PATH.get()
        NETHER_BRICK_PATH.get().transforms[Items.NETHER_WART] = RED_NETHER_BRICK_PATH.get()
        BLACKSTONE_PATH.get().transforms[Items.GOLD_NUGGET] = GILDED_BLACKSTONE_PATH.get()

        SLEDGEHAMMER.get().tap {
            it.transforms[STONE_PATH.get()] = CRACKED_STONE_PATH.get()
            it.transforms[DEEPSLATE_PATH.get()] = CRACKED_DEEPSLATE_PATH.get()
            it.transforms[ICE_PATH.get()] = CRACKED_ICE_PATH.get()
            it.transforms[BLUE_ICE_PATH.get()] = CRACKED_BLUE_ICE_PATH.get()
            it.transforms[NETHER_BRICK_PATH.get()] = CRACKED_NETHER_BRICK_PATH.get()
            it.transforms[BLACKSTONE_PATH.get()] = CRACKED_BLACKSTONE_PATH.get()
            it.transforms[OBSIDIAN_PATH.get()] = CRACKED_OBSIDIAN_PATH.get()
            it.transforms[PURPUR_PATH.get()] = CRACKED_PURPUR_PATH.get()
        }
    }

    static RegistryObject<SettItem> registerSett(String name, RegistryObject<? extends BetterPathBlock> path, Supplier<? extends Block>... sources) {
        return ITEMS.register(name, () -> new SettItem(new Item.Properties(), path, sources))
    }
}
