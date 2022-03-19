package com.joojn.client.GUI.Ext;

import java.awt.Color;

import com.joojn.client.GUI.Instances;
import com.joojn.client.GUI.Managers.GuiManager;
import com.joojn.client.GUI.fontRenderer.GlyphPageFontRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class ModOption {

	// transient => gson ignore
	private transient String name;
	private transient float posX = 0;
	private transient float posY = 0;
	private transient GlyphPageFontRenderer font;
	
	public ModOption(String name) {
		this.name = name;
		
		this.font = Instances.getDiscordGUI().fonts.RUBIK;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public int getWidth() {
		return font.getStringWidth(this.getRenderText());
	}


	
	public void renderPos(float posX, float posY, boolean mouseOver) {
		this.posX = posX;
		this.posY = posY;
		
		this.render(posX, posY, mouseOver);
	}
	
	
	public String getRenderText() {
		return this.name;
	}
	
	
	public void render(float posX, float posY, boolean mouseOver) {	
		float[] loc = new float[] { Instances.getDiscordGUI().width/2F-120F, Instances.getDiscordGUI().width/2F+200F };
		if(mouseOver) {
			Gui.drawFloatRect(loc[0], posY-this.getPadding(), loc[1], posY+this.getHeight()+this.getPadding(), new Color(50, 53, 59).getRGB());
		}
		
		font.drawString(this.getRenderText(), posX, posY, -1, true);
	}

	public void onClick(int mouseX, int mouseY, int button) {};
	
	public float getX() {
		return this.posX;
	}
	
	public float getY() {
		return this.posY;
	}
	
	public int getHeight() {
		return font.getFontHeight();
	}
	
	public int getPadding() {
		return 5;
	}
	
}
