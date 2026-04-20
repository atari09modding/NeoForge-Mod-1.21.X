package net.atari09.atarisnewmegamodproject.screen;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.item.ItemStack;

public class ItemClientTooltipComponent implements ClientTooltipComponent {

    private final ItemStack stack;

    public ItemClientTooltipComponent(ItemTooltipComponent data) {
        this.stack = data.stack();
    }

    @Override
    public int getHeight() {
        return 20;
    }

    @Override
    public int getWidth(Font font) {
        return 18;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        ClientTooltipComponent.super.renderImage(font, x, y, guiGraphics);
        guiGraphics.renderItem(stack,x,y);
        guiGraphics.renderItemDecorations(font,stack,x,y);
    }
}
