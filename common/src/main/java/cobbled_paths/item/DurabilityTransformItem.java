package cobbled_paths.item;

import cobbled_paths.block.BetterPathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.function.Supplier;

public class DurabilityTransformItem extends Item {
    protected final Map<Block, Block> TRANSFORMS;
    private final Supplier<Item> repairItem;

    public DurabilityTransformItem(Properties properties, Map<Block, Block> transforms, Supplier<Item> repairItem) {
        super(properties);
        TRANSFORMS = transforms;
        this.repairItem = repairItem;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(repairItem.get()) || super.isValidRepairItem(stack, repairCandidate);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            Player player = context.getPlayer();
            Block outBlock = TRANSFORMS.get(blockState.getBlock());
            if (outBlock != null) {
                BlockState outState = outBlock.defaultBlockState();
                level.playSound(player, blockPos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    if (outBlock instanceof BetterPathBlock bpb) {
                        outState = bpb.updateBlockState(outState, level, blockPos);
                    }
                    level.setBlock(blockPos, outState, 11);
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, (contextEntity) -> {
                            contextEntity.broadcastBreakEvent(context.getHand());
                        });
                    }
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }
    }
}
