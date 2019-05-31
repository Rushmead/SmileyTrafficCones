package dev.imabad.smileytc.items;

import dev.imabad.smileytc.SmileyTrafficCones;
import dev.imabad.smileytc.blocks.SmileyBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmileyItems {

    public static Item SMILEY_TRAFFIC_CONE = new ItemBlock(SmileyBlocks.SMILEY_TRAFFIC_CONE).setRegistryName("trafficcone");

}
