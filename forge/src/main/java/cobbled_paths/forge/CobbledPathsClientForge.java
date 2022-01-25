package cobbled_paths.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import cobbled_paths.CobbledPaths;
import cobbled_paths.CobbledPathsClient;

@Mod.EventBusSubscriber(modid = CobbledPaths.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CobbledPathsClientForge {
    public static void init(final FMLClientSetupEvent event) {
        CobbledPathsClient.init();
    }
}
