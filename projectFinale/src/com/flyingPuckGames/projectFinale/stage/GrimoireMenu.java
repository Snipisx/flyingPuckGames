package com.flyingPuckGames.projectFinale.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Json;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.model.enemy.Enemy;
import com.flyingPuckGames.projectFinale.utils.JSONParser;
import com.google.gson.Gson;
import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;

public class GrimoireMenu{

	
	
	private float WIDTH;
	private float HEIGHT;
	
	private LabelStyle labelMenusStyle;
	private ButtonStyle standardButon;
	
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
	
	private int monsterSelected;
	private JSONParser parser;
	
	
	public GrimoireMenu(LabelStyle menuStyle,ButtonStyle buton){
		labelMenusStyle = menuStyle;
		standardButon = buton;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		parser = new JSONParser();
		init();
	}
	
	
	/*
	 * This method initialize all the labels with the info of the first enemy
	 */
	private void init(){
		monsterName = new Label("",labelMenusStyle);
		monsterLevel = new Label("",labelMenusStyle);
		monsterHp = new Label("",labelMenusStyle);
		monsterNum = new Label("",labelMenusStyle);
		monsterStrong = new Label("",labelMenusStyle);
		monsterInmune = new Label("",labelMenusStyle);
		monsterWeak = new Label("",labelMenusStyle);
		monsterAbsorb = new Label("",labelMenusStyle);
		monsterExp = new Label("",labelMenusStyle);
		monsterDrop = new Label("",labelMenusStyle);
		monsterDesc = new Label("",labelMenusStyle);
		setNextEnemy(0);
		monsterSelected = 0;
	}
	
	/*
	 * Method that set the info of the nextEnemy
	 */
	
	private void setNextEnemy(int index){
		Enemy enemy = parser.getEnemy(index);
		
		monsterName.setText(enemy.getName().toUpperCase());
		monsterLevel.setText("LVL" + Integer.toString(enemy.getLevel()));
		monsterHp.setText("HP" + Integer.toString(enemy.getHp()));
		monsterNum.setText("NO. " + Integer.toString(enemy.getId()));
		monsterStrong.setText((enemy.getStrongAgainst().toUpperCase()));
		monsterInmune.setText(enemy.getInmuneAgainst().toUpperCase());
		monsterWeak.setText(enemy.getWeakAgainst().toUpperCase());
		monsterAbsorb.setText(enemy.getAbsorb().toUpperCase());
		monsterExp.setText("EXP " + Float.toString(enemy.getExp()));
		monsterDrop.setText(enemy.getDrop().toUpperCase());
		monsterDesc.setText(enemy.getDesc().toUpperCase());
	}
	
	/*
	 * Method that create the window
	 */
	public Group create(final MenuController menuController, final boolean onMenu){
		
		Group stage = new Group();
		
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					if(onMenu){
						menuController.mainMenu(5);
					}else{
						menuController.status(4);
					}
				}
				
