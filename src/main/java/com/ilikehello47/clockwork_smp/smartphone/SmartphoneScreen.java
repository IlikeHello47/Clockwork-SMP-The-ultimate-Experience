package com.ilikehello47.clockwork_smp.smartphone;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SmartphoneScreen extends Screen {
    public SmartphoneScreen() {
        super(Component.literal("Smartphone"));
    }

    @Override
    protected void init() {
        int buttonWidth = 100;
        int buttonHeight = 20;
        int centerX = this.width / 2 - (buttonWidth / 2);
        int centerY = this.height / 2;

        this.addRenderableWidget(Button.builder(Component.literal("💬 Chat App"), button -> {
            if (this.minecraft != null && this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.literal("§a[Handy]: Nachricht erfolgreich gesendet!"));
                this.onClose();
            }
        }).bounds(centerX, centerY - 25, buttonWidth, buttonHeight).build());

        this.addRenderableWidget(Button.builder(Component.literal("🔴 Ausschalten"), button -> {
            this.onClose();
        }).bounds(centerX, centerY + 5, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        int frameWidth = 140;
        int frameHeight = 200;
        int frameX = this.width / 2 - (frameWidth / 2);
        int frameY = this.height / 2 - (frameHeight / 2);

        guiGraphics.fill(frameX, frameY, frameX + frameWidth, frameY + frameHeight, 0xFF2D2D2D);

        guiGraphics.fill(frameX + 5, frameY + 5, frameX + frameWidth - 5, frameY + frameHeight - 5, 0xFF555555);

        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, frameY + 15, 0xFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}