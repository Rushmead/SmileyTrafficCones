package dev.imabad.smileytc;

import dev.imabad.smileytc.packets.UpdateConePacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class SmileyPacketHandler {

    public static SimpleNetworkWrapper INSTANCE;


    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(
            SmileyTrafficCones.MODID);
        INSTANCE
            .registerMessage(UpdateConePacket.ServerHandler.class, UpdateConePacket.class, 0,
                Side.SERVER);
    }

    public static void clientInit(){
        INSTANCE
            .registerMessage(UpdateConePacket.ClientHandler.class, UpdateConePacket.class, 3,
                Side.CLIENT);
    }
}
