package com.joojn.client.Event.impl;

import com.joojn.client.Event.Event;
import com.joojn.client.Event.EventCancelable;

import net.minecraft.entity.Entity;

public class ParticleShowEvent extends EventCancelable {

	private Entity target;

	public ParticleShowEvent(Entity target) {
		this.target = target;
	}

	public Entity getEntity() {
		return this.target;
	}

}
