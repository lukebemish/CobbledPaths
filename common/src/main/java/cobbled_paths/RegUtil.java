package cobbled_paths;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class RegUtil {
    @ExpectPlatform
    public static Supplier<Block> getVanillaBlockSupplier(ResourceLocation rl) {
        throw new AssertionError();
    }
    @ExpectPlatform
    public static Supplier<Item> getVanillaItemSupplier(ResourceLocation rl) {
        throw new AssertionError();
    }
}
