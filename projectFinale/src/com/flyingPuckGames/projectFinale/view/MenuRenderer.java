package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.World;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MenuRenderer {
	
	private MegaGame megaGame;
	private MenuScreen menuScreen;
	private OrthographicCamera camera;
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private float W;
	private float H;
	private float ppuX;	//Pixels per unit X-axis
	private float ppuY; //Pixels per unit Y-axis
	
	public MenuRenderer(MegaGame megaGame,MenuScreen menuScreen){
		this.megaGame = megaGame;
		this.menuScreen = menuScreen;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.update();
		setSize(megaGame.SCREENW, megaGame.SCREENH);
		
//		font = new BitmapFont();
//		loadTextures();
//		spriteBatch = new SpriteBatch();
//		debug = true;
		System.out.println("CameraX: " + camera.position.x + "\nCameraY:" + camera.position.y);
	}
	
	public void setSize (float w, float h){
		this.W = w;
		this.H = h;
		ppuX = (float)W / CAMERA_WIDTH;
		ppuY = (float)H / CAMERA_HEIGHT;
	}
		

	public void render(float delta) {
		clearScreen();
		camera.update();
		
		
		menuScreen.stage.act(delta);
		menuScreen.stage.draw();
		
		//System.out.println("X:" + world.getPlayer().getPosition().x + "\nY:" + world.getPlayer().getPosition().y);
		System.out.println(ppuX + "-" + ppuY);
	}
	
	public void renderGameMenu(float delta) {
		clearScreen();
	}
	
	/**
	 * This method clears the screen.
	 */
	private void clearScreen() {
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		System.out.println("HolaClear");
	}

	

}
