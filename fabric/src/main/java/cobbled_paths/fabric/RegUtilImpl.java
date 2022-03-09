package cobbled_paths.fabric;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class RegUtilImpl {
    public static Supplier<Item> getVanillaItemSupplier(ResourceLocation rl) {
        return ()->Registry.ITEM.containsKey(rl)?Registry.ITEM.get(rl):null;
    }

    public static Supplier<Block> getVanillaBlockSupplier(ResourceLocation rl) {
        return ()->Registry.BLOCK.containsKey(rl)?Registry.BLOCK.get(rl):null;
    }
}
