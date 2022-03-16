package com.github.lukebemish.cobbled_paths.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class SpadeItem extends Item {
    private final Map<Item, Block> placementMap;
    private final Map<Block, Item> removalMap;

    public SpadeItem(Properties properties, Map<Item, Block> placementMap, Map<Block, Item> removalMap) {
        super(properties);
        this.placementMap = placementMap;
        this.removalMap = removalMap;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.COPPER_INGOT) || super.isValidRepairItem(stack, repairCandidate);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Player player = context.getPlayer();
        if (player != null) {
            InteractionHand offHand = oppositeHand(context.getHand());
            ItemStack offHandItem = player.getItemInHand(offHand);
            Block blockToPlace = placementMap.get(offHandItem.getItem());
            Item itemToGet = removalMap.get(blockState.getBlock());
            if (blockToPlace != null && itemToGet != null) {
                level.playSound(player, blockPos, SoundEvents.GRAVEL_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    BlockState outState = blockToPlace.defaultBlockState();
                    level.setBlock(blockPos, outState, 11);
                    if (!player.isCreative()) {
                        offHandItem.shrink(1);
                        player.addItem(new ItemStack(itemToGet, 1));
                    }
                    context.getItemInHand().hurtAndBreak(1, player, (contextEntity) -> {
                        contextEntity.broadcastBreakEvent(context.getHand());
                    });
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return InteractionResult.PASS;
    }

    private InteractionHand oppositeHand(InteractionHand hand) {
        switch (hand) {
            case OFF_HAND:
                return InteractionHand.MAIN_HAND;
            case MAIN_HAND:
                return InteractionHand.OFF_HAND;
        }
        return InteractionHand.OFF_HAND;
    }
}
