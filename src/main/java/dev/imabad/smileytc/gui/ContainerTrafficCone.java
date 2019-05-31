package dev.imabad.smileytc.gui;

import dev.imabad.smileytc.blocks.TileSmileyTrafficCone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ContainerTrafficCone extends Container {

    private TileSmileyTrafficCone tileSmileyTrafficCone;
    private IInventory playerInventory;

    public ContainerTrafficCone(IInventory playerInventory, TileSmileyTrafficCone tileSmileyTrafficCone) {
        this.tileSmileyTrafficCone = tileSmileyTrafficCone;
        this.playerInventory = playerInventory;
    }

    public IInventory getPlayerInventory() {
        return playerInventory;
    }


    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}