package dev.imabad.smileytc.gui;

import dev.imabad.smileytc.SmileyPacketHandler;
import dev.imabad.smileytc.SmileyTrafficCones;
import dev.imabad.smileytc.blocks.SmileyBlocks;
import dev.imabad.smileytc.blocks.TileSmileyTrafficCone;
import dev.imabad.smileytc.packets.UpdateConePacket;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiTrafficCone extends GuiContainer {

    private static final ResourceLocation background = new ResourceLocation(SmileyTrafficCones.MODID,
        "textures/gui/blank.png");

    private ContainerTrafficCone inventoryPlayer;
    private TileSmileyTrafficCone tileSmileyTrafficCone;
    private GuiTextField nameField;

    public GuiTrafficCone(TileSmileyTrafficCone tileSmileyTrafficCone, ContainerTrafficCone inventorySlotsIn) {
        super(inventorySlotsIn);
        this.inventoryPlayer = inventorySlotsIn;
        this.tileSmileyTrafficCone = tileSmileyTrafficCone;

        this.xSize = 176;
        this.ySize = 125;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(SmileyBlocks.SMILEY_TRAFFIC_CONE.getTranslationKey() + ".name");
        fontRenderer
            .drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
        fontRenderer.drawString("Username", xSize / 2 - fontRenderer.getStringWidth("Username") / 2,
            20 /*(height + 11)*/, 0x404040);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        this.nameField.drawTextBox();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(background);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    public void initGui() {
        super.initGui();
        int width = this.width / 2;
        int height = this.height / 2;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, width - 50, height, 100, 20, I18n.format("structure_block.mode.save")));
        this.nameField = new GuiTextField(0, this.fontRenderer, width - 50, height - 30, 100, 20);
        this.nameField.setFocused(true);
        this.nameField
            .setText(tileSmileyTrafficCone.getName());
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.nameField.mouseClicked(mouseX, mouseY, state);
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if(this.nameField.isFocused()){
            this.nameField.textboxKeyTyped(typedChar, keyCode);
        }else{
            super.keyTyped(typedChar, keyCode);
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 0)
            {
                if(nameField.getText().length() < 1){
                    return;
                }
                SmileyPacketHandler.INSTANCE.sendToServer(new UpdateConePacket(tileSmileyTrafficCone.getPos(), nameField.getText()));
            }
        }
    }
}
