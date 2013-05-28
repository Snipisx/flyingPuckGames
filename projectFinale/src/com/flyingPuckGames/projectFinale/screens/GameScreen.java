package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.Level;
import com.flyingPuckGames.projectFinale.model.Player;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;
import com.flyingPuckGames.projectFinale.view.PlayerRenderer;
import com.flyingPuckGames.projectFinale.view.WorldRenderer;

public class GameScreen implements Screen{
	
	private MegaGame megaGame;
	private Level level;
	private Player player;
	private WorldRenderer worldRenderer;
	private PlayerRenderer playerRenderer;
	
	
	/**
	 * Constructor
	 * @param megaGame
	 */
	public GameScreen(MegaGame megaGame) {
		this.megaGame = megaGame;
		level = new Level(new TmxMapLoader().load(Constants.TEST_TILEMAP_PATH));
		player = new Player(Constants.PLAYER_STARTING_POSITION);
		worldRenderer = new WorldRenderer(level);
		playerRenderer = new PlayerRenderer(player);
	}


	@Override
	public void render(float delta) {
		RenderUtils.clearScreen();
		worldRenderer.update(delta, player.getPosition());
		playerRenderer.update(delta, worldRenderer.getTileRenderer().getSpriteBatch());
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}


	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
	
}
