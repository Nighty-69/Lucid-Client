package com.joojn.client.GUI.Ext;

import net.minecraft.client.Minecraft;

public class IntegerOption extends ModOption{
	
	private int minValue;
	private int maxValue;
	private int value;
	
	public IntegerOption(String name, int minValue, int maxValue, int value) {
		super(name);
		
		this.value = value;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public String getRenderText() {
		return String.format("%s: %s", getName(), this.value);
	}

	@Override
	public void onClick(int mouseX, int mouseY, int button) {
		this.value++;
		if(this.value > this.maxValue) {
			this.value = this.minValue;
		}
	}

	public int getValue() {
		return this.value;
	}

	public int getMaxValue() {
		return this.maxValue;
	}

	public int getMinValue() {
		return this.minValue;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
}
