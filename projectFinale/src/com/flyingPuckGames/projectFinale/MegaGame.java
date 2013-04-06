package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.Game;
import com.flyingPuckGames.projectFinale.screens.GameScreen;


public class MegaGame extends Game {

	public static final String VERSION = "0.0.4";
	public static final String LOG = "projectFinale - ";
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
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
	}
	
}