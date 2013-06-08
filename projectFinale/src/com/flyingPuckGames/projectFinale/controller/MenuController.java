package com.flyingPuckGames.projectFinale.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.Options;
import com.flyingPuckGames.projectFinale.model.player.PlayerStatus;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.utils.Constants;
import com.flyingPuckGames.projectFinale.utils.MenuBuilder;

public class MenuController {

	private MegaGame megaGame;
	private MenuBuilder menuBuilder;
	private Stage stage;
	private Options option;
	private GameScreen game;
	private MenuScreen menu;
	private boolean fromGame;
	
	
	/*
	 * Init a new menuController with his own menuBuilder
	 */
	public MenuController(MegaGame megaGame){
		this.megaGame = megaGame;
		this.menuBuilder = new MenuBuilder();
		menuBuilder.init();
		option = megaGame.getOptions();
	}
	
	
	/**
	 * Method called when the window has resized
	 * These call to the method OnResize of the menuBuilder, to set the new size to all the menus
	 * @param width
	 * @param height
	 */
	public void onResize(float width, float height){
		menuBuilder.onResize(width, height);
		
	}
	
	
	/*
	 * Method that create and set the options for the Game.
	 */
	public void optionsGame(int i) {
		switch(i){
		case 1:
			stage = new Stage();
			stage.addActor(menuBuilder.videoOptions(this,false));
			game.setMenu(stage);
			break;
		case 2:
			stage = new Stage();
			stage.addActor(menuBuilder.SoundOptions(this,false));
			game.setMenu(stage);
			break;
		}
		
	}
	
	/*
	 * Method that create and set the options for the Main Menu.
	 */
	public void optionsMenu(int i){
		switch(i){
		case 1:
			stage = new Stage();
			
			stage.addActor(menuBuilder.videoOptions(this,true));
			
			menu.setMenu(stage);
			break;
		case 2:
			stage = new Stage();
			stage.addActor(menuBuilder.SoundOptions(this, true));
			menu.setMenu(stage);
		}
	}
	
	
	/*
	 * Method that create and set different windows from StatusMenu.
	 */
	public void status(int menu){
		switch(menu){
			case 1:
				stage = new Stage();
				stage.addActor(menuBuilder.equipMenu(this));
				game.setMenu(stage);
				break;
				
			case 2:
				stage = new Stage();
				stage.addActor(menuBuilder.optionMenu(this,false));
				game.setMenu(stage);
				break;
				
			case 3:
				stage = new Stage();
				stage.addActor(menuBuilder.GrimoireMenu(this, false));
				game.setMenu(stage);
				break;
				
			case 4:
				stage = new Stage();
				stage.addActor(menuBuilder.statusMenu(this));
				game.setMenu(stage);
				break;
			case 5:
				//megaGame.setScreen(this.menu);
				break;
		}
	}
	
	/*
	 * Method that create and set differents windows from MainMenu
	 */
	
	public void mainMenu(Integer screen){
		switch(screen){
			case 1:
				if(fromGame){
					megaGame.setScreen(this.game);
				}else{
					fromGame = true;
					megaGame.setScreen(new GameScreen(megaGame));
				}
				
				break;
			case 2:
				stage = new Stage();
				stage.addActor(menuBuilder.optionMenu(this,true));
				menu.setMenu(stage);
				break;
			case 3: 
				stage = new Stage();
				stage.addActor(menuBuilder.GrimoireMenu(this,true));
				menu.setMenu(stage);
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
		option.setVideoOptions(Constants.RESOLUTIONS[selectionIndex][0], Constants.RESOLUTIONS[selectionIndex][1],selectionIndex);
	}
	
	public void setSoundSettings(int value, boolean on){
		option.setAudioOptions(value, on);
	}
	
	public String[] getSoundSettings(){
		
		return option.getAudioOptions(); 
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
	
	public PlayerStatus getPlayerStatus(){
		return game.getPlayerStatus();
	}


	public int getVideoSettings() {
		return option.getVideoOptions();
	}
	
}
