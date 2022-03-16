package com.github.lukebemish.cobbled_paths.fabric;

import com.github.lukebemish.cobbled_paths.CobbledPathsClient;
import net.fabricmc.api.ClientModInitializer;

public class CobbledPathsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CobbledPathsClient.init();
    }
}
