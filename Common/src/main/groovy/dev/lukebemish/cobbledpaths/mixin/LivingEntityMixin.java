/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.mixin;

import java.util.Map;

import dev.lukebemish.cobbledpaths.CobbledPathsCommon;
import dev.lukebemish.cobbledpaths.block.BetterPathBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Accessor
    abstract Map<MobEffect, MobEffectInstance> getActiveEffects();

    LivingEntityMixin(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    @Final
    public abstract boolean addEffect(MobEffectInstance effectInstance);

    @Shadow
    protected abstract boolean shouldRemoveSoulSpeed(BlockState state);

    @Inject(method = "baseTick()V", at = @At("HEAD"))
    private void cobbledpaths$baseTick(CallbackInfo ci) {
        BlockState state = this.level.getBlockState(this.blockPosition());
        if (state.is(CobbledPathsCommon.getPURPUR_PATH().get()) || state.is(CobbledPathsCommon.getCRACKED_PURPUR_PATH().get())) {
            if (!this.getActiveEffects().containsKey(MobEffects.JUMP) || this.getActiveEffects().get(MobEffects.JUMP).getAmplifier() < 2) {
                this.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 2, false, false, false));
            }
        }
    }

    @Inject(method = "checkFallDamage", at = @At("HEAD"))
    private void cobbledpaths$checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos, CallbackInfo ci) {
        if (!this.level.isClientSide && onGround && this.fallDistance > 0.0F) {
            this.cobbledpaths$removeStickySpeed();
            this.cobbledpaths$checkStickySpeed();
        }
    }

    private void cobbledpaths$removeStickySpeed() {
        AttributeInstance movementSpeed = ((LivingEntity)(Object)this).getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
        if (movementSpeed != null) {
            if (movementSpeed.getModifier(CobbledPathsCommon.STICKY_SPEED_UUID) != null) {
                movementSpeed.removeModifier(CobbledPathsCommon.STICKY_SPEED_UUID);
            }
        }
    }

    private void cobbledpaths$checkStickySpeed() {
        if (!this.getBlockStateOn().isAir()) {
            if ((Object)this instanceof Player player) {
                if (player.getAbilities().flying) {
                    return;
                }
            }
            Block block = this.level.getBlockState(this.blockPosition()).getBlock();
            if (block instanceof BetterPathBlock betterPathBlock) {
                double modifier = betterPathBlock.getSpeedModifier();
                AttributeInstance movementSpeed = ((LivingEntity)(Object)this).getAttributes().getInstance(Attributes.MOVEMENT_SPEED);
                if (movementSpeed == null) {
                    return;
                }
                double completeModifier = movementSpeed.getBaseValue() * (modifier - 1.0F);
                movementSpeed.addTransientModifier(new AttributeModifier(CobbledPathsCommon.STICKY_SPEED_UUID, "Path speed boost", completeModifier, AttributeModifier.Operation.ADDITION));
            }
        }
    }

    @Inject(method = "onChangedBlock", at = @At("TAIL"))
    private void cobbledpaths$$blockChange(BlockPos pos, CallbackInfo ci) {
        if (this.shouldRemoveSoulSpeed(this.getBlockStateOn())) {
            this.cobbledpaths$removeStickySpeed();
        }
        this.cobbledpaths$checkStickySpeed();
    }
}
