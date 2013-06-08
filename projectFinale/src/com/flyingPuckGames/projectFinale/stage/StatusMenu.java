package com.flyingPuckGames.projectFinale.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.model.player.PlayerStatus;

public class StatusMenu {

	private ButtonStyle buttonStandard;
	private LabelStyle labelMenusStyle;
	private LabelStyle labelStatusStyle;
	private LabelStyle nameStyle;
	private Color statusColor;
	
	private float WIDTH;
	private float HEIGHT;
	
	//labels info Status
	private Label levelInfo;
	private Label expInfo;
	private Label nextInfo;
	private Label statusInfo;
	private Label hpInfo;
	private Label mpInfo;
	private Label timeInfo;
	private Label coinsInfo;
	
	//labels info stats
	private Label strInfo;
	private Label conInfo;
	private Label inteInfo;
	private Label lckInfo;
	private Label dmgInfo;
	private Label defInfo;
	
	public StatusMenu(ButtonStyle button,LabelStyle label,LabelStyle status,LabelStyle name,Color stColor){
		buttonStandard = button;
		labelMenusStyle = label;
		labelStatusStyle = status;
		nameStyle = name;
		statusColor = stColor;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}	
	
	
	/*
	 * Method that load all the labels of the menu.
	 */
	private void loadLabels(PlayerStatus playerStatus){
		
		levelInfo = new Label(playerStatus.getLevel().toString(),labelStatusStyle);
		expInfo = new Label(Float.toString(playerStatus.getExpActual()),labelStatusStyle);
		nextInfo = new Label(Float.toString(playerStatus.getExpNextLvl()),labelStatusStyle);
		statusInfo = new Label(playerStatus.getStatus(),labelStatusStyle);
		hpInfo = new Label((playerStatus.getHP().toString() + "/" + playerStatus.getMaxHp().toString()),labelStatusStyle);
		mpInfo = new Label((playerStatus.getMP().toString() + "/" + playerStatus.getMaxMp().toString()),labelStatusStyle);
		timeInfo = new Label((Float.toString(playerStatus.getTime())),labelStatusStyle);
		coinsInfo = new Label(Float.toString(playerStatus.getCoins()),labelStatusStyle);
		
		strInfo = new Label(playerStatus.getStr().toString(),labelStatusStyle);
		conInfo = new Label(playerStatus.getCon().toString(),labelStatusStyle);
		inteInfo = new Label(playerStatus.getInte().toString(),labelStatusStyle);
		lckInfo = new Label(playerStatus.getLck().toString(),labelStatusStyle);
		dmgInfo = new Label(playerStatus.getDmg().toString(),labelStatusStyle);
		defInfo = new Label(playerStatus.getDef().toString(),labelStatusStyle);
	}
	
	
	/*
	 * Method that create the window
	 */
	public Group create(final MenuController menuController){
		loadLabels(menuController.getPlayerStatus());
		
		menuController.setOnMenu(true);
		Group p = new Group();
		
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					menuController.status(4);
					menuController.setContEsc(1);
					menuController.setOnMenu(false);
					menuController.setInputProcessor();
				}
				
