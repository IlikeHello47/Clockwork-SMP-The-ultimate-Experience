package dev.IlikeHello47.clockwork_smp.obj;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;

public class VoxelBlockEntity extends BlockEntity {
    private byte[] voxelData = new byte[0];
    private int resolution = 64;
    private boolean dataChanged = false;

    public VoxelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public byte[] getVoxelData() { return this.voxelData; }
    public int getResolution() { return this.resolution; }
    public boolean hasDataChanged() { return this.dataChanged; }
    public void setClean() { this.dataChanged = false; }

    public void setVoxelData(byte[] data, int res) {
        this.voxelData = data;
        this.resolution = res;
        this.dataChanged = true;
        this.setChanged();

        if (this.level != null && !this.level.isClientSide) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putByteArray("VoxelData", this.voxelData);
        tag.putInt("Resolution", this.resolution);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.voxelData = tag.getByteArray("VoxelData");
        this.resolution = tag.getInt("Resolution");
        this.dataChanged = true;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }
}
