package dev.lukebemish.cobbledpaths.block

import groovy.transform.CompileStatic
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DirtPathBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult
import org.jetbrains.annotations.ApiStatus

import java.util.function.Supplier

@CompileStatic
class BetterPathBlock extends DirtPathBlock {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH
    public static final BooleanProperty EAST = BlockStateProperties.EAST
    public static final BooleanProperty WEST = BlockStateProperties.WEST

    final float speedModifier

    static BooleanProperty getDirectionProperty(Direction direction) {
        switch (direction) {
            case Direction.NORTH:
                return NORTH
            case Direction.SOUTH:
                return SOUTH
            case Direction.EAST:
                return EAST
            case Direction.WEST:
                return WEST
            default:
                return null
        }
    }

    public final Map<Item, Block> transforms = new HashMap<>()

    final Supplier<Block> original

    BetterPathBlock(Properties properties, Supplier<Block> original) {
        this(properties, original, 1.0F)
    }

    BetterPathBlock(Properties properties, Supplier<Block> original, float speedModifier) {
        super(properties)
        this.original = original
        this.speedModifier = speedModifier
    }

    @Override
    @ApiStatus.OverrideOnly
    @SuppressWarnings(["deprecation", 'GrDeprecatedAPIUsage'])
    InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide()) {
            return super.use(state, level, pos, player, hand, hit)
        }
        ItemStack heldItemStack = player.getItemInHand(hand)
        Item heldItem = heldItemStack.item
        Block toTransform = transforms.get(heldItem)
        if (toTransform instanceof BetterPathBlock) {
            BlockState outState = toTransform.defaultBlockState()
            outState = updateBlockState(outState, level, pos)
            level.setBlock(pos, outState, UPDATE_ALL_IMMEDIATE)
            if (!player.isCreative()) {
                player.getItemInHand(hand).shrink(1)
            }
            return InteractionResult.CONSUME
        } else {
            return super.use(state, level, pos, player, hand, hit)
        }
    }

    @Override
    BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        if (!this.defaultBlockState().canSurvive(blockPlaceContext.level, blockPlaceContext.clickedPos)) {
            return pushEntitiesUp(this.defaultBlockState(), this.original.get().defaultBlockState(), blockPlaceContext.level, blockPlaceContext.clickedPos)
        }
        Level blockGetter = blockPlaceContext.level
        BlockState stateOut = this.defaultBlockState()
        stateOut = updateBlockState(stateOut, blockGetter, blockPlaceContext.clickedPos)
        return stateOut
    }

    static boolean hasBelow(BlockGetter blockGetter, BlockPos blockPos) {
        return isFaceFull(blockGetter.getBlockState(blockPos).getBlockSupportShape(blockGetter, blockPos), Direction.UP)
    }

    @Override
    BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        blockState = updateBlockState(blockState, levelAccessor, blockPos)
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2)
    }

    static BlockState updateBlockState(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos nPos = blockPos.north()
        BlockPos sPos = blockPos.south()
        BlockPos ePos = blockPos.east()
        BlockPos wPos = blockPos.west()
        BlockPos dPos = blockPos.below()
        boolean hasBelow = hasBelow(levelReader, dPos)
        boolean n = levelReader.getBlockState(nPos.below()).block instanceof DirtPathBlock
        boolean s = levelReader.getBlockState(sPos.below()).block instanceof DirtPathBlock
        boolean e = levelReader.getBlockState(ePos.below()).block instanceof DirtPathBlock
        boolean w = levelReader.getBlockState(wPos.below()).block instanceof DirtPathBlock
        return blockState.setValue(NORTH, n && hasBelow).setValue(SOUTH, s && hasBelow).setValue(EAST, e && hasBelow).setValue(WEST, w && hasBelow)
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH,SOUTH,EAST,WEST)
    }

    @Override
    void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.turnToOriginal(state, level, pos)
    }

    void turnToOriginal(BlockState blockState, Level level, BlockPos blockPos) {
        level.setBlockAndUpdate(blockPos, pushEntitiesUp(blockState, this.original.get().defaultBlockState(), level, blockPos))
    }
}
