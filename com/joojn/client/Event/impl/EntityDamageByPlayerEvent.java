package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

public class EntityDamageByPlayerEvent extends EventCancelable {

    private Entity entity;
    private EntityPlayer damager;

    public EntityDamageByPlayerEvent(EntityPlayer damager, Entity entity){
        this.entity = entity;
        this.damager = damager;
    }

    public EntityPlayer getDamager(){
        return this.damager;
    }
    public Entity getEntity(){
        return this.entity;
    }
}
