package com.flyingPuckGames.projectFinale.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;

public class MenuRenderer {
	
	private MegaGame megaGame;
	private Stage stage;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private Image background;
	private Boolean onGameMenu;
	private Boolean onMainMenu;
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private float W;
	private float H;
	private float ppuX;	//Pixels per unit X-axis
	private float ppuY; //Pixels per unit Y-axis
	
	public MenuRenderer(MegaGame megaGame){
		this.megaGame = megaGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.update();
		setSize(megaGame.SCREENW, megaGame.SCREENH);
		onMainMenu(true);
		setBackground();
//		loadTextures();
		spriteBatch = new SpriteBatch();
	}
	
	public void setSize (float w, float h){
		this.W = w;
		this.H = h;
		ppuX = W / CAMERA_WIDTH;
		ppuY = H / CAMERA_HEIGHT;
	}
		

	public void render(float delta) {
		
		if(!onGameMenu){
			RenderUtils.clearScreen();
		}
		
		spriteBatch.begin();
		background.draw(spriteBatch, 0.5f);
		spriteBatch.end();
		
		camera.update();
		stage.act(delta);
		stage.draw();
		
		
		//System.out.println(ppuX + "-" + ppuY);
	}
	

	public void setBackground(){
		background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/negro.png")))));
		background.setBounds(0, 0,megaGame.SCREENW, megaGame.SCREENH);
		background.setColor(Color.valueOf("3d3d3d"));
	}
	
	public void setStage(Stage actor){
		stage = actor;
		stage.setKeyboardFocus(stage.getActors().first());
		Gdx.input.setInputProcessor(stage);
	}
	public void onGameMenu(Boolean menu){
		onGameMenu = menu;
	}
	
	public void onMainMenu(Boolean menu){
		onMainMenu = menu;
	}

	

}
