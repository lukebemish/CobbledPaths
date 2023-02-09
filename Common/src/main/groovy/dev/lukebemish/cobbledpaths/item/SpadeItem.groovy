/*
 * Copyright (C) 2022-2023 Luke Bemish and contributors
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */

package dev.lukebemish.cobbledpaths.item

import dev.lukebemish.cobbledpaths.CobbledPathsCommon
import groovy.transform.CompileStatic
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import org.jetbrains.annotations.NotNull

@CompileStatic
class SpadeItem extends Item {

    SpadeItem(Properties properties) {
        super(properties)
    }

    @Override
    boolean isValidRepairItem(@NotNull ItemStack stack, ItemStack repairCandidate) {
        return stack.itemHolder.is(CobbledPathsCommon.SPADE_REPAIR_TAG) || super.isValidRepairItem(stack, repairCandidate)
    }

    @Override
    InteractionResult useOn(UseOnContext context) {
        Level level = context.level
        BlockPos blockPos = context.clickedPos
        BlockState blockState = level.getBlockState(blockPos)
        Player player = context.player
        if (player !== null) {
            InteractionHand offHand = oppositeHand(context.hand)
            ItemStack offHandItem = player.getItemInHand(offHand)
            Block blockToPlace = Block.byItem(offHandItem.item)
            Item itemToGet = blockState.block.asItem()
            if (blockToPlace !== null && itemToGet !== null &&
                BuiltInRegistries.ITEM.wrapAsHolder(itemToGet).is(CobbledPathsCommon.SPADE_COMPATIBLE_TAG) &&
                offHandItem.itemHolder.is(CobbledPathsCommon.SPADE_COMPATIBLE_TAG)) {
                level.playSound(player, blockPos, SoundEvents.GRAVEL_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F)
                if (!level.isClientSide) {
                    BlockState outState = blockToPlace.defaultBlockState()
                    level.setBlock(blockPos, outState, Block.UPDATE_ALL_IMMEDIATE)
                    if (!player.isCreative()) {
                        offHandItem.shrink(1)
                        player.addItem(new ItemStack(itemToGet, 1))
                    }
                    context.itemInHand.hurtAndBreak(1, player, (contextEntity) -> {
                        contextEntity.broadcastBreakEvent(context.hand)
                    })
                }
                return InteractionResult.sidedSuccess(level.isClientSide)
            }
        }
        return InteractionResult.PASS
    }

    private static InteractionHand oppositeHand(InteractionHand hand) {
        return switch (hand) {
            case InteractionHand.OFF_HAND -> InteractionHand.MAIN_HAND
            case InteractionHand.MAIN_HAND -> InteractionHand.OFF_HAND
        }
    }
}
