package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;


public class MegaGame extends Game {

	public static final String VERSION = "0.0.7 Snipis revenge.";
	public static final String LOG = "projectFinale - ";
	public float SCREENW;
	public float SCREENH;
	public MenuBuilder menuBuilder;
	
	@Override
	public void create() {
		SCREENW = Gdx.graphics.getWidth();
		SCREENH = Gdx.graphics.getHeight();
		System.out.println(SCREENW);
		System.out.println(SCREENH);
		menuBuilder = new MenuBuilder();
		menuBuilder.setStyles();
		menuBuilder.init();
		setScreen(new MenuScreen(this));
	}

	
	public MenuBuilder getMenuBuilder(){
		return menuBuilder;
	}
	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		SCREENW = Gdx.graphics.getWidth();
		SCREENH = Gdx.graphics.getHeight();
	}
	
}