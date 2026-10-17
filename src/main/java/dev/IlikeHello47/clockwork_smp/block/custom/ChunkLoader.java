package dev.IlikeHello47.clockwork_smp.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class ChunkLoader extends Block {
    public static final MapCodec<ChunkLoader> CODEC = simpleCodec(ChunkLoader::new);

    public static final IntegerProperty POWERSTATE = IntegerProperty.create("powerstate", 0, 6);
    public static final BooleanProperty FULL_ACTIVATE = BooleanProperty.create("full_activate");

    @Override
    protected @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public ChunkLoader(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERSTATE, 0)
                .setValue(FULL_ACTIVATE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERSTATE, FULL_ACTIVATE);
    }

    @Override
    protected void neighborChanged(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Block neighborBlock, @NotNull BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);

        if (!level.isClientSide()) {
            boolean hasSignal = level.hasNeighborSignal(pos);
            int currentPower = state.getValue(POWERSTATE);
            boolean isFullyActive = state.getValue(FULL_ACTIVATE);

            if (hasSignal && !isFullyActive) {
                if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
                    level.scheduleTick(pos, this, 20);
                }
            } else if (!hasSignal && (isFullyActive || currentPower > 0)) {
                if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
                    level.scheduleTick(pos, this, 20);
                }
            }
        }
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.tick(state, level, pos, random);

        boolean hasSignal = level.hasNeighborSignal(pos);
        int currentPower = state.getValue(POWERSTATE);
        boolean isFullyActive = state.getValue(FULL_ACTIVATE);

        int nextPower = currentPower;
        boolean nextFullyActive = isFullyActive;

        if (hasSignal) {
            if (currentPower < 6) {
                nextPower = currentPower + 1;
                if (nextPower == 6) {
                    nextFullyActive = true;
                }
            }
        } else {
            if (isFullyActive) {
                nextFullyActive = false;
                nextPower = 5;
            } else if (currentPower > 0) {
                nextPower = currentPower - 1;
            }
        }

        if (nextPower != currentPower || nextFullyActive != isFullyActive) {
            BlockState newState = state.setValue(POWERSTATE, nextPower).setValue(FULL_ACTIVATE, nextFullyActive);
            level.setBlock(pos, newState, Block.UPDATE_ALL);

            if (nextFullyActive && !isFullyActive) {
                ChunkLoadingHandler.startLoading(level, pos);
            } else if (!nextFullyActive && isFullyActive) {
                ChunkLoadingHandler.stopLoading(level, pos);
            }

            if (hasSignal && !nextFullyActive) {
                level.scheduleTick(pos, this, 20);
            } else if (!hasSignal && (nextFullyActive || nextPower > 0)) {
                level.scheduleTick(pos, this, 20);
            }
        }
    }

    @Override
    protected void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                if (state.getValue(FULL_ACTIVATE)) {
                    ChunkLoadingHandler.stopLoading(serverLevel, pos);
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }
}
