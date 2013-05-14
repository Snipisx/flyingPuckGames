package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.World;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MenuRenderer {
	
	private MegaGame megaGame;
	private Stage stage;
	private OrthographicCamera camera;
	private Stage background;
	private SpriteBatch spriteBatch;
	private Actor actor;
	private Image img;
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private float W;
	private float H;
	private float ppuX;	//Pixels per unit X-axis
	private float ppuY; //Pixels per unit Y-axis
	private int i;
	
	public MenuRenderer(MegaGame megaGame){
		this.megaGame = megaGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.update();
		setSize(megaGame.SCREENW, megaGame.SCREENH);
		background = new Stage();
//		font = new BitmapFont();
//		loadTextures();
		spriteBatch = new SpriteBatch();
//		debug = true;
		System.out.println("CameraX: " + camera.position.x + "\nCameraY:" + camera.position.y);
		background.setViewport(W, H, true);
		actor = new Actor();
		actor.setColor(0, 0, 0, 0.5f);
		actor.setSize(W,H);
		img = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/negro.png")))));
		img.setColor(0, 0, 0, 0.1f);
		i = 0;
	}
	
	public void setSize (float w, float h){
		this.W = w;
		this.H = h;
		ppuX = (float)W / CAMERA_WIDTH;
		ppuY = (float)H / CAMERA_HEIGHT;
	}
		

	public void render(float delta) {
		clearScreen();
		

		spriteBatch.begin();
		img.draw(spriteBatch, 0.2f);
		spriteBatch.end();
		
		camera.update();
		stage.act(delta);
		stage.draw();
		System.out.println(ppuX + "-" + ppuY);
	}
	

	/**
	 * This method clears the screen.
	 */
	private void clearScreen() {
		
		Gdx.graphics.getGL20().glBlendColor(0,0,0,0.5f);
		//Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		System.out.println("HolaClear");
	}
	
	public void setStage(Stage actor){
		stage = actor;
		
		Gdx.input.setInputProcessor(stage);
	}
	
	public void setBackground(Pixmap back){

	}
	

}
