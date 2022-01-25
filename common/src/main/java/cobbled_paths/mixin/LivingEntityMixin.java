package cobbled_paths.mixin;

import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onChangedBlock(Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"))
    private void blockChangePathsUpdate(CallbackInfo ci) {
        // Blank for now, but some paths will need this
    }
}
