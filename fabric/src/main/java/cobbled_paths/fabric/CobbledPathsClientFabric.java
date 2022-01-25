package cobbled_paths.fabric;

import net.fabricmc.api.ClientModInitializer;
import cobbled_paths.CobbledPathsClient;

public class CobbledPathsClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CobbledPathsClient.init();
    }
}
