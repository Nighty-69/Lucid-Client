package com.joojn.client.GUI.Ext;

import net.minecraft.client.Minecraft;

public class BooleanOption extends ModOption{

	private boolean enabled;
	
	public BooleanOption(String name, boolean enabled) {
		super(name);
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	
	public void toggle() {
		this.enabled = !this.enabled;
	}
	
	
	@Override
	public String getRenderText() {
		return String.format("%s: %s", getName(), isEnabled() ? "On" : "Off");
	}
	
	
	@Override
	public void onClick(int mouseX, int mouseY, int button) {
		this.toggle();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
