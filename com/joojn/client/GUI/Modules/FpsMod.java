package com.joojn.client.GUI.Modules;

import com.joojn.client.GUI.Ext.BooleanOption;
import com.joojn.client.GUI.Ext.ClientModule;
import com.joojn.client.GUI.Ext.IntegerOption;

public class FpsMod extends ClientModule{

	public FpsMod(String name) {
		super(name);
		
		addOption(new BooleanOption("Enabled", true));
	}
}
