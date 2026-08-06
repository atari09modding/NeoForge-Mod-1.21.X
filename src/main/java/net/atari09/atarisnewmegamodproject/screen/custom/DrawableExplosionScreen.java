package net.atari09.atarisnewmegamodproject.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.atari09.atarisnewmegamodproject.AtariMod;
import net.atari09.atarisnewmegamodproject.network.payload.LaunchButtonPressedPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.phys.Vec2;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class DrawableExplosionScreen extends Screen {
    private List<Vec2> coords = new ArrayList<>();
    private Button launch;
    private static final ResourceLocation CROSSHAIR_SPRITE = AtariMod.res("textures/gui/crosshair.png");

    public DrawableExplosionScreen(Component title) {
        super(title);
    }


    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        this.coords.add(new Vec2((float) mouseX-((float) width /2), (float) mouseY-((float) height /2)));
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    protected void init() {
        super.init();

        launch = addRenderableWidget(Button.builder(Component.literal("LAUNCH"),
                button->{
                            PacketDistributor.sendToServer(new LaunchButtonPressedPacket(coords));
                }
        ).bounds(width/2-20,height-this.font.lineHeight*3,40,this.font.lineHeight+8).build());
    }



    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics,mouseX,mouseY,partialTick);

        guiGraphics.fill(20,20,width-20,height-20,0xFF828282);
        guiGraphics.blit(CROSSHAIR_SPRITE,width/2-7,height/2-7,0,0,15,15,15,15);
        for(Vec2 pos:coords){
            guiGraphics.fill((int) pos.x+(width/2), (int) pos.y+(height/2), (int) (pos.x+((float) width /2)+2), (int) (pos.y+((float) height /2)+2),0xFFFF0000);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);

    }
}
