package dev.imabad.smileytc;

import dev.imabad.smileytc.gui.GuiHandler;
import dev.imabad.smileytc.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = SmileyTrafficCones.MODID, name = SmileyTrafficCones.NAME, version = SmileyTrafficCones.VERSION)
public class SmileyTrafficCones
{
    public static final String MODID = "smileytc";
    public static final String NAME = "Smiley Traffic Cones";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static SmileyTrafficCones instance;


    @SidedProxy(clientSide = "dev.imabad.smileytc.proxy.ClientProxy", serverSide = "dev.imabad.smileytc.proxy.CommonProxy")
    public static CommonProxy proxy;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        SmileyPacketHandler.init();
        SmileyPacketHandler.clientInit();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}

