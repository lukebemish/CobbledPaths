package cobbled_paths.mixin;

import cobbled_paths.block.BetterPathBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class DirtPathBlockMixin {
    @Inject(method = "onPlace", at = @At("HEAD"))
    public void onPlace(BlockState state, Level levelAccessor, BlockPos pos, BlockState oldState, boolean isMoving, CallbackInfo ci) {
        if (state.getBlock() instanceof DirtPathBlock || state.getBlock() instanceof FarmBlock) {
            BlockPos abovePos = pos.relative(Direction.UP);
            BlockPos nPosAbove = abovePos.relative(Direction.NORTH);
            BlockPos sPosAbove = abovePos.relative(Direction.SOUTH);
            BlockPos ePosAbove = abovePos.relative(Direction.EAST);
            BlockPos wPosAbove = abovePos.relative(Direction.WEST);
            if (levelAccessor.getBlockState(nPosAbove).getBlock() instanceof BetterPathBlock bpb) {
                levelAccessor.setBlockAndUpdate(nPosAbove, levelAccessor.getBlockState(nPosAbove).setValue(BetterPathBlock.SOUTH, bpb.hasBelow(levelAccessor, nPosAbove.relative(Direction.DOWN))));
            }
            if (levelAccessor.getBlockState(sPosAbove).getBlock() instanceof BetterPathBlock bpb) {
                levelAccessor.setBlockAndUpdate(sPosAbove, levelAccessor.getBlockState(sPosAbove).setValue(BetterPathBlock.NORTH, bpb.hasBelow(levelAccessor, sPosAbove.relative(Direction.DOWN))));
            }
            if (levelAccessor.getBlockState(ePosAbove).getBlock() instanceof BetterPathBlock bpb) {
                levelAccessor.setBlockAndUpdate(ePosAbove, levelAccessor.getBlockState(ePosAbove).setValue(BetterPathBlock.WEST, bpb.hasBelow(levelAccessor, ePosAbove.relative(Direction.DOWN))));
            }
            if (levelAccessor.getBlockState(wPosAbove).getBlock() instanceof BetterPathBlock bpb) {
                levelAccessor.setBlockAndUpdate(wPosAbove, levelAccessor.getBlockState(wPosAbove).setValue(BetterPathBlock.EAST, bpb.hasBelow(levelAccessor, wPosAbove.relative(Direction.DOWN))));

            }
        }
    }

    @Inject(method = "onRemove", at = @At("HEAD"))
    public void onRemove(BlockState state, Level levelAccessor, BlockPos pos, BlockState newState, boolean isMoving, CallbackInfo ci) {
        if (state.getBlock() instanceof DirtPathBlock) {
            BlockPos abovePos = pos.relative(Direction.UP);
            BlockPos nPosAbove = abovePos.relative(Direction.NORTH);
            BlockPos sPosAbove = abovePos.relative(Direction.SOUTH);
            BlockPos ePosAbove = abovePos.relative(Direction.EAST);
            BlockPos wPosAbove = abovePos.relative(Direction.WEST);
            if (levelAccessor.getBlockState(nPosAbove).getBlock() instanceof BetterPathBlock) {
                levelAccessor.setBlockAndUpdate(nPosAbove, levelAccessor.getBlockState(nPosAbove).setValue(BetterPathBlock.SOUTH, false));
            }
            if (levelAccessor.getBlockState(sPosAbove).getBlock() instanceof BetterPathBlock) {
                levelAccessor.setBlockAndUpdate(sPosAbove, levelAccessor.getBlockState(sPosAbove).setValue(BetterPathBlock.NORTH, false));
            }
            if (levelAccessor.getBlockState(ePosAbove).getBlock() instanceof BetterPathBlock) {
                levelAccessor.setBlockAndUpdate(ePosAbove, levelAccessor.getBlockState(ePosAbove).setValue(BetterPathBlock.WEST, false));
            }
            if (levelAccessor.getBlockState(wPosAbove).getBlock() instanceof BetterPathBlock) {
                levelAccessor.setBlockAndUpdate(wPosAbove, levelAccessor.getBlockState(wPosAbove).setValue(BetterPathBlock.EAST, false));

            }
        }
    }
}
