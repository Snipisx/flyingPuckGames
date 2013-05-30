package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;


public class MegaGame extends Game {

	public static final String VERSION = "0.0.7 Snipis revenge.";
	public static final String LOG = "projectFinale - ";
	public float SCREENW;
	public float SCREENH;
	private MenuBuilder menuBuilder;
	private Options options;
	private MenuController menuController;
	
	@Override
	public void create() {
		SCREENW = Gdx.graphics.getWidth();
		SCREENH = Gdx.graphics.getHeight();
		System.out.println(SCREENW);
		System.out.println(SCREENH);
		setOptions(new Options());
		setMenuController(new MenuController(this));
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


	public Options getOptions() {
		return options;
	}


	public void setOptions(Options options) {
		this.options = options;
	}


	public MenuController getMenuController() {
		return menuController;
	}


	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}
	
}