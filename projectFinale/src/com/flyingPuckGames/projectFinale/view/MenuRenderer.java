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
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;

public class MenuRenderer {
	
	private MegaGame megaGame;
	private Stage stage;
	private OrthographicCamera camera;
	private Boolean onGameMenu;
	private static final float CAMERA_WIDTH = 20f;
	private static final float CAMERA_HEIGHT = 11f;
	private float W;
	private float H;

	public MenuRenderer(MegaGame megaGame){
		this.megaGame = megaGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		camera.update();
		setSize(megaGame.SCREENW, megaGame.SCREENH);
		onGameMenu(false);
//		loadTextures();
	}
	
	public void setSize (float w, float h){
		this.W = w;
		this.H = h;
	}
		

	public void render(float delta) {
		
		if(!onGameMenu){
			RenderUtils.clearScreen();
		}
		
		
		camera.update();
		stage.act(delta);
		stage.draw();
		
	}
	

	public void setBackground(){
	}
	
	public void setStage(Stage actor){
		stage = actor;
		stage.setKeyboardFocus(stage.getActors().first());
		Gdx.input.setInputProcessor(stage);
	}
	public void onGameMenu(Boolean menu){
		onGameMenu = menu;
	}
	

	

}
