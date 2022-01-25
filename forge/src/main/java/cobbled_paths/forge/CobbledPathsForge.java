package cobbled_paths.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import cobbled_paths.CobbledPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CobbledPaths.MOD_ID)
public class CobbledPathsForge {
    public CobbledPathsForge() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(CobbledPaths.MOD_ID, modbus);
        CobbledPaths.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(CobbledPathsClientForge::init));
    }
}
