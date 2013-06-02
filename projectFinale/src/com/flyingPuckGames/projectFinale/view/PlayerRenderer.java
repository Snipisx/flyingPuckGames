package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.utils.Constants;

/**
 * Player renderer.
 * @author flyingPuck(games);
 */
public class PlayerRenderer {
	private Player player;
	private SpriteBatch spriteBatch;
	private Texture playerTexture;
	private float textureWidth;
	private float textureHeight;
	
	
	/**
	 * Constructor
	 * @param player
	 */
	public PlayerRenderer(Player player) {
		super();
		this.player = player;
		playerTexture = player.getTexture();
		spriteBatch = new SpriteBatch();
		textureWidth = Constants.PLAYER_WIDTH_IN_UNITS * playerTexture.getWidth();
		textureHeight = Constants.PLAYER_HEIGHT_IN_UNITS * playerTexture.getHeight();
	}
	
	/**
	 * Metod called to update state of playerRenderer.
	 * @param delta
	 */
	public void update(float delta, SpriteBatch batchContext){
		spriteBatch = batchContext;
		renderPlayer();
	}
	
	/**
	 * Draws actual state of player.
	 */
	public void renderPlayer(){
		spriteBatch.begin();
		if (player.isFacesRight()) {
			spriteBatch.draw(playerTexture, player.getPosition().x, player.getPosition().y, textureWidth, textureHeight);
		} else {
			spriteBatch.draw(playerTexture, player.getPosition().x + textureWidth, player.getPosition().y, -textureWidth, textureHeight);
		}
		spriteBatch.end();
	}
}
