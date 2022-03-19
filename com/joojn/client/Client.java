package com.joojn.client;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.joojn.client.Event.EventManager;
import com.joojn.client.GUI.Instances;
import com.joojn.client.Utils.TaskUtil;

import net.minecraft.client.Minecraft;

public class Client {
	public static final String clientDisplayName = "clientname";

	private static final Client INSTANCE = new Client();
	public static Client getInstance() {
		return INSTANCE;
	}


	public void init() throws LWJGLException {
		EventManager.register(new Instances());
		EventManager.register(new TaskUtil());
		
		// borderless screen
		if (Minecraft.getMinecraft().isFullScreen()) {
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			Display.setLocation(0, 0);
			Display.setFullscreen(false);
			Display.setResizable(false);
		} else {
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
			Display.setDisplayMode(new DisplayMode(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight));
			Display.setResizable(true);
		}
	}
	public void shutdown(){
	}
}
