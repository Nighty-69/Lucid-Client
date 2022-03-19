package com.joojn.client.GUI.Modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

import com.joojn.client.Event.EventTarget;
import com.joojn.client.Event.impl.RenderPlayerNickEvent;
import com.joojn.client.GUI.Instances;
import com.joojn.client.GUI.Ext.BooleanOption;
import com.joojn.client.GUI.Ext.ClientModule;
import com.joojn.client.GUI.Managers.GuiManager;


public class DamageIndicator extends ClientModule {

	public DamageIndicator(String name) {
		super(name);
		
		addOption(new BooleanOption("Enabled", true));
		addOption(new BooleanOption("Display Potions", true));
	}
	
	@EventTarget
	public void onNickRender(RenderPlayerNickEvent event){
		if(!GuiManager.getBooleanFromModule(getName(), "Enabled")) return;

		EntityPlayer player = event.getPlayer();

		int maxHearts = (int) Math.floor(player.getMaxHealth() / 2);
		int hearts = (int) Math.floor((player).getHealth() / 2);
		int fullHealth = (int) Math.floor(player.getHealth());
		boolean halfHeart = fullHealth % 2 != 0;

		HashMap<String, int[]> heartTextures = new HashMap<String, int[]>();
		heartTextures.put("heart", new int[] {52, 0});
		heartTextures.put("half_heart", new int[] {61, 0});
		heartTextures.put("empty_heart", new int[] {16, 0});

		// works only sometimes
		if(GuiManager.getBooleanFromModule(getName(), "Display Potions")){
			if(player.isPotionActive(Potion.poison)){
				heartTextures.replace("heart", new int[] {88, 0});
				heartTextures.replace("half_heart", new int[] {97, 0});
			}
			if(player.isPotionActive(Potion.wither)){
				heartTextures.replace("heart", new int[] {124, 0});
				heartTextures.replace("half_heart", new int[] {133, 0});
			}
		}

		GlStateManager.enableDepth();
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/icons.png"));

		// render background hearts
		for (int in = 0; in < maxHearts; in++) {
			int newX = 9 * in - 5 * 9;
			Instances.getDiscordGUI().drawTexturedModalRect(newX,-13, heartTextures.get("empty_heart")[0], heartTextures.get("empty_heart")[1],9,9);
		}
		// render players hearts
		int in;
		for (in = 0; in < hearts; in++) {
			int newX = 9 * in - 5 * 9;
			Instances.getDiscordGUI().drawTexturedModalRect(newX, -13, heartTextures.get("heart")[0], heartTextures.get("heart")[1], 9, 9);
		}
		// render half heart
		if(halfHeart){
			int newX = 9 * in - 5 * 9;
			Instances.getDiscordGUI().drawTexturedModalRect(newX, -13, heartTextures.get("half_heart")[0], heartTextures.get("half_heart")[1], 9, 9);
		}
		GlStateManager.enableBlend();
	}
}
