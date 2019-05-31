package dev.imabad.smileytc.gui;

import dev.imabad.smileytc.blocks.TileSmileyTrafficCone;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID){
            case 1:
                return new ContainerTrafficCone(player.inventory, (TileSmileyTrafficCone) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID){
            case 1:
                return new GuiTrafficCone(
                    (TileSmileyTrafficCone) world.getTileEntity(new BlockPos(x, y, z)),
                    (ContainerTrafficCone) getServerGuiElement(ID, player, world, x, y, z));
            default:
                return null;
        }
    }
}
