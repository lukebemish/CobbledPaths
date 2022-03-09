package cobbled_paths.fabric;

import cobbled_paths.CobbledPaths;
import cobbled_paths.CobbledPathsBlocks;
import cobbled_paths.CobbledPathsItems;
import net.fabricmc.api.ModInitializer;

public class CobbledPathsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CobbledPaths.init();
        CobbledPathsBlocks.initTransforms();
        CobbledPathsItems.initTransforms();
    }
}
