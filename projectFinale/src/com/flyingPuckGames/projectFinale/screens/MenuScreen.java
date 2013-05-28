package com.flyingPuckGames.projectFinale.screens;


import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;

public class MenuScreen implements Screen,InputProcessor {

	private MegaGame megaGame;
	private MenuBuilder menuBuilder;
	private MenuRenderer renderer;
	private Stage stage;

	
	public MenuScreen(MegaGame megaGame) {
		this.megaGame = megaGame;
	}
	
	@Override
	public void show() {
		
		menuBuilder = megaGame.getMenuBuilder();
		renderer = new MenuRenderer(megaGame);
		stage = new Stage();
		setStage(menuBuilder.mainMenu(this));
		renderer.onGameMenu(false);
		
	}
	
	
	@Override
	public void render(float delta) {
		
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		
		
	}
	
	public void setStage(Actor actor){
		stage = new Stage();
		stage.addActor(actor);
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
	
	public void changeScreen(Integer screen){
		switch(screen){
			case 1:
				megaGame.setScreen(new GameScreen(megaGame));
				break;
			case 2:
				
				break;
			case 3: 
				System.out.println("In Construction");
				break;
			case 4:
				megaGame.dispose();
				System.exit(0);
				break;
		}
		
	}
	
	public void changeMenu(Integer menu){
		switch(menu){
		case 1: 
			setStage(menuBuilder.mainMenu(this));
			break;
		case 2:
			setStage(menuBuilder.optionMenu(this));
		}
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
