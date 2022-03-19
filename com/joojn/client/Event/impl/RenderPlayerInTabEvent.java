package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;
import com.joojn.client.Utils.STRING;


public class RenderPlayerInTabEvent extends EventCancelable {

    private STRING displayName;
    private STRING name;

    public RenderPlayerInTabEvent(STRING displayName, STRING name) {
        this.displayName = displayName;
        this.name = name;
    }

    public String getDisplayName(){
        return this.displayName.getString();
    }

    public String getName(){
        return this.name.getString();
    }

    public void setName(String name){
        this.displayName.setString(name);
    }

}
