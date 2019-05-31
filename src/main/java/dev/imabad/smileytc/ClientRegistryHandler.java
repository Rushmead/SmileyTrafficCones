package dev.imabad.smileytc;

import dev.imabad.smileytc.blocks.SmileyBlocks;
import dev.imabad.smileytc.blocks.TESRTrafficCone;
import dev.imabad.smileytc.blocks.TileSmileyTrafficCone;
import dev.imabad.smileytc.items.SmileyItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = SmileyTrafficCones.MODID, value = Side.CLIENT)
public class ClientRegistryHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(SmileyItems.SMILEY_TRAFFIC_CONE, 0, new ModelResourceLocation(SmileyItems.SMILEY_TRAFFIC_CONE.getRegistryName(), "normal"));
        ClientRegistry.bindTileEntitySpecialRenderer(TileSmileyTrafficCone.class, new TESRTrafficCone());
    }
}
