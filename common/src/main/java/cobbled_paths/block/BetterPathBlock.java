package cobbled_paths.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Random;

public class BetterPathBlock extends DirtPathBlock {
    private final BlockState origBlockState;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty WEST;

    public BetterPathBlock(Properties properties) {
        this(properties, Blocks.DIRT.defaultBlockState());
    }

    public BetterPathBlock(Properties properties, BlockState blockState) {
        super(properties);
        this.origBlockState = blockState;
        this.registerDefaultState((this.stateDefinition.any()).setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(EAST, false).setValue(WEST, false));
    }

    static {
        NORTH = BlockStateProperties.NORTH;
        SOUTH = BlockStateProperties.SOUTH;
        EAST = BlockStateProperties.EAST;
        WEST = BlockStateProperties.WEST;
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        if (!this.defaultBlockState().canSurvive(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos())) {
            return Block.pushEntitiesUp(this.defaultBlockState(), this.origBlockState, blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
        }
        Level blockGetter = blockPlaceContext.getLevel();
        BlockState stateOut = this.defaultBlockState();
        stateOut = updateBlockState(stateOut, blockGetter, blockPlaceContext.getClickedPos());
        return stateOut;
    }

    public boolean hasBelow(BlockGetter blockGetter, BlockPos blockPos) {
        return Block.isFaceFull(blockGetter.getBlockState(blockPos).getBlockSupportShape(blockGetter, blockPos), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        blockState = this.updateBlockState(blockState, levelAccessor, blockPos);
        System.out.println(blockState);
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public BlockState updateBlockState(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos nPos = blockPos.north();
        BlockPos sPos = blockPos.south();
        BlockPos ePos = blockPos.east();
        BlockPos wPos = blockPos.west();
        BlockPos dPos = blockPos.below();
        boolean hasBelow = hasBelow(levelReader, dPos);
        System.out.println(hasBelow);
        boolean n = canSurvive(blockState, levelReader, nPos.below()) && hasBelow;
        boolean s = canSurvive(blockState, levelReader, sPos.below()) && hasBelow;
        boolean e = canSurvive(blockState, levelReader, ePos.below()) && hasBelow;
        boolean w = canSurvive(blockState, levelReader, wPos.below()) && hasBelow;
        return blockState.setValue(NORTH, n).setValue(SOUTH, s).setValue(EAST, e).setValue(WEST, w);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH,SOUTH,EAST,WEST);
    }

    @Override
    public void tick(BlockState arg, ServerLevel arg2, BlockPos arg3, Random random) {
        this.turnToOriginal(arg, arg2, arg3);
    }

    public void turnToOriginal(BlockState blockState, Level level, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, pushEntitiesUp(blockState, this.origBlockState, level, blockPos));
    }
}
