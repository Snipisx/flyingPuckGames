package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.controller.OldPlayerController;
import com.flyingPuckGames.projectFinale.model.Level;
import com.flyingPuckGames.projectFinale.model.player.Player;
import com.flyingPuckGames.projectFinale.model.player.PlayerStatus;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.JSONParser;
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
	private boolean 			onMenu;
	private Integer				contEsc;
	private OldPlayerController	oldPlayerController; //TODO Über refactor
	private MenuController		menuController;
	/**
	 * Constructor
	 * @param megaGame
	 */
	public GameScreen(MegaGame megaGame) {
		
		this.megaGame = megaGame;
		level = new Level(new TmxMapLoader().load(Constants.TEST_TILEMAP_PATH));
		//player = new Player(Constants.PLAYER_STARTING_POSITION);
		JSONParser a = new JSONParser();
		player = a.loadPlayer();
		worldRenderer = new WorldRenderer(level);
		playerRenderer = new PlayerRenderer(player);
		menuRenderer = new MenuRenderer(megaGame);
		menuController = megaGame.getMenuController();
		menuController.setGame(this);
		oldPlayerController	= new OldPlayerController(player);
		contEsc = 0;
		setInputProcessor();
	}


	@Override
	public void render(float delta) {
		
		if(!onMenu){
			oldPlayerController.update(delta);
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
	
	public void setMenu(Stage stage){
		oldPlayerController.rightReleased();
		oldPlayerController.leftReleased();
		menuRenderer.setStage(stage);
	}
	
	

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			oldPlayerController.leftPressed();
		if (keycode == Keys.RIGHT)
			oldPlayerController.rightPressed();
		if (keycode == Keys.Z)
			oldPlayerController.jumpPressed();
		if (keycode == Keys.X){
			oldPlayerController.firePressed();
			JSONParser parser1 = new JSONParser();
			parser1.savePlayer(player);
			System.out.println("save player");
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		System.out.println("hola");
		if (keycode == Keys.LEFT)
			oldPlayerController.leftReleased();
		if (keycode == Keys.RIGHT)
			oldPlayerController.rightReleased();
		if (keycode == Keys.Z)
			oldPlayerController.jumpReleased();
		if (keycode == Keys.X)
			oldPlayerController.fireReleased();
		if (keycode == Keys.D){
			
		}
		if (keycode == Keys.ESCAPE){
			if(contEsc == 1){
				contEsc = 0;
			}else{
				contEsc = 1;
				menuController.status(4);
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
			menuController.status(1);
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

	public void setOnMenu(boolean menu) {
		onMenu = menu;
		menuRenderer.onGameMenu(menu);
	}
	
	public PlayerStatus getPlayerStatus(){
		return player.getStatus();
	}
	
}
