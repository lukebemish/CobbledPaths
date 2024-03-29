/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.item

import dev.lukebemish.cobbledpaths.block.BetterPathBlock
import groovy.transform.CompileStatic
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent

import java.util.function.Supplier

@CompileStatic
class SettItem extends Item {
    private final Supplier<? extends Block> createdBlock
    private final List<Supplier<? extends Block>> originalBlocks

    SettItem(Properties properties, Supplier<? extends Block> createdBlock, Supplier<? extends Block>... originalBlocks) {
        super(properties)
        this.createdBlock = createdBlock
        this.originalBlocks = Arrays.asList(originalBlocks)
    }

    @Override
    InteractionResult useOn(UseOnContext context) {
        Level level = context.level
        BlockPos blockPos = context.clickedPos
        BlockState blockState = level.getBlockState(blockPos)
        if (context.clickedFace === Direction.DOWN) {
            return InteractionResult.PASS
        } else {
            Player player = context.player
            if (originalBlocks.any {blockState.is((Block)it.get())}) {
                Block toCreate = createdBlock.get()
                if (toCreate instanceof BetterPathBlock) {
                    level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F)
                    if (!level.isClientSide) {
                        BlockState outState = toCreate.defaultBlockState()
                        level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, outState))
                        outState = BetterPathBlock.updateBlockState(outState, level, blockPos)
                        level.setBlock(blockPos, outState, Block.UPDATE_ALL_IMMEDIATE)
                        context.itemInHand.shrink(1)
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide)
                }
            }
        }
        return InteractionResult.PASS
    }
}
