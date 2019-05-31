package dev.imabad.smileytc.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class TESRTrafficCone extends TileEntitySpecialRenderer<TileSmileyTrafficCone> {

    @Override
    public void render(TileSmileyTrafficCone te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);
        Minecraft mc = Minecraft.getMinecraft();
        if (te.getName() != null && !te.getName().isEmpty() && !mc.gameSettings.hideGUI) {
            if (mc.player.getDistanceSq(te.getPos()) <= 36) {
                drawNameplate(te, te.getName(), x , y, z, 12);
            }
        }
    }
}
