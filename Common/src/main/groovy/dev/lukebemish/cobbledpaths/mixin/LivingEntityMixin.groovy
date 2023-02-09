/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.mixin

import dev.lukebemish.cobbledpaths.CobbledPathsCommon
import dev.lukebemish.cobbledpaths.block.BetterPathBlock
import groovy.transform.CompileStatic
import groovy.transform.stc.POJO
import net.minecraft.core.BlockPos
import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeInstance
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import org.spongepowered.asm.mixin.Final
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@CompileStatic
@POJO
@Mixin(LivingEntity)
abstract class LivingEntityMixin extends Entity {
    @Shadow
    @Final
    private Map<MobEffect, MobEffectInstance> activeEffects

    LivingEntityMixin(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level)
    }

    @Shadow
    @Final
    abstract boolean addEffect(MobEffectInstance effectInstance)

    @Shadow
    protected abstract boolean shouldRemoveSoulSpeed(BlockState state)

    @Inject(method = "baseTick()V", at = @At("HEAD"))
    private void cobbledpaths$baseTick(CallbackInfo ci) {
        BlockState state = this.level.getBlockState(this.blockPosition())
        if (state.is(CobbledPathsCommon.PURPUR_PATH.get()) || state.is(CobbledPathsCommon.CRACKED_PURPUR_PATH.get())) {
            if (!this.activeEffects.containsKey(MobEffects.JUMP) || this.activeEffects.get(MobEffects.JUMP).getAmplifier() < 2) {
                this.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 2, false, false, false))
            }
        }
    }

    @Inject(method = "checkFallDamage", at = @At("HEAD"))
    protected void checkFallDamage$head(double y, boolean onGround, BlockState state, BlockPos pos, CallbackInfo ci) {
        if (!this.level.isClientSide && onGround && this.fallDistance > 0.0F) {
            this.cobbledpaths$removeStickySpeed()
            this.cobbledpaths$checkStickySpeed()
        }
    }

    private void cobbledpaths$removeStickySpeed() {
        AttributeInstance movementSpeed = ((LivingEntity)(Object)this).attributes.getInstance(Attributes.MOVEMENT_SPEED)
        if (movementSpeed !== null) {
            if (movementSpeed.getModifier(CobbledPathsCommon.STICKY_SPEED_UUID) !== null) {
                movementSpeed.removeModifier(CobbledPathsCommon.STICKY_SPEED_UUID)
            }
        }
    }

    private void cobbledpaths$checkStickySpeed() {
        if (!this.blockStateOn.air) {
            if ((Object)this instanceof Player) {
                var player = (Player)(Object)this
                if (player.getAbilities().flying) {
                    return
                }
            }
            Block block = this.level.getBlockState(this.blockPosition()).block
            if (block instanceof BetterPathBlock) {
                double modifier = block.speedModifier
                AttributeInstance movementSpeed = ((LivingEntity)(Object)this).attributes.getInstance(Attributes.MOVEMENT_SPEED)
                if (movementSpeed == null) {
                    return
                }
                double completeModifier = movementSpeed.getBaseValue() * (modifier - 1.0F)
                movementSpeed.addTransientModifier(new AttributeModifier(CobbledPathsCommon.STICKY_SPEED_UUID, "Path speed boost", completeModifier, AttributeModifier.Operation.ADDITION))
            }
        }
    }

    @Inject(method = "onChangedBlock", at = @At("TAIL"))
    protected void onChangedBlock$tail(BlockPos pos, CallbackInfo ci) {
        if (this.shouldRemoveSoulSpeed(this.getBlockStateOn())) {
            this.cobbledpaths$removeStickySpeed()
        }
        this.cobbledpaths$checkStickySpeed()
    }
}
