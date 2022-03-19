package com.joojn.client.GUI.Ext;

import java.util.List;
import java.util.Set;

import com.joojn.client.FileManager.FileManager;
import com.joojn.client.GUI.DiscordGUI;
import com.joojn.client.GUI.Instances;
import com.joojn.client.GUI.Managers.GuiManager;
import com.joojn.client.GUI.fontRenderer.GlyphPageFontRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

public class ClientModule {
	
	private String name;
	private Set<ModOption> options = new HashSet<>();
	
	private float posX;
	private float posY;
	
	public ClientModule(String name) {
		this.name = name;
	}
	
	public void addOption(ModOption... options) {
		this.options.addAll(Arrays.asList(options));
	}
	
	
	public String getRenderText() {
		return "# "+name.replace(' ', '-').toLowerCase();
	}
	
	
	public void render(float posX, float posY, boolean mouseOver) {
		GlyphPageFontRenderer font = DiscordGUI.fonts.ARIAL_TEXTBOX;
		int color = mouseOver ? -1 : Instances.getDiscordGUI().textColor;
		
		if(mouseOver) {
			float[] loc = new float[] { Instances.getDiscordGUI().width/2F-180, Instances.getDiscordGUI().width/2F-120};
			Gui.drawFloatRect(loc[0], posY, loc[1], posY+getHeight(), new Color(52,55, 60).getRGB());
		}

		
		font.drawString(getRenderText(), posX, posY, color, false);
	}
	
		

	public Set<ModOption> getOptions() {
		return this.options;
	}
	public String getName() {
		return this.name;
	}
	
	public int getWidth() {
		return DiscordGUI.fonts.ARIAL_TEXTBOX.getStringWidth(getRenderText());
	}
	
	public int getHeight() {
		return DiscordGUI.fonts.ARIAL_TEXTBOX.getFontHeight();
	}

	public void renderOptions(float mouseX, float mouseY) {
		int i = 0;
		for(ModOption option : options) {
			float[] loc = new float[] { Instances.getDiscordGUI().getTextPosX(), Instances.getDiscordGUI().getTextPosY() - (option.getHeight() + option.getPadding() * 2) * i};
			float[] loc2 = new float[] {Instances.getDiscordGUI().width/2F-120, Instances.getDiscordGUI().width/2F+200 };
			
			boolean mouseOver = mouseX > loc2[0] && mouseX < loc2[1] && mouseY > loc[1]-option.getPadding() && mouseY < loc[1] + getHeight() + option.getPadding();
			
			option.renderPos(loc[0], loc[1], mouseOver);
			i++;
		}
	}

	public void loadFromFile() {
		File f = new File(FileManager.getModsDirectory(), this.getName());

		for(ModOption option : this.options){
			if(option instanceof BooleanOption){
				BooleanOption op = FileManager.readFromJson(new File(f, option.getName() + ".json"), BooleanOption.class);
				if(op == null) continue;
				((BooleanOption) option).setEnabled(op.isEnabled());
			}else if(option instanceof IntegerOption){
				IntegerOption op = FileManager.readFromJson(new File(f, option.getName() + ".json"), IntegerOption.class);
				if(op == null) continue;
				((IntegerOption) option).setValue(op.getValue());
				((IntegerOption) option).setMinValue(op.getMinValue());
				((IntegerOption) option).setMaxValue(op.getMaxValue());
			}
		}
	}

	public void saveToFile(){
		File f = new File(FileManager.getModsDirectory(), this.getName());

		for(ModOption option : this.options){
			FileManager.writeJsonToFile(new File(f, option.getName() + ".json"), option);
		}
	}

}
