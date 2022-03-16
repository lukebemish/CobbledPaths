package com.github.lukebemish.cobbled_paths.fabric;

import com.github.lukebemish.cobbled_paths.CobbledPaths;
import com.github.lukebemish.cobbled_paths.CobbledPathsBlocks;
import com.github.lukebemish.cobbled_paths.CobbledPathsItems;
import net.fabricmc.api.ModInitializer;

public class CobbledPathsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CobbledPaths.init();
        CobbledPathsBlocks.initTransforms();
        CobbledPathsItems.initTransforms();
    }
}
