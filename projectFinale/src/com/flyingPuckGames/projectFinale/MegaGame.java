package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.OldGameScreen;


public class MegaGame extends Game {

	public static final String VERSION = "0.0.5 preMVC";
	public static final String LOG = "projectFinale - ";
	public float SCREENW;
	public float SCREENH;
	
	@Override
	public void create() {
		SCREENW = Gdx.graphics.getWidth();
		SCREENH = Gdx.graphics.getHeight();
		System.out.println(SCREENW);
		System.out.println(SCREENH);
		setScreen(new OldGameScreen(this));
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