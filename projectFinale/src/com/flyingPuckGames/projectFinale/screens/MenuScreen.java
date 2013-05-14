package com.flyingPuckGames.projectFinale.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;

public class MenuScreen implements Screen {

	private MegaGame megaGame;
	private MenuBuilder menuBuilder;
	private MenuRenderer renderer;
	private Stage stage;
	private Texture background;
	public InputMultiplexer inputSystem;
	
	public MenuScreen(MegaGame megaGame) {
		this.megaGame = megaGame;
	}
	
	@Override
	public void show() {
		
		menuBuilder = new MenuBuilder();
		renderer = new MenuRenderer(megaGame);
		stage = new Stage();
		setStage();
	}
	
	
	@Override
	public void render(float delta) {
		
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		
		
	}
	
	public void setStage(){
		
		stage.addActor(menuBuilder.mainMenu(this));
		renderer.setStage(stage);
	}
	

	
	public void setInputSystems(InputProcessor... processors) {
		inputSystem = new InputMultiplexer(processors);
		Gdx.input.setInputProcessor(inputSystem);
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
				System.out.println("In construction");
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

}
