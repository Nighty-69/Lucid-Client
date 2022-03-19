package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import net.minecraft.inventory.IInventory;

public class OpenContainerEvent extends Event {

    private IInventory chestInventory;

    public OpenContainerEvent(IInventory chestInventory) {
        this.chestInventory = chestInventory;
    }

    public IInventory getContainer(){
        return this.chestInventory;
    }
}
