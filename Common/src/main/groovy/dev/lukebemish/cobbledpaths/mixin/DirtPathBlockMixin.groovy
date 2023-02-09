/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.mixin

import dev.lukebemish.cobbledpaths.block.BetterPathBlock
import groovy.transform.CompileStatic
import groovy.transform.stc.POJO
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DirtPathBlock
import net.minecraft.world.level.block.FarmBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Unique
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

@CompileStatic
@POJO
@Mixin(BlockBehaviour)
class DirtPathBlockMixin {
    @Unique
    private static final List<Direction> DIRECTIONS = [Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST]

    @Inject(method = "onPlace", at = @At("HEAD"))
    private void cobbledpaths$onPlace(BlockState state, Level levelAccessor, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        if (state.getBlock() instanceof DirtPathBlock || state.getBlock() instanceof FarmBlock) {
            BlockPos abovePos = pos.relative(Direction.UP)
            DIRECTIONS.each {
                BlockPos relativePos = abovePos.relative(it)
                Block block = levelAccessor.getBlockState(relativePos).getBlock()
                if (block instanceof BetterPathBlock) {
                    levelAccessor.setBlockAndUpdate(relativePos, levelAccessor.getBlockState(relativePos).setValue(BetterPathBlock.getDirectionProperty(it.opposite), BetterPathBlock.hasBelow(levelAccessor, relativePos.relative(Direction.DOWN))))
                }
            }
        }
    }

    @Inject(method = "onRemove", at = @At("HEAD"))
    private void cobbledpaths$onRemove(BlockState state, Level levelAccessor, BlockPos pos, BlockState newState, boolean isMoving, CallbackInfo ci) {
        if (state.getBlock() instanceof DirtPathBlock) {
            BlockPos abovePos = pos.relative(Direction.UP)
            DIRECTIONS.each {
                BlockPos relativePos = abovePos.relative(it)
                Block block = levelAccessor.getBlockState(relativePos).getBlock()
                if (block instanceof BetterPathBlock) {
                    levelAccessor.setBlockAndUpdate(relativePos, levelAccessor.getBlockState(relativePos).setValue(BetterPathBlock.getDirectionProperty(it.opposite), false))
                }
            }
        }
    }
}
