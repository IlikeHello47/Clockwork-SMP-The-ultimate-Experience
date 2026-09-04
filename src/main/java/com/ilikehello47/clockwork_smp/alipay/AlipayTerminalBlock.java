package com.ilikehello47.clockwork_smp.alipay;

import com.ilikehello47.clockwork_smp.Clockwork_SMP;
import com.ilikehello47.clockwork_smp.item.ModItems;
import com.ilikehello47.clockwork_smp.util.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;
import java.util.function.Supplier;

public class AlipayTerminalBlock {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Clockwork_SMP.MOD_ID);

    public static final DeferredBlock<Block> ALIPAY_TERMINAL = registerBlock("alipay_terminal",
            () -> new Terminal(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .noOcclusion()
            ));

    public static class Terminal extends HorizontalDirectionalBlock implements EntityBlock {
        private static final MapCodec<Terminal> CODEC = simpleCodec(Terminal::new);

        private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 12.0D, 15.0D);

        @Override
        protected MapCodec<? extends HorizontalDirectionalBlock> codec() { return CODEC; }

        public Terminal(Properties properties) {
            super(properties);
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Nullable
        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new TerminalEntity(pos, state);
        }

        @Override
        protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
            if (!level.isClientSide) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof TerminalEntity terminalBE) {
                    ItemStack mainHandItem = player.getMainHandItem();

                    if (player.isShiftKeyDown()) {
                        terminalBE.setLinkedPos(null);
                        player.sendSystemMessage(Component.translatable("chat.clockwork_smp.alipay_terminal.disconnected"));
                        return InteractionResult.SUCCESS;
                    }

                    if (!mainHandItem.isEmpty()) {
                        String itemDescriptionId = mainHandItem.getDescriptionId();

                        if (itemDescriptionId.contains("link") || itemDescriptionId.contains("wrench") || itemDescriptionId.contains("ticker")) {

                            // Holt das CustomData-Paket typsicher (1.21-Standard)
                            net.minecraft.world.item.component.CustomData customData =
                                    mainHandItem.getComponents().get(net.minecraft.core.component.DataComponents.CUSTOM_DATA);

                            if (customData != null) {
                                CompoundTag itemTag = customData.copyTag();
                                BlockPos targetPos = null;

                                if (itemTag.contains("TargetPos")) {
                                    targetPos = NbtUtils.readBlockPos(itemTag, "TargetPos").orElse(null);
                                } else if (itemTag.contains("Location")) {
                                    targetPos = NbtUtils.readBlockPos(itemTag, "Location").orElse(null);
                                } else if (itemTag.contains("links", 10)) {
                                    CompoundTag linksTag = itemTag.getCompound("links");
                                    if (linksTag.contains("TargetPos")) {
                                        targetPos = NbtUtils.readBlockPos(linksTag, "TargetPos").orElse(null);
                                    }
                                } else if (itemTag.contains("display_link", 10)) {
                                    CompoundTag displayTag = itemTag.getCompound("display_link");
                                    if (displayTag.contains("TargetPos")) {
                                        targetPos = NbtUtils.readBlockPos(displayTag, "TargetPos").orElse(null);
                                    }
                                }

                                if (targetPos != null) {
                                    terminalBE.setLinkedPos(targetPos);
                                    player.sendSystemMessage(Component.translatable("chat.clockwork_smp.alipay_terminal.connected_sync", targetPos.toShortString()));
                                    return InteractionResult.SUCCESS;
                                }
                            }

                            player.sendSystemMessage(Component.literal("§b[Alipay] §eKlicke zuerst mit dem Stocklink auf dein Create-Netzwerk!"));
                            return InteractionResult.SUCCESS;
                        }
                    }

                    if (terminalBE.isLinked()) {
                        BlockPos targetPos = terminalBE.getLinkedPos();
                        BlockEntity targetBE = level.getBlockEntity(targetPos);

                        if (targetBE instanceof SmartBlockEntity) {
                            player.sendSystemMessage(Component.translatable("chat.clockwork_smp.alipay_terminal.bro_message"));
                        } else {
                            player.sendSystemMessage(Component.translatable("chat.clockwork_smp.alipay_terminal.network_missing", targetPos.toShortString()));
                        }
                    } else {
                        player.sendSystemMessage(Component.translatable("chat.clockwork_smp.alipay_terminal.offline"));
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) { return SHAPE; }

        @Override
        public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) { return 1.0F; }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(FACING); }
    }

    public static class TerminalEntity extends BlockEntity {
        private BlockPos linkedNetworkPos = null;

        public TerminalEntity(BlockPos pos, BlockState state) {
            super(ModBlockEntities.ALIPAY_TERMINAL_BE.get(), pos, state);
        }

        public boolean isLinked() { return linkedNetworkPos != null; }
        public BlockPos getLinkedPos() { return linkedNetworkPos; }

        public void setLinkedPos(@Nullable BlockPos pos) {
            this.linkedNetworkPos = pos;
            setChanged();
        }

        @Override
        protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
            super.loadAdditional(tag, registries);
            if (tag.contains("LinkedPos")) {
                this.linkedNetworkPos = NbtUtils.readBlockPos(tag, "LinkedPos").orElse(null);
            }
        }

        @Override
        protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
            super.saveAdditional(tag, registries);
            if (this.linkedNetworkPos != null) {
                tag.put("LinkedPos", NbtUtils.writeBlockPos(this.linkedNetworkPos));
            }
        }
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventbus) { BLOCKS.register(eventbus); }
}
