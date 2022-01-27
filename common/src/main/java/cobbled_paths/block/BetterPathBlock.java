package cobbled_paths.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BetterPathBlock extends DirtPathBlock {
    private final BlockState origBlockState;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty WEST;

    public final Map<Item, Block> TRANSFORMS;

    public BetterPathBlock(Properties properties) {
        this(properties, Blocks.DIRT.defaultBlockState());
    }

    public BetterPathBlock(Properties properties, BlockState blockState) {
        this(properties, blockState, new HashMap<>());
    }
    public BetterPathBlock(Properties properties, BlockState blockState, Map<Item, Block> transforms) {
        super(properties);
        this.TRANSFORMS = transforms;
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) {
            return super.use(state, level, pos, player, hand, hit);
        }
        ItemStack heldItemStack = player.getItemInHand(hand);
        Item heldItem = heldItemStack.getItem();
        Block toTransform = TRANSFORMS.get(heldItem);
        if (toTransform instanceof BetterPathBlock betterPathBlock) {
            BlockState outState = betterPathBlock.defaultBlockState();
            outState = betterPathBlock.updateBlockState(outState, level, pos);
            level.setBlock(pos, outState, 11);
            if (!player.isCreative()) {
                player.getItemInHand(hand).shrink(1);
            }
            return InteractionResult.CONSUME;
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
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
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    public BlockState updateBlockState(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos nPos = blockPos.north();
        BlockPos sPos = blockPos.south();
        BlockPos ePos = blockPos.east();
        BlockPos wPos = blockPos.west();
        BlockPos dPos = blockPos.below();
        boolean hasBelow = hasBelow(levelReader, dPos);
        boolean n = levelReader.getBlockState(nPos.below()).getBlock() instanceof DirtPathBlock;
        boolean s = levelReader.getBlockState(sPos.below()).getBlock() instanceof DirtPathBlock;
        boolean e = levelReader.getBlockState(ePos.below()).getBlock() instanceof DirtPathBlock;
        boolean w = levelReader.getBlockState(wPos.below()).getBlock() instanceof DirtPathBlock;
        return blockState.setValue(NORTH, n && hasBelow).setValue(SOUTH, s && hasBelow).setValue(EAST, e && hasBelow).setValue(WEST, w && hasBelow);
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
