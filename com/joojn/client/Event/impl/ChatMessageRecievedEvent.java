package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChatMessageRecievedEvent extends EventCancelable {

    private ChatComponentText chatMessage;

    public ChatMessageRecievedEvent(ChatComponentText chatComponent) {
        this.chatMessage = chatComponent;
    }


    public void setMessage(String message){
        this.chatMessage.setFormattedText(message);
    }

    public String getMessage(){
        return this.chatMessage.getFormattedText();
    }
}
