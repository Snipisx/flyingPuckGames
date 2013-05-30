package com.flyingPuckGames.projectFinale.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;
import com.flyingPuckGames.projectFinale.view.MenuRenderer;

public class MenuController {

	private MegaGame megaGame;
	private MenuBuilder menuBuilder;
	private Stage stage;
	private Options option;
	private GameScreen game;
	private MenuScreen menu;
	
	
	public MenuController(MegaGame megaGame){
		this.megaGame = megaGame;
		this.menuBuilder = new MenuBuilder();
		menuBuilder.init();
		option = megaGame.getOptions();
	}
	
	public void optionsGame(int i) {
		switch(i){
		case 1:
			stage = new Stage();
			stage.addActor(menuBuilder.videoOptionsGame(this));
			game.setMenu(stage);
			break;
		}
		
	}
	
	public void optionsMenu(int i){
		switch(i){
		case 1:
			stage = new Stage();
			stage.addActor(menuBuilder.videoOptionsMenu(this));
			menu.setMenu(stage);
			break;
		}
	}
	
	public void status(int menu){
		switch(menu){
			case 1:
				stage = new Stage();
				stage.addActor(menuBuilder.equipMenu(this));
				game.setMenu(stage);
				break;
				
			case 2:
				stage = new Stage();
				stage.addActor(menuBuilder.optionMenu(this));
				game.setMenu(stage);
				break;
				
			case 3:
				break;
				
			case 4:
				stage = new Stage();
				stage.addActor(menuBuilder.statusMenu(this));
				game.setMenu(stage);
				break;
		}
	}
	
	
	public void mainMenu(Integer screen){
		switch(screen){
			case 1:
				megaGame.setScreen(new GameScreen(megaGame));
				break;
			case 2:
				stage = new Stage();
				stage.addActor(menuBuilder.optionMainMenu(this));
				menu.setMenu(stage);
				break;
			case 3: 
				System.out.println("In Construction");
				break;
			case 4:
				Gdx.app.exit();
				//System.exit(0);
				break;
			case 5:
				stage = new Stage();
				stage.addActor(menuBuilder.mainMenu(this));
				menu.setMenu(stage);
				break;
		}
		
	}
	
	public void setOnMenu(boolean menu){
		game.setOnMenu(menu);
	}
	
	public void setContEsc(int cont){
		game.setContEsc(cont);
	}
	
	public void setVideoSettings(int selectionIndex) {
		option.setVideoOptions(Constants.RESOLUTIONS[selectionIndex][0], Constants.RESOLUTIONS[selectionIndex][1]);
	}

	public GameScreen getGame() {
		return game;
	}

	public void setGame(GameScreen game) {
		this.game = game;
	}

	public MenuScreen getMenu() {
		return menu;
	}

	public void setMenu(MenuScreen menu) {
		this.menu = menu;
	}

	public void setInputProcessor() {
		game.setInputProcessor();
		
	}
	
	
	
}
