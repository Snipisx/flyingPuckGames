package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flyingPuckGames.projectFinale.model.World;

public class PlayerRenderer {
	private World world;
	private SpriteBatch spriteBatch;
	private Texture playerTexture;
	private float ppuX;	//Pixels per unit X-axis
	private float ppuY; //Pixels per unit Y-axis
	
	public PlayerRenderer(World world) {
		super();
		this.world = world;
		playerTexture =  new Texture(Gdx.files.internal("data/plahCharacter.png"));
		spriteBatch = new SpriteBatch();

	}
	public void drawPlayer(){

		if (world.getPlayer().isFacesRight()) {
			spriteBatch.begin();
			spriteBatch.draw(
					playerTexture,
					world.getPlayer().getPosition().x * ppuX,
					world.getPlayer().getPosition().y * ppuY,
					world.getPlayer().getWsize()*ppuX,
					world.getPlayer().getHsize()*ppuY
			);
			spriteBatch.end();
		} else {
			spriteBatch.begin();
			spriteBatch.draw(playerTexture,
					world.getPlayer().getPosition().x ,
					world.getPlayer().getPosition().y ,
					-world.getPlayer().getWsize()*ppuX,
					world.getPlayer().getHsize()*ppuY
			);
			spriteBatch.end();
		}
	}

}
