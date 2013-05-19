package com.flyingPuckGames.projectFinale.screens;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.controller.PlayerController;
import com.flyingPuckGames.projectFinale.model.World;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;
import com.flyingPuckGames.projectFinale.view.WorldRenderer;

public class GameScreen implements Screen,InputProcessor {

	private World 				world;
	private WorldRenderer 		rendererGame;
	private MegaGame			megaGame;
	private MenuRenderer		rendererMenu;
	private PlayerController 	controller;
	private MenuBuilder			menuBuilder;
	private Stage				stage;
	public boolean 				onMenu;
	public Integer				cont;
	
	private int W, H;
	
	public GameScreen(MegaGame megaGame) {
		this.megaGame = megaGame;
	}

	@Override
	public void show() {
		world = new World();
		rendererGame = new WorldRenderer(world, false, this.megaGame);
		rendererMenu = new MenuRenderer(megaGame);
		controller = new PlayerController(world);
		menuBuilder = new MenuBuilder();
		menuBuilder.setStyles();
		setInputProcessor();
		onMenu = false;
		cont = 0;

	}

	@Override
	public void render(float delta) {

		if(!onMenu){
			controller.update(delta);
			rendererGame.render(delta);
		}else{
			rendererGame.render(delta);
			rendererMenu.render(delta);
		}
	}
	
	@Override
	public void resize(int W, int H) {
		rendererGame.setSize(W, H);
		rendererMenu.setSize(W, H);
		this.W = W;
		this.H = H;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	public void setInputProcessor(){
		Gdx.input.setInputProcessor(this);
	}
	
	private void setOnMenu(){
		stage = new Stage();
		stage.addActor(menuBuilder.gameMenu(this));
		rendererMenu.setStage(stage);
		rendererMenu.setBackground();
		onMenu = true;
		rendererMenu.onGameMenu(true);
	}
	
	
	public void changeMenu(int menu){
		
		switch(menu){
		case 1: 
			setOnMenu();
			break;
		case 2:
			stage = new Stage();
			stage.addActor(menuBuilder.optionMenu(this));
			rendererMenu.setStage(stage);
			break;
		case 3:
			stage = new Stage();
			stage.addActor(menuBuilder.statusMenu(this));
			rendererMenu.setStage(stage);
			break;
		case 4:
			onMenu = false;
			setInputProcessor();
			break;
		}
		
		
	}
	
	public void changeMenuStatus(int menu){
		
		switch(menu){
			case 1:
				stage = new Stage();
				stage.addActor(menuBuilder.equipMenu(this));
				rendererMenu.setStage(stage);
				break;
			case 2:
				break;
			case 3:
				stage = new Stage();
				stage.addActor(menuBuilder.gameMenu(this));
				rendererMenu.setStage(stage);
				break;
			case 4:
				stage = new Stage();
				stage.addActor(menuBuilder.statusMenu(this));
				rendererMenu.setStage(stage);
				break;
		}
		
	}
	// * InputProcessor methods ***************************//
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftPressed();
		if (keycode == Keys.RIGHT)
			controller.rightPressed();
		if (keycode == Keys.Z)
			controller.jumpPressed();
		if (keycode == Keys.X)
			controller.firePressed();
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftReleased();
		if (keycode == Keys.RIGHT)
			controller.rightReleased();
		if (keycode == Keys.Z)
			controller.jumpReleased();
		if (keycode == Keys.X)
			controller.fireReleased();
		if (keycode == Keys.D)
			rendererGame.renderDebugText();
		if (keycode == Keys.ESCAPE){
			if(cont == 1){
				cont = 0;
			}else{
				cont = 1;
				setOnMenu();
			}
			
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < this.W / 2 && y > this.H / 2) {
			controller.leftPressed();
		}
		if (x > this.W / 2 && y > this.H / 2) {
			controller.rightPressed();
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < this.W / 2 && y > this.H / 2) {
			controller.leftReleased();
		}
		if (x > this.W / 2 && y > this.H / 2) {
			controller.rightReleased();
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
