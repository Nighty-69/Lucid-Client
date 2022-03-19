package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;
import com.joojn.client.Utils.STRING;

public class RenderScoreboardEvent extends EventCancelable {

    private STRING name;

    public RenderScoreboardEvent(STRING player) {
        this.name = player;
    }

    public void setName(String name){
        this.name.setString(name);
    }

    public String getName(){
        return this.name.getString();
    }
}
