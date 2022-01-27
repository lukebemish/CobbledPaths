package cobbled_paths.mixin;

import cobbled_paths.CobbledPathsBlocks;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    @Final
    private Map<MobEffect, MobEffectInstance> activeEffects;

    @Shadow
    @Final
    public abstract boolean addEffect(MobEffectInstance effectInstance);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "baseTick()V", at = @At("HEAD"))
    private void baseTick(CallbackInfo ci) {
        if (this.level.getBlockState(this.blockPosition()).is(CobbledPathsBlocks.PURPUR_PATH.get())) {
            if (!this.activeEffects.containsKey(MobEffects.JUMP) || this.activeEffects.get(MobEffects.JUMP).getAmplifier() < 2) {
                this.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 2, false, false, false));
                // Blank for now, but some paths will need this
            }
        }
    }
}