				return true;
			}
		});
		
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);

		p.addActor(background);

		
		Label nameChar = new Label("KYRIEL", nameStyle);
		nameChar.setAlignment(0, Align.center);
		nameChar.setX(  (background.getWidth() * 0.1f) );
		nameChar.setY(  (background.getHeight() * 0.85f) );
		
		p.addActor(nameChar);
		
		Table infoTableLeft = new Table();
		infoTableLeft.defaults().width(WIDTH * 0.12f);
		infoTableLeft.defaults().height( HEIGHT * 0.02f );
		infoTableLeft.defaults().padLeft(WIDTH * 0.03f);
		infoTableLeft.defaults().padBottom(HEIGHT * 0.02f);
		infoTableLeft.setX(WIDTH * 0.15f);
		infoTableLeft.setY(HEIGHT * 0.45f);
		
		Label level = new Label("LEVEL",labelStatusStyle);
		level.setColor(statusColor);
		level.setAlignment(Align.right);
		infoTableLeft.add(level);
		levelInfo.setAlignment(Align.center);
		infoTableLeft.add(levelInfo);
		infoTableLeft.row();
		
		Label exp = new Label("EXP",labelStatusStyle);
		exp.setColor(statusColor);
		exp.setAlignment(Align.right);
		infoTableLeft.add(exp);
		expInfo.setAlignment(Align.center);
		infoTableLeft.add(expInfo);
		infoTableLeft.row();
		
		Label next = new Label("NEXT",labelStatusStyle);
		next.setColor(statusColor);
		next.setAlignment(Align.right);
		infoTableLeft.add(next);
		nextInfo.setAlignment(Align.center);
		infoTableLeft.add(nextInfo);
		infoTableLeft.row();
		
		Label vod = new Label("",labelMenusStyle);
		infoTableLeft.add(vod);
		infoTableLeft.row();
		
		Label status = new Label("STATUS",labelStatusStyle);
		status.setColor(statusColor);
		status.setAlignment(Align.right);
		infoTableLeft.add(status);
		statusInfo.setAlignment(Align.center);
		infoTableLeft.add(statusInfo);
		infoTableLeft.row();
		
		Label hp = new Label("HP",labelStatusStyle);
		hp.setColor(statusColor);
		hp.setAlignment(Align.right);
		infoTableLeft.add(hp);
		hpInfo.setAlignment(Align.center);
		infoTableLeft.add(hpInfo);
		infoTableLeft.row();
		
		Label mp = new Label("MP",labelStatusStyle);
		mp.setColor(statusColor);
		mp.setAlignment(Align.right);
		infoTableLeft.add(mp);
		mpInfo.setAlignment(Align.center);
		infoTableLeft.add(mpInfo);
		infoTableLeft.row();
		
		Label vod2 = new Label("",labelMenusStyle);
		infoTableLeft.add(vod2);
		infoTableLeft.row();
		
		Label time = new Label("TIME",labelStatusStyle);
		time.setColor(statusColor);
		time.setAlignment(Align.right);
		infoTableLeft.add(time);
		timeInfo.setAlignment(Align.center);
		infoTableLeft.add(timeInfo);
		infoTableLeft.row();
		
		Label coins = new Label("COINS",labelStatusStyle);
		coins.setColor(statusColor);
		coins.setAlignment(Align.right);
		infoTableLeft.add(coins);
		coinsInfo.setAlignment(Align.center);
		infoTableLeft.add(coinsInfo);
		infoTableLeft.row();
		
		p.addActor(infoTableLeft);
		
		
		Table infoTableRight = new Table();
		infoTableRight.defaults().width(WIDTH * 0.12f);
		infoTableRight.defaults().height( HEIGHT * 0.02f );
		infoTableRight.defaults().padLeft(WIDTH * 0.03f);
		infoTableRight.defaults().padBottom(HEIGHT * 0.02f);
		infoTableRight.setX(WIDTH * 0.8f);
		infoTableRight.setY(HEIGHT * 0.45f);
		
		
		Label str = new Label("STR",labelStatusStyle);
		str.setColor(statusColor);
		str.setAlignment(Align.right);
		infoTableRight.add(str);
		strInfo.setAlignment(Align.center);
		infoTableRight.add(strInfo);
		infoTableRight.row();
		
		Label inte = new Label("INT",labelStatusStyle);
		inte.setColor(statusColor);
		inte.setAlignment(Align.right);
		infoTableRight.add(inte);
		inteInfo.setAlignment(Align.center);
		infoTableRight.add(inteInfo);
		infoTableRight.row();
		
		Label con = new Label("CON",labelStatusStyle);
		con.setColor(statusColor);
		con.setAlignment(Align.right);
		infoTableRight.add(con);
		conInfo.setAlignment(Align.center);
		infoTableRight.add(conInfo);
		infoTableRight.row();
		
		Label lck = new Label("LCK",labelStatusStyle);
		lck.setColor(statusColor);
		lck.setAlignment(Align.right);
		infoTableRight.add(lck);
		lckInfo.setAlignment(Align.center);
		infoTableRight.add(lckInfo);
		infoTableRight.row();
		
		Label vod3 = new Label("",labelMenusStyle);
		infoTableRight.add(vod3);
		infoTableRight.row();
		
		Label dmg = new Label("DMG",labelStatusStyle);
		dmg.setColor(statusColor);
		dmg.setAlignment(Align.right);
		infoTableRight.add(dmg);
		dmgInfo.setAlignment(Align.center);
		infoTableRight.add(dmgInfo);
		infoTableRight.row();
		
		Label def = new Label("DEF",labelStatusStyle);
		def.setColor(statusColor);
		def.setAlignment(Align.right);
		infoTableRight.add(def);
		defInfo.setAlignment(Align.center);
		infoTableRight.add(defInfo);
		infoTableRight.row();
		
		
		p.addActor(infoTableRight);
		
		
		Table optionTable = new Table();
		optionTable.defaults().width( WIDTH * 0.2f );
		optionTable.defaults().height( HEIGHT * 0.05f );
		optionTable.setX( WIDTH / 2 );
		optionTable.setY( HEIGHT / 2 );
		
		
		Button equip = new Button(buttonStandard);
		equip.add(new Label("EQUIP",labelMenusStyle));
		equip.center();
		equip.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(1);
				
			}
		});
		
		Button grimoire = new Button(buttonStandard);
		grimoire.add(new Label("GRIMOIRE", labelMenusStyle));
		grimoire.center();
		grimoire.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(3);
			}

		});
		
		Button options = new Button(buttonStandard);
		options.add(new Label("OPTIONS", labelMenusStyle));
		options.center();
		options.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				menuController.status(2);
			}

		});

		Button back = new Button(buttonStandard);
		back.add(new Label("BACK",labelMenusStyle));
		back.center();
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(4);
				menuController.setContEsc(0);
				menuController.setOnMenu(false);
				menuController.setInputProcessor();
			}
		});
		
		Button exitMenu = new Button(buttonStandard);
		exitMenu.add(new Label("MAIN MENU",labelMenusStyle));
		exitMenu.center();
		exitMenu.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(5);
			}
		});
		optionTable.add(equip);
		optionTable.row();
		optionTable.add(options);
		optionTable.row();
		optionTable.add(grimoire);
		optionTable.row();
		optionTable.add(back);
		optionTable.row();
		optionTable.add(exitMenu);
		
		p.addActor(optionTable);
		
		return p;
		
	}

	public void setResolution(float width, float height) {
		WIDTH = width;
		HEIGHT = height;
		
	}
}
