package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;

public class CustomKeyEvent extends Event {

    private final int key;

    public CustomKeyEvent(int key){
        this.key = key;
    }

    public int getKey(){
        return key;
    }
}
//