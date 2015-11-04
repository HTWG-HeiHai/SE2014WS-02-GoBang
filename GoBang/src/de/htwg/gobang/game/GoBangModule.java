package de.htwg.gobang.game;

import com.google.inject.AbstractModule;

import de.htwg.gobang.controller.IGbLogic;


public class GoBangModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IGbLogic.class).to(de.htwg.gobang.controller.impl.GbLogic.class);
	}
}
