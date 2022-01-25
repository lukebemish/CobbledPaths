package cobbled_paths.fabric;

import cobbled_paths.CobbledPaths;
import net.fabricmc.api.ModInitializer;

public class CobbledPathsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CobbledPaths.init();
    }
}
