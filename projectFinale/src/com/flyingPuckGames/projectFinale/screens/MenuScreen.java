package com.flyingPuckGames.projectFinale.screens;


import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;

public class MenuScreen implements Screen,InputProcessor {

	private MegaGame megaGame;
	private MenuRenderer renderer;
	private MenuController menuController;
	
	public MenuScreen(MegaGame megaGame) {
		this.megaGame = megaGame;
		menuController = megaGame.getMenuController();
		menuController.setMenu(this);
		renderer = new MenuRenderer(megaGame);
		menuController.mainMenu(5);
	}
	
	@Override
	public void show() {
		
	}
	
	
	@Override
	public void render(float delta) {
		
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		
		
	}
	
	public void setMenu(Stage stage){

		renderer.setStage(stage);
	}
	
	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
	
	
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			
		if (keycode == Keys.RIGHT)
			
		if (keycode == Keys.Z)
			
		if (keycode == Keys.X)

		return true;
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			
		if (keycode == Keys.RIGHT)
			
		if (keycode == Keys.DOWN)
			
		if (keycode == Keys.UP)
			
		if (keycode == Keys.D)
			
		if (keycode == Keys.ESCAPE){
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
		
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		
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
