package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.controller.MusicController;
import com.flyingPuckGames.projectFinale.controller.MusicController.Tracks;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.utils.JSONParser;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;



/**
 * This is the Game
 * @author flyingPuck(games);
 */
public class MegaGame extends Game {

	public static final String VERSION = "0.1.1 Alpha Footage";
	public static final String LOG = "projectFinale - ";
	public float SCREENW;
	public float SCREENH;
	private MenuBuilder menuBuilder;
	private Options options;
	private MenuController menuController;
	private MusicController musicController;
	private MusicController soundFXController;

	
	@Override
	public void create() {
		SCREENW = Gdx.graphics.getWidth();
		SCREENH = Gdx.graphics.getHeight();
		
		System.out.println(SCREENW);
		System.out.println(SCREENH);
		
		options = new Options(this);
		options.loadOptions();
		
		new JSONParser().saveItems();

		menuController = new MenuController(this);
		musicController = new MusicController(this);
		musicController.setEnabled(true);
		musicController.setVolume(0.2f);
		musicController.play(Tracks.MENU, true);
		
		soundFXController = new MusicController(this);
		soundFXController.setEnabled(true);
		soundFXController.setVolume(1);
	
		
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
	
	public MusicController getMusicController() {
		return musicController;
	}

	public void setMusicController(MusicController musicController) {
		this.musicController = musicController;
	}

	public MusicController getSoundFXController() {
		return soundFXController;
	}

	public void setSoundFXController(MusicController soundFXController) {
		this.soundFXController = soundFXController;
	}
	
}