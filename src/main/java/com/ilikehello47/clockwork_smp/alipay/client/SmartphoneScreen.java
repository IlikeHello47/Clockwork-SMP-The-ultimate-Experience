package com.ilikehello47.clockwork_smp.alipay.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;

public class SmartphoneScreen extends Screen {
    private final ItemStack phoneStack;
    private final Player player;
    private App currentApp = App.HOME;

    enum App { HOME, ALIPAY, GPS, SETTINGS }

    public SmartphoneScreen(ItemStack stack, Player player) {
        super(Component.literal("Smartphone"));
        this.phoneStack = stack;
        this.player = player;
    }

    @Override
    protected void init() {
        this.clearWidgets();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        if (this.currentApp == App.HOME) {
            // App-Buttons auf dem Homescreen registrieren
            this.addRenderableWidget(Button.builder(Component.literal("🔵 Alipay"), b -> openApp(App.ALIPAY))
                    .bounds(centerX - 40, centerY - 40, 80, 20).build());

            this.addRenderableWidget(Button.builder(Component.literal("🗺️ GPS/Map"), b -> openApp(App.GPS))
                    .bounds(centerX - 40, centerY - 10, 80, 20).build());

            this.addRenderableWidget(Button.builder(Component.literal("⚙️ Settings"), b -> openApp(App.SETTINGS))
                    .bounds(centerX - 40, centerY + 20, 80, 20).build());
        } else {
            // Universaler "Home-Button" für alle Unter-Apps
            this.addRenderableWidget(Button.builder(Component.literal("↩ Home"), b -> openApp(App.HOME))
                    .bounds(centerX - 40, centerY + 60, 80, 20).build());
        }
    }

    private void openApp(App app) {
        this.currentApp = app;
        this.init(); // GUI neu laden, um Buttons der App anzuzeigen
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Render das "Handy-Gehäuse" (Ein einfaches dunkles Rechteck)
        guiGraphics.fill(centerX - 60, centerY - 100, centerX + 60, centerY + 100, 0xFF1A1A1A); // Rahmen
        guiGraphics.fill(centerX - 55, centerY - 95, centerX + 55, centerY + 95, 0xFF333333);   // Bildschirm

        // Inhalt je nach geöffneter App rendern
        switch (this.currentApp) {
            case HOME -> guiGraphics.drawCenteredString(this.font, "SmartOS v1.0", centerX, centerY - 80, 0xFFFFFF);
            case ALIPAY -> renderAlipayApp(guiGraphics, centerX, centerY);
            case GPS -> renderGpsApp(guiGraphics, centerX, centerY);
            case SETTINGS -> renderSettingsApp(guiGraphics, centerX, centerY);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void renderAlipayApp(GuiGraphics guiGraphics, int cx, int cy) {
        guiGraphics.drawCenteredString(this.font, "支 Alipay 🟢", cx, cy - 80, 0x00A0E9);
        // Simuliertes Guthaben aus den Item-Daten holen (Hier vereinfacht als Platzhalter)
        guiGraphics.drawCenteredString(this.font, "Guthaben:", cx, cy - 40, 0xAAAAAA);
        guiGraphics.drawCenteredString(this.font, "845.50 ¥", cx, cy - 25, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "[Scan-Bereit]", cx, cy + 10, 0x55FF55);
    }

    private void renderGpsApp(GuiGraphics guiGraphics, int cx, int cy) {
        guiGraphics.drawCenteredString(this.font, "GPS Navigation", cx, cy - 80, 0xFFAA00);
        guiGraphics.drawCenteredString(this.font, "X: " + (int)player.getX(), cx, cy - 40, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "Y: " + (int)player.getY(), cx, cy - 25, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "Z: " + (int)player.getZ(), cx, cy - 10, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "Biom: " + player.level().getBiome(player.blockPosition()).value().toString(), cx, cy + 20, 0x55FFFF);
    }

    private void renderSettingsApp(GuiGraphics guiGraphics, int cx, int cy) {
        guiGraphics.drawCenteredString(this.font, "Einstellungen", cx, cy - 80, 0xAAAAAA);
        guiGraphics.drawCenteredString(this.font, "Flugmodus: AUS", cx, cy - 40, 0xFF5555);
        guiGraphics.drawCenteredString(this.font, "Akku: 100%", cx, cy - 20, 0x55FF55);
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Das Spiel läuft im Hintergrund weiter
    }
}

