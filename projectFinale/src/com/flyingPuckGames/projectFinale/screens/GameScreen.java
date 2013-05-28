package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.controller.PlayerController;
import com.flyingPuckGames.projectFinale.model.Level;
import com.flyingPuckGames.projectFinale.model.Player;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;
import com.flyingPuckGames.projectFinale.utils.RenderUtils;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;
import com.flyingPuckGames.projectFinale.view.PlayerRenderer;
import com.flyingPuckGames.projectFinale.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor{
	
	private MegaGame 			megaGame; //TODO @Refactor Name
	private Level 				level;	//TODO @Refactor Name
	private Player 				player;
	private WorldRenderer		worldRenderer;
	private PlayerRenderer 		playerRenderer;
	private MenuRenderer	 	menuRenderer;
	private MenuBuilder			menuBuilder;
	private Stage				stage; //TODO @Refactor Name
	private boolean 			onMenu;
	private Integer				contEsc;
	private PlayerController	playerController; //TODO Über refactor
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
		menuRenderer = new MenuRenderer(megaGame);
		playerController	= new PlayerController(player);
		menuBuilder = new MenuBuilder();
		menuBuilder.setStyles();
		menuBuilder.init();
		contEsc = 0;
		setInputProcessor();
	}


	@Override
	public void render(float delta) {
		
		if(!onMenu){
			playerController.update(delta);
			RenderUtils.clearScreen();
			worldRenderer.update(delta, player.getPosition());
			playerRenderer.update(delta, worldRenderer.getTileRenderer().getSpriteBatch());
		}else{
			worldRenderer.update(delta, player.getPosition());
			menuRenderer.render(delta);
		}
	}

	@Override
	public void resize(int width, int height) {
		
		worldRenderer.setActualWindowSize();
		menuRenderer.setSize(width, height);
	}

	@Override
	public void show() {
		//Ignored
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}


	@Override
	public void pause() {
		//Ignored
	}

	@Override
	public void resume() {
		//Ignored
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	public void setInputProcessor(){
		Gdx.input.setInputProcessor(this);
	}
	
	private void setOnMenu(){
		playerController.rightReleased();
		playerController.leftReleased();
		stage = new Stage();
		stage.addActor(menuBuilder.statusMenu(this));
		menuRenderer.setStage(stage);
		menuRenderer.setBackground();
		onMenu = true;
		menuRenderer.onGameMenu(true);
	}
	
	public void changeMenuStatus(int menu){
		switch(menu){
			case 1:
				stage = new Stage();
				stage.addActor(menuBuilder.equipMenu(this));
				menuRenderer.setStage(stage);
				break;
				
			case 2:
				stage = new Stage();
				stage.addActor(menuBuilder.optionMenu(this));
				menuRenderer.setStage(stage);
				break;
				
			case 3:
				break;
				
			case 4:
				stage = new Stage();
				stage.addActor(menuBuilder.statusMenu(this));
				menuRenderer.setStage(stage);
				break;
		}
	}
	
	public void changeMenu(int menu){
		switch(menu){
		case 1: 
			setOnMenu();
			break;
			
		case 2:
			stage = new Stage();
			stage.addActor(menuBuilder.optionMenu(this));
			menuRenderer.setStage(stage);
			break;
			
		case 3:
			//TODO GRIMORIO
			break;
			
		case 4:
			onMenu = false;
			setInputProcessor();
			break;
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			playerController.leftPressed();
		if (keycode == Keys.RIGHT)
			playerController.rightPressed();
		if (keycode == Keys.Z)
			playerController.jumpPressed();
		if (keycode == Keys.X)
			playerController.firePressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		System.out.println("hola");
		if (keycode == Keys.LEFT)
			playerController.leftReleased();
		if (keycode == Keys.RIGHT)
			playerController.rightReleased();
		if (keycode == Keys.Z)
			playerController.jumpReleased();
		if (keycode == Keys.X)
			playerController.fireReleased();
		if (keycode == Keys.D){
			System.out.println("psdfa");
		}
		if (keycode == Keys.ESCAPE){
			if(contEsc == 1){
				contEsc = 0;
			}else{
				contEsc = 1;
				setOnMenu();
			}
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		//Ignored
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (screenX < Gdx.graphics.getWidth() / 2 && screenY > Gdx.graphics.getHeight() / 2) {
//			controller.leftPressed();
		}
		if (screenX > Gdx.graphics.getWidth() / 2 && screenY > Gdx.graphics.getHeight() / 2) {
//			controller.rightPressed();
		}
		
		if (screenX < Gdx.graphics.getWidth() * 0.10f && screenY > Gdx.graphics.getHeight() * 0.90f) {
			setOnMenu();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (screenX < Gdx.graphics.getWidth() / 2 && screenY > Gdx.graphics.getHeight() / 2) {
//			controller.leftReleased();
		}
		
		if (screenX > Gdx.graphics.getWidth() / 2 && screenY > Gdx.graphics.getHeight() / 2) {
//			controller.rightReleased();
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		//Ignored
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		//Ignored
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		//Ignored
		return false;
	}
	
	//Getters & setters ---------
	
	public Integer getContEsc() {
		return contEsc;
	}
	
	
	public void setContEsc(Integer contEsc) {
		this.contEsc = contEsc;
	}
	
}
