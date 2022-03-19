package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import com.joojn.client.Utils.STRING;
public class RenderPlayerNickEvent extends EventCancelable {

    private EntityPlayer player;
    private STRING nick;

    public RenderPlayerNickEvent(STRING nick, Entity entity){
        this.nick = nick;
        this.player = (EntityPlayer) entity;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public String getNick(){
        return this.nick.getString();
    }

    public void setNick(String nick){
        this.nick.setString(nick);
    }

}
