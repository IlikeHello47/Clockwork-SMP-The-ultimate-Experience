package dev.IlikeHello47.clockwork_smp.radio;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class RadioScreen extends Screen {
    private final BlockPos blockPos;
    private EditBox urlInputField;

    public RadioScreen(BlockPos pos) {
        super(Component.translatable("radio.clockwork_smp.settings"));
        this.blockPos = pos;
    }

    @Override
    protected void init() {
        this.urlInputField = new EditBox(this.font, this.width / 2 - 100, this.height / 2 - 20, 200, 20, Component.literal("URL"));
        this.urlInputField.setMaxLength(256);
        this.addRenderableWidget(this.urlInputField);

        this.addRenderableWidget(Button.builder(Component.translatable("radio.clockwork_smp.save"), button -> {
            saveAndClose();
        }).bounds(this.width / 2 - 100, this.height / 2 + 10, 200, 20).build());
    }

    private void saveAndClose() {
        String url = this.urlInputField.getValue();
        RadioClientHandler.updateRadioStream(blockPos, url);
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
