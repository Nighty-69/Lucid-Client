package com.joojn.client.GUI;

import com.joojn.client.Event.EventManager;
import com.joojn.client.Event.EventTarget;
import com.joojn.client.Event.impl.StartEvent;
import com.joojn.client.GUI.Managers.GuiManager;

public class Instances {
	
	
	private static GuiManager guiManager;
	private static DiscordGUI discordGUI;
	
	@EventTarget
	public void onStart(StartEvent event) {
		
		discordGUI = new DiscordGUI();
		guiManager = new GuiManager();
		
		
		EventManager.register(guiManager);
	}
	
	public static DiscordGUI getDiscordGUI() {
		return discordGUI;
	}
	

}
