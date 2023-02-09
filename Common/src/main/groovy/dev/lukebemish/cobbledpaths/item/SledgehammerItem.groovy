/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.item

import dev.lukebemish.cobbledpaths.CobbledPathsCommon
import dev.lukebemish.cobbledpaths.block.BetterPathBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState

class SledgehammerItem extends Item {
    public final Map<Block, Block> transforms = new HashMap<>()

    SledgehammerItem(Properties properties) {
        super(properties)
    }

    @Override
    boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return stack.itemHolder.is(CobbledPathsCommon.SLEDGEHAMMER_REPAIR_TAG) || super.isValidRepairItem(stack, repairCandidate)
    }

    @Override
    InteractionResult useOn(UseOnContext context) {
        Level level = context.level
        BlockPos blockPos = context.clickedPos
        BlockState blockState = level.getBlockState(blockPos)
        if (context.clickedFace == Direction.DOWN) {
            return InteractionResult.PASS
        } else {
            Player player = context.player
            Block outBlock = transforms.get(blockState.block)
            if (outBlock != null) {
                BlockState outState = outBlock.defaultBlockState()
                level.playSound(player, blockPos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F)
                if (!level.isClientSide) {
                    if (outBlock instanceof BetterPathBlock) {
                        outState = BetterPathBlock.updateBlockState(outState, level, blockPos)
                    }
                    level.setBlock(blockPos, outState, Block.UPDATE_ALL_IMMEDIATE)
                    if (player != null) {
                        context.itemInHand.hurtAndBreak(1, player, (contextEntity) -> {
                            contextEntity.broadcastBreakEvent(context.hand)
                        })
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide)
            }
            return InteractionResult.PASS
        }
    }
}
