package cobbled_paths;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registries;

import java.util.function.Supplier;

public class CobbledPaths {
    public static final String MOD_ID = "cobbled_paths";
    // We can use this if we don't want to use DeferredRegister
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));

    public static void init() {
        CobbledPathsBlocks.init();
        CobbledPathsItems.init();
    }
}
