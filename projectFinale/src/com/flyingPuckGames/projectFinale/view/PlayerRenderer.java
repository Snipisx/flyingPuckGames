package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.model.player.Player.State;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;

/**
 * Player renderer.
 * @author flyingPuck(games);
 */
public class PlayerRenderer {
	private Player player;
	private SpriteBatch spriteBatch;
	private Animation idleLeft;
	private Animation idleRight;
	private Animation walkingLeft;
	private Animation walkingRight;
	private Animation fallingLeft;
	private Animation fallingRight;
	private TextureRegion jumpingLeft;
	private TextureRegion jumpingRight;
	private TextureRegion actualStateFrame;
	private float textureWidth;
	private float textureHeight;
	
		
	/**
	 * Constructor
	 * @param player
	 */
	public PlayerRenderer(Player player) {
		this.player = player;
		loadTextures();
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
	 * Loads in memory every basic player mechanic animation .
	 */
	private void loadTextures(){
		spriteBatch = new SpriteBatch();
		textureWidth = player.getBoundsWidth();
		textureHeight = player.getBoundsHeight() ;
		
		//RightWalkingAnimation
		TextureRegion[] walkRightFrames = new TextureRegion[6];
		for(int i = 0; i < 6; i++){
			walkRightFrames[i] = RenderUtils.localAsset("player/Walking" + "000" + (i) + Constants.ASSET_FORMAT);
		}
		walkingRight = new Animation(0.12f, walkRightFrames);
		
		//LeftWalkingAnimation
		TextureRegion[] walkLeftFrames = new TextureRegion[6];
		for(int i = 0; i < 6; i++){
			walkLeftFrames[i] = new TextureRegion(walkRightFrames[i]);
			walkLeftFrames[i].flip(true, false);
		}
		walkingLeft = new Animation(0.12f, walkLeftFrames);
		
		//RightIdleAnimation
		TextureRegion[] idleRightFrames = new TextureRegion[4];
		for(int i = 0; i < 4; i++){
			idleRightFrames[i] = RenderUtils.localAsset("player/Idle" + "000" + (i) + Constants.ASSET_FORMAT);
		}
		idleRight = new Animation(0.5f, idleRightFrames);
		
		//LeftWalkingAnimation
		TextureRegion[] idleLeftFrames = new TextureRegion[4];
		for(int i = 0; i < 4; i++){
			idleLeftFrames[i] = new TextureRegion(idleRightFrames[i]);
			idleLeftFrames[i].flip(true, false);
		}
		idleLeft = new Animation(0.5f, idleLeftFrames);
		
		//RightFallingAnimation
		TextureRegion[] fallingRightFrames = new TextureRegion[2];
		for(int i = 0; i < 2; i++){
			fallingRightFrames[i] = RenderUtils.localAsset("player/Falling" + "000" + (i) + Constants.ASSET_FORMAT);
		}
		fallingRight = new Animation(0.5f, fallingRightFrames);
		
		//RightFallingAnimation
		TextureRegion[] fallingLeftFrames = new TextureRegion[2];
		for(int i = 0; i < 2; i++){
			fallingLeftFrames[i] = new TextureRegion(fallingRightFrames[i]);
			fallingLeftFrames[i].flip(true, false);
		}
		fallingLeft = new Animation(0.5f, fallingLeftFrames);
		
		
		jumpingRight = RenderUtils.localAsset("player/Jump" + "0000" + Constants.ASSET_FORMAT);
		jumpingLeft = new TextureRegion(jumpingRight);;
		jumpingLeft.flip(true, false);
	}
	
	/**
	 * Draws actual state of player.
	 */
	public void renderPlayer(){
		spriteBatch.begin();
		spriteBatch.draw(decideFrame(), player.getPosition().x, player.getPosition().y, textureWidth, textureHeight);
		spriteBatch.end();
	}
	
	/**
	 * Decides what frame is needed to print.
	 * @return
	 */
	private TextureRegion decideFrame() {
		
		if (player.isFacesRight() && player.getState() == State.IDLE && player.getVelocity().y == 0) {
			return actualStateFrame = idleRight.getKeyFrame(player.getStateTime(), true);
		} 
		else if (!player.isFacesRight() && player.getState() == State.IDLE && player.getVelocity().y == 0) {
			return  actualStateFrame = idleLeft.getKeyFrame(player.getStateTime(), true);
		} 
		else if (player.isFacesRight() && player.getState() == State.WALKING && player.getVelocity().y == 0) {
			return actualStateFrame = walkingRight.getKeyFrame(player.getStateTime(), true);
		} 
		else if (!player.isFacesRight() && player.getState() == State.WALKING && player.getVelocity().y == 0) {
			return actualStateFrame = walkingLeft.getKeyFrame(player.getStateTime(), true);
		} 
		else if (player.isFacesRight() && player.getVelocity().y > 0) {
			return actualStateFrame = jumpingRight;
		} 
		else if (!player.isFacesRight() && player.getVelocity().y > 0) {
			return actualStateFrame = jumpingLeft;
		}
		else if (player.isFacesRight() && player.getVelocity().y < 0) {
			return actualStateFrame = fallingRight.getKeyFrame(player.getStateTime(), true);
		} 
		else if (!player.isFacesRight() && player.getVelocity().y < 0) {
			return actualStateFrame = fallingLeft.getKeyFrame(player.getStateTime(), true);
		}
		
		return actualStateFrame;
	}
	
}
