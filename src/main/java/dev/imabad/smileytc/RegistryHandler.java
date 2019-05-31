package dev.imabad.smileytc;

import dev.imabad.smileytc.blocks.SmileyBlocks;
import dev.imabad.smileytc.blocks.TileSmileyTrafficCone;
import dev.imabad.smileytc.items.SmileyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = SmileyTrafficCones.MODID)
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(SmileyItems.SMILEY_TRAFFIC_CONE);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(SmileyBlocks.SMILEY_TRAFFIC_CONE);
        GameRegistry.registerTileEntity(TileSmileyTrafficCone.class, SmileyBlocks.SMILEY_TRAFFIC_CONE.getRegistryName());
    }

}