				return true;
			}
		});
		
		Image backgroundGrimoire = new Image((new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/backGrimoire.png"))))));
		backgroundGrimoire.setBounds(WIDTH * 0.25f, HEIGHT * 0.25f, WIDTH * 0.5f,HEIGHT * 0.5f);
		backgroundGrimoire.setColor(0.4f, 0.4f, 0.4f, 0.8f);
		
		stage.addActor(backgroundGrimoire);
		
		Image monsterImage = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/monster.png")))));
		monsterImage.setBounds(WIDTH * 0.27f, HEIGHT * 0.37f, WIDTH * 0.2f, HEIGHT * 0.25f);
		stage.addActor(monsterImage);
		
		
		monsterName.setBounds( WIDTH * 0.29f, HEIGHT * 0.65f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterLevel.setBounds(WIDTH* 0.3f, HEIGHT * 0.6f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterHp.setBounds(WIDTH* 0.4f, HEIGHT * 0.6f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterNum.setBounds(WIDTH *0.65f, HEIGHT * 0.65f, WIDTH * 0.1f, HEIGHT * 0.1f);
		stage.addActor(monsterName);
		stage.addActor(monsterLevel);
		stage.addActor(monsterHp);
		stage.addActor(monsterNum);
		
		Label strong = new Label("STRONG VS.",labelMenusStyle);
		strong.setBounds(WIDTH * 0.48f, HEIGHT * 0.6f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterStrong.setBounds(WIDTH * 0.48f, HEIGHT * 0.55f, WIDTH * 0.1f, HEIGHT * 0.1f);
		stage.addActor(monsterStrong);
		stage.addActor(strong);
		
		Label immune = new Label("IMMUNE",labelMenusStyle);
		immune.setBounds(WIDTH * 0.48f, HEIGHT * 0.50f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterInmune.setBounds(WIDTH * 0.48f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.1f);
		stage.addActor(monsterInmune);
		stage.addActor(immune);
		
		Label weak = new Label("WEAK VS.",labelMenusStyle);
		weak.setBounds(WIDTH * 0.48f, HEIGHT * 0.4f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterWeak.setBounds(WIDTH * 0.48f, HEIGHT * 0.35f, WIDTH * 0.1f, HEIGHT * 0.1f);
		stage.addActor(monsterWeak);
		stage.addActor(weak);
		
		Label drop = new Label("DROP ITEMS",labelMenusStyle);
		drop.setBounds(WIDTH * 0.61f, HEIGHT * 0.6f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterDrop.setBounds(WIDTH * 0.63f, HEIGHT * 0.55f, WIDTH * 0.1f, HEIGHT * 0.1f);
		stage.addActor(monsterDrop);
		stage.addActor(drop);
		
		monsterExp.setBounds(WIDTH * 0.61f, HEIGHT * 0.5f, WIDTH * 0.1f, HEIGHT * 0.1f);
		stage.addActor(monsterExp);
		
		Label absorb = new Label("ABSORB",labelMenusStyle);
		absorb.setBounds(WIDTH * 0.61f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.1f);
		monsterAbsorb.setBounds(WIDTH * 0.61f, HEIGHT * 0.4f, WIDTH * 0.1f, HEIGHT * 0.1f);
		
		stage.addActor(monsterAbsorb);
		stage.addActor(absorb);
		
		
		monsterDesc.setBounds(WIDTH * 0.3f, HEIGHT * 0.3f, WIDTH * 0.1f, HEIGHT * 0.1f);
		
		stage.addActor(monsterDesc);
		
		
		Button back = new Button(new Label("BACK",labelMenusStyle),standardButon);
		
		back.setBounds(WIDTH * 0.8f, HEIGHT * 0.8f, WIDTH * 0.1f, HEIGHT * 0.1f);
		
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
					if(onMenu){
						menuController.mainMenu(5);
					}else{
						menuController.status(4);
					}
			}
		});
		
		stage.addActor(back);
		
		Button next = new Button(new Label("NEXT",labelMenusStyle),standardButon);
		
		next.setBounds(WIDTH * 0.7f, HEIGHT * 0.15f, WIDTH * 0.1f, HEIGHT * 0.1f);
		
		next.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				monsterSelected++;
				if(monsterSelected < 10){
					setNextEnemy(monsterSelected);
				}else{
					monsterSelected = 10;
				}
				
			}
		});
		stage.addActor(next);
		
		
		Button prev = new Button(new Label("PREV",labelMenusStyle),standardButon);
		
		prev.setBounds(WIDTH * 0.2f, HEIGHT * 0.15f, WIDTH * 0.1f, HEIGHT * 0.1f);
		
		prev.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				monsterSelected--;
				if(monsterSelected > -1){
					setNextEnemy(monsterSelected);
				}else{
					monsterSelected = -1;
				}
				
			}
		});
		stage.addActor(prev);
		
		
		return stage;
		
	}


	public void setResolution(float width, float height) {
		WIDTH = width;
		HEIGHT = height;
	}
}
