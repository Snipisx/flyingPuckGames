package com.flyingPuckGames.projectFinale.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.utils.JSONParser;

public class GrimoireMenu{

	
	
	private float WIDTH;
	private float HEIGHT;
	
	private LabelStyle labelMenusStyle;
	private ButtonStyle standarButton;
	
	private Label monsterName;
	private Label monsterLevel;
	private Label monsterHp;
	private Label monsterNum;
	private Label monsterStrong;
	private Label monsterInmune;
	private Label monsterWeak;
	private Label monsterAbsorb;
	private Label monsterDrop;
	private Label monsterExp;
	private Label monsterDesc;
	
	private JSONParser parser;
	public GrimoireMenu(){
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		parser = new JSONParser();
	}
	
	
	private void setLabels(){
		
		
		
	}
	
	
	public Group create(MenuController menuController, boolean onMenu){
		
		Group stage = new Group();
		
		Image backgroundGrimoire = new Image((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/backGrimoire.png"))))));
		backgroundGrimoire.setBounds(WIDTH * 0.25f, HEIGHT * 0.25f, WIDTH * 0.5f,HEIGHT * 0.5f);
		backgroundGrimoire.setColor(0.4f, 0.4f, 0.4f, 0.8f);
		
		stage.addActor(backgroundGrimoire);
		
		
		
		
		return stage;
		
	}
}
