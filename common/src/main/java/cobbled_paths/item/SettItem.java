package cobbled_paths.item;

import cobbled_paths.block.BetterPathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class SettItem extends Item {
    private Block targetBlock;
    private List<Block> originalBlock = new ArrayList<>();

    public SettItem(Properties properties, Block targetBlock, Block originalBlock) {
        super(properties);
        this.targetBlock = targetBlock;
        this.originalBlock.add(originalBlock);
    }

    public SettItem(Properties properties, Block targetBlock, List<Block> originalBlocks) {
        super(properties);
        this.targetBlock = targetBlock;
        this.originalBlock = originalBlocks;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (useOnContext.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        } else {
            Player player = useOnContext.getPlayer();
            if (originalBlock.stream().anyMatch(blockState::is)) {
                if (targetBlock instanceof  BetterPathBlock newTarget) {
                    level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    BlockState outState = newTarget.defaultBlockState();
                    outState = newTarget.updateBlockState(outState, level, blockPos);
                    level.setBlock(blockPos, outState, 11);
                    useOnContext.getItemInHand().shrink(1);
                }
            }
        }
        return InteractionResult.PASS;
    }
}
