package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.controller.MusicController;
import com.flyingPuckGames.projectFinale.controller.MusicController.Tracks;
import com.flyingPuckGames.projectFinale.controller.PlayerController;
import com.flyingPuckGames.projectFinale.model.Level;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.JSONParser;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;
import com.flyingPuckGames.projectFinale.view.PlayerRenderer;
import com.flyingPuckGames.projectFinale.view.WorldRenderer;



/**
 * This is the Game
 * @author flyingPuck(games);
 */
public class MegaGame extends Game {

	public static final String VERSION = "0.1.1 Alpha Footage";
	public static final String LOG = "projectFinale - ";
	public float SCREENW;
	public float SCREENH;

	private PlayerRenderer playerRenderer;
	private Player player;
	private Level level;
	private PlayerController playerController;
	private WorldRenderer worldRenderer;
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
		
		menuController = new MenuController(this);
		musicController = new MusicController(this);
		musicController.setEnabled(true);
		musicController.setVolume(0.2f);
		musicController.play(Tracks.MENU, true);
		
		soundFXController = new MusicController(this);
		soundFXController.setEnabled(true);
		soundFXController.setVolume(1);
		
		loadAll();
		setScreen(new MenuScreen(this));
	}

	private void loadAll() {
		level = new Level(new TmxMapLoader().load(Constants.TEST_TILEMAP_PATH));
		player = new Player(Constants.PLAYER_STARTING_POSITION);
		worldRenderer = new WorldRenderer(level);
		playerRenderer = new PlayerRenderer(player);
		playerController = new PlayerController(player, (TiledMapTileLayer) level.getTiledMap().getLayers().get(0), this);		
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
	public PlayerRenderer getPlayerRenderer() {
		return playerRenderer;
	}

	public void setPlayerRenderer(PlayerRenderer playerRenderer) {
		this.playerRenderer = playerRenderer;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}

	public WorldRenderer getWorldRenderer() {
		return worldRenderer;
	}

	public void setWorldRenderer(WorldRenderer worldRenderer) {
		this.worldRenderer = worldRenderer;
	}

	
}