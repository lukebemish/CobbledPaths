package cobbled_paths.item;

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

public class SettItem extends Item {
    private BlockState targetState;
    private Block targetBlock;

    public SettItem(Properties properties, BlockState blockState, Block block) {
        super(properties);
        this.targetState = blockState;
        this.targetBlock = block;
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
            if (blockState.is(targetBlock)) {
                level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(blockPos, targetState, 11);
                useOnContext.getItemInHand().shrink(1);
            }
        }
        return InteractionResult.PASS;
    }
}
