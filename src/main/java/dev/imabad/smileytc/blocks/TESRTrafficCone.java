package dev.imabad.smileytc.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TESRTrafficCone extends TileEntitySpecialRenderer<TileSmileyTrafficCone> {

    @Override
    public void render(TileSmileyTrafficCone te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        Minecraft mc = Minecraft.getMinecraft();
        if (te.getName() != null && !te.getName().isEmpty() && !mc.gameSettings.hideGUI) {
            drawNameplate(te, te.getName(), x , y, z, 36);
        }
    }
}
