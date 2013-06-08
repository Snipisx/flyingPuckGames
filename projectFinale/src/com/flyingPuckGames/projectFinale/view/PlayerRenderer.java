package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.utils.Constants;

/**
 * Player renderer.
 * @author flyingPuck(games);
 */
public class PlayerRenderer {
	private Player player;
	private SpriteBatch spriteBatch;
	private Animation idleLeft;
	private Animation idleRight;
	private Animation walkRightAnimation;
	private Animation walkLeftAnimation;
	private TextureRegion thisFrame;
	private float textureWidth;
	private float textureHeight;
	
		
	/**
	 * Constructor
	 * @param player
	 */
	public PlayerRenderer(Player player) {
		this.player = player;
		spriteBatch = new SpriteBatch();
		textureWidth = player.getBoundsWidth() * 1.23f;
		textureHeight = player.getBoundsHeight() * 0.84f;
		
		//RightAnimation
		TextureRegion[] walkRightFrames = new TextureRegion[6];
		for(int i = 0; i < 6; i++){
			walkRightFrames[i] = new TextureRegion(new Texture(Gdx.files.internal("data/ProtoAnimation000" + (i) + ".png")));
			System.out.println("ProtoAnimation000" + (i));

		}
		walkRightAnimation = new Animation(0.09f, walkRightFrames);
		
		//LeftAnimation
		TextureRegion[] walkLeftFrames = new TextureRegion[6];
		for(int i = 0; i < 6; i++){
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}
		walkLeftAnimation = new Animation(0.09f, walkLeftFrames);
	}
	
	/**
	 * Metod called to update state of playerRenderer.
	 * @param delta
	 * @param batchContext, context where the player is gonna be drawn.
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
			thisFrame = walkRightAnimation.getKeyFrame(player.getStateTime(), true);
		} else {
			thisFrame = walkLeftAnimation.getKeyFrame(player.getStateTime(), true);
		}
		spriteBatch.draw(thisFrame, player.getPosition().x, player.getPosition().y, textureWidth, textureHeight);
		spriteBatch.end();
	}
}
