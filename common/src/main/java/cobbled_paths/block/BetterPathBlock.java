package cobbled_paths.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class BetterPathBlock extends DirtPathBlock {
    private BlockState origBlockState;

    public BetterPathBlock(Properties properties) {
        this(properties, Blocks.DIRT.defaultBlockState());
    }

    public BetterPathBlock(Properties properties, BlockState blockState) {
        super(properties);
        this.origBlockState = blockState;
    }

    @Override
    public void tick(BlockState arg, ServerLevel arg2, BlockPos arg3, Random random) {
        this.turnToOriginal(arg, arg2, arg3);
    }

    public void turnToOriginal(BlockState blockState, Level level, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, pushEntitiesUp(blockState, this.origBlockState, level, blockPos));
    }
}
