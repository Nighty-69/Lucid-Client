package com.joojn.client.GUI.Managers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.joojn.client.Event.EventManager;
import com.joojn.client.Event.EventTarget;
import com.joojn.client.Event.impl.CustomKeyEvent;
import com.joojn.client.Event.impl.TickEvent;
import com.joojn.client.GUI.DiscordGUI;
import com.joojn.client.GUI.Instances;
import com.joojn.client.GUI.Ext.BooleanOption;
import com.joojn.client.GUI.Ext.IntegerOption;
import com.joojn.client.GUI.Ext.ClientModule;
import com.joojn.client.GUI.Ext.ModOption;
import com.joojn.client.GUI.Modules.DamageIndicator;
import com.joojn.client.GUI.Modules.FpsMod;

import net.minecraft.client.Minecraft;

public class GuiManager {
	private static Set<ClientModule> modules = new HashSet<>();
	
	private static DamageIndicator damageIndicator = new DamageIndicator("Damage Indicator");
	private static FpsMod fpsMod = new FpsMod("Fps Mod");
	
	public GuiManager() {
		registerMods(damageIndicator);
		registerMods(fpsMod);
		
		
		EventManager.register(damageIndicator);
		
		// load from file
		for(ClientModule module : getModules()){
			module.loadFromFile();
		}
	}
	
	
	@EventTarget
	public void onKey(CustomKeyEvent event) {
		if(event.getKey() == Keyboard.KEY_RSHIFT) {
			Minecraft.getMinecraft().displayGuiScreen(Instances.getDiscordGUI());
		}
	}
	
	public void registerMods(ClientModule... modules) {
		this.modules.addAll(Arrays.asList(modules));
	}
	
	public static Set<ClientModule> getModules(){
		return modules;
	}
	
	
	public static boolean getBooleanFromModule(String module, String option) {
		for(ClientModule module1 : getModules()) {
			if(module1.getName().equalsIgnoreCase(module)) {
				for(ModOption option1 : module1.getOptions()) {
					if(option1.getName().equalsIgnoreCase(option) && option1 instanceof BooleanOption) {
						return ((BooleanOption) option1).isEnabled();
					}
				}
			}
		}
		// if not found, return false
		return false;
	}
	
	public static int getIntFromModule(String module, String option) {
		for(ClientModule module1 : getModules()) {
			if(module1.getName().equalsIgnoreCase(module)) {
				for(ModOption option1 : module1.getOptions()) {
					if(option1.getName().equalsIgnoreCase(option) && option1 instanceof IntegerOption) {
						return ((IntegerOption) option1).getValue();
					}
				}
			}
		}
		// if not found, return -1
		return -1;
	}
}
