package dev.IlikeHello47.clockwork_smp.radio;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class RadioBlockEntity extends BlockEntity {
    private String streamUrl = "";
    private MonoRadioThread radioThread;

    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(RadioRegistration.RADIO_BLOCK_ENTITY.get(), pos, state);
    }

    public void setStreamUrl(String url) {
        this.streamUrl = url;
        setChanged();

        stopStream();
        if (!url.isEmpty()) {
            radioThread = new MonoRadioThread(url);
            radioThread.start();
        }
    }

    public void stopStream() {
        if (radioThread != null) {
            radioThread.stopRadio();
            radioThread = null;
        }
    }

    @Override
    public void setRemoved() {
        stopStream();
        super.setRemoved();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("StreamUrl", streamUrl);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.streamUrl = tag.getString("StreamUrl");
    }
}
