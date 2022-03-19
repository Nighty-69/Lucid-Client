package com.joojn.client.GUI;

import org.lwjgl.input.Keyboard;

import com.joojn.client.GUI.Ext.ClientModule;
import com.joojn.client.GUI.Ext.ModOption;
import com.joojn.client.GUI.Managers.GuiManager;
import com.joojn.client.GUI.fontRenderer.GlyphFonts;
import com.joojn.client.Utils.DrawUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class DiscordGUI extends GuiScreen{

	
    private int backgroundColor = new Color(54, 57, 63).getRGB();
    private int color2 = new Color(47, 49, 54).getRGB();
    private int color3 = new Color(32, 34, 37).getRGB();
    private int textBoxColor = new Color(64, 68, 75).getRGB();
    public int textColor = new Color(142, 146, 151).getRGB();
    
    public static GlyphFonts fonts;
    private ClientModule activeModule = null;
    
    private HashMap<ClientModule, float[]> moduleLocations = new HashMap<>();
    
    public DiscordGUI() {
    	fonts = new GlyphFonts();
    }
    
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		Gui.drawFloatRect(width/2F-200F, height/2F-100F, width/2F+200F, height/2F+100F, backgroundColor);
		Gui.drawFloatRect(width/2F-200F, height/2F-100F, width/2F-180F, height/2F+100F, color3);
		Gui.drawFloatRect(width/2F-180F, height/2F-100F, width/2F-120F, height/2F+100F, color2);
		DrawUtil.drawRoundedRect(width/2F+180, height/2F+95F, width/2F-100, height/2F+80,-7, textBoxColor);

		
		GlStateManager.enableLighting();
		fonts.RUBIK.drawString("You do not have permissions to send messages in this channel!", width/2F-95, height/2F+80+fonts.RUBIK.getFontHeight()/2F, new Color(75, 79, 85).getRGB(), false);
		GlStateManager.disableLighting();
		
		
		
		int i = 0;
		for(ClientModule module : GuiManager.getModules()) {
			float[] loc = new float[] { width/2F-178, height/2F-80F + module.getHeight() * i };
			// if mouse over or active module is this module then render white text
			boolean mouseOver = (mouseX > width/2F-180 && mouseX < width/2F-120 + module.getWidth() && mouseY > loc[1] && mouseY < loc[1] + module.getHeight()) || activeModule == module;
			module.render(loc[0], loc[1], mouseOver);
			moduleLocations.put(module, loc);
			i++;
		}

		if(activeModule != null) {
			activeModule.renderOptions(mouseX, mouseY);
		}

		
		// display skin
		mc.getTextureManager().bindTexture(mc.thePlayer.getLocationSkin());
		float scale = 0.5F;
        GlStateManager.scale(scale, scale, scale);
        DrawUtil.setColor(new Color(250, 250, 250).getRGB());
		this.drawTexturedModalRect((width/2F-180)/scale+5,(height/2F+100F -32F*scale)/scale-5,32,32,32,32);
		GlStateManager.scale(2, 2, 2);
		
		// draw name
		fonts.RUBIK.drawString(mc.thePlayer.getName(), width/2F-180+ (32 * scale) + 5, height/2F+100-(32*scale)-fonts.RUBIK.getFontHeight()/2F, -1, true);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		for(ClientModule module : moduleLocations.keySet()) {
			float[] loc = moduleLocations.get(module);
			
			if(mouseX > width/2F-180 && mouseX < width/2F-120 + module.getWidth() && mouseY > loc[1] && mouseY < loc[1] + module.getHeight()) {
				activeModule = module;
			}	
		}
		
		
		if(activeModule != null) {
			for(ModOption option : activeModule.getOptions()) {
				if(mouseX > width/2F-120 && mouseX < width/2F+200 && mouseY > option.getY()-option.getPadding() && mouseY < option.getY() + option.getHeight() + option.getPadding()) {
					option.onClick(mouseX, mouseY, button);
				}
			}
		}
	}
	
	
	@Override
	public void keyTyped(char key, int keyCode) {
        if(keyCode == Keyboard.KEY_ESCAPE) {
            this.mc.displayGuiScreen(null);
        }
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

		for(ClientModule module : GuiManager.getModules()){
			module.saveToFile();
		}
	}
	
	
	public float getTextPosX() {
		return width/2F-80;
	}
	public float getTextPosY() {
		return height/2F+65F;
	}
}
