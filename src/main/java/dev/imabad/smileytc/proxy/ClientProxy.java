package dev.imabad.smileytc.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

    @Override
    public World getWorld() {
        return Minecraft.getMinecraft().world;
    }

}
