package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;
import com.joojn.client.Utils.STRING;

public class ChatMessageSendEvent extends EventCancelable {

    private STRING message;

    public ChatMessageSendEvent(STRING message) {
        this.message = message;
    }

    public void setMessage(String message){
        this.message.setString(message);
    }

    public String getMessage(){
        return this.message.getString();
    }
}
