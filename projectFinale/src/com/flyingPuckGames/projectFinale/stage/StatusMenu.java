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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.model.player.PlayerStatus;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.SecondGameScreen;

public class StatusMenu {

	private ButtonStyle style;
	private LabelStyle lStyle;
	private LabelStyle statusSt;
	private LabelStyle nameStyle;
	private Color statusColor;
	
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
		style = button;
		lStyle = label;
		statusSt = status;
		nameStyle = name;
		statusColor = stColor;
	}	
	
	
	
	
	private void loadLabels(PlayerStatus playerStatus){
		
		levelInfo = new Label(playerStatus.getLevel().toString(),statusSt);
		expInfo = new Label(Float.toString(playerStatus.getExpActual()),statusSt);
		nextInfo = new Label(Float.toString(playerStatus.getExpNextLvl()),statusSt);
		statusInfo = new Label(playerStatus.getStatus(),statusSt);
		hpInfo = new Label((playerStatus.getHP().toString() + "/" + playerStatus.getMaxHp().toString()),statusSt);
		mpInfo = new Label((playerStatus.getMP().toString() + "/" + playerStatus.getMaxMp().toString()),statusSt);
		timeInfo = new Label((Float.toString(playerStatus.getTime())),statusSt);
		coinsInfo = new Label(Float.toString(playerStatus.getCoins()),statusSt);
		
		strInfo = new Label(playerStatus.getStr().toString(),statusSt);
		conInfo = new Label(playerStatus.getCon().toString(),statusSt);
		inteInfo = new Label(playerStatus.getInte().toString(),statusSt);
		lckInfo = new Label(playerStatus.getLck().toString(),statusSt);
		dmgInfo = new Label(playerStatus.getDmg().toString(),statusSt);
		defInfo = new Label(playerStatus.getDef().toString(),statusSt);
	}
	
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
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);

		p.addActor(background);

		
		
		Label nameChar = new Label("KYRIEL", nameStyle);
		nameChar.setAlignment(0, Align.center);
		nameChar.setX(  (background.getWidth() * 0.1f) );
		nameChar.setY(  (background.getHeight() * 0.85f) );
		
		p.addActor(nameChar);
		
		Table infoTableLeft = new Table();
		infoTableLeft.defaults().width(Gdx.graphics.getWidth() * 0.12f);
		infoTableLeft.defaults().height( Gdx.graphics.getHeight() * 0.02f );
		infoTableLeft.defaults().padLeft(Gdx.graphics.getWidth() * 0.03f);
		infoTableLeft.defaults().padBottom(Gdx.graphics.getHeight() * 0.02f);
		infoTableLeft.setX(Gdx.graphics.getWidth() * 0.15f);
		infoTableLeft.setY(Gdx.graphics.getHeight() * 0.45f);
		
		Label level = new Label("LEVEL",statusSt);
		level.setColor(statusColor);
		level.setAlignment(Align.right);
		infoTableLeft.add(level);
		levelInfo.setAlignment(Align.center);
		infoTableLeft.add(levelInfo);
		infoTableLeft.row();
		
		Label exp = new Label("EXP",statusSt);
		exp.setColor(statusColor);
		exp.setAlignment(Align.right);
		infoTableLeft.add(exp);
		expInfo.setAlignment(Align.center);
		infoTableLeft.add(expInfo);
		infoTableLeft.row();
		
		Label next = new Label("NEXT",statusSt);
		next.setColor(statusColor);
		next.setAlignment(Align.right);
		infoTableLeft.add(next);
		nextInfo.setAlignment(Align.center);
		infoTableLeft.add(nextInfo);
		infoTableLeft.row();
		
		Label vod = new Label("",lStyle);
		infoTableLeft.add(vod);
		infoTableLeft.row();
		
		Label status = new Label("STATUS",statusSt);
		status.setColor(statusColor);
		status.setAlignment(Align.right);
		infoTableLeft.add(status);
		statusInfo.setAlignment(Align.center);
		infoTableLeft.add(statusInfo);
		infoTableLeft.row();
		
		Label hp = new Label("HP",statusSt);
		hp.setColor(statusColor);
		hp.setAlignment(Align.right);
		infoTableLeft.add(hp);
		hpInfo.setAlignment(Align.center);
		infoTableLeft.add(hpInfo);
		infoTableLeft.row();
		
		Label mp = new Label("MP",statusSt);
		mp.setColor(statusColor);
		mp.setAlignment(Align.right);
		infoTableLeft.add(mp);
		mpInfo.setAlignment(Align.center);
		infoTableLeft.add(mpInfo);
		infoTableLeft.row();
		
		Label vod2 = new Label("",lStyle);
		infoTableLeft.add(vod2);
		infoTableLeft.row();
		
		Label time = new Label("TIME",statusSt);
		time.setColor(statusColor);
		time.setAlignment(Align.right);
		infoTableLeft.add(time);
		timeInfo.setAlignment(Align.center);
		infoTableLeft.add(timeInfo);
		infoTableLeft.row();
		
		Label coins = new Label("COINS",statusSt);
		coins.setColor(statusColor);
		coins.setAlignment(Align.right);
		infoTableLeft.add(coins);
		coinsInfo.setAlignment(Align.center);
		infoTableLeft.add(coinsInfo);
		infoTableLeft.row();
		
		p.addActor(infoTableLeft);
		
		
		Table infoTableRight = new Table();
		infoTableRight.defaults().width(Gdx.graphics.getWidth() * 0.12f);
		infoTableRight.defaults().height( Gdx.graphics.getHeight() * 0.02f );
		infoTableRight.defaults().padLeft(Gdx.graphics.getWidth() * 0.03f);
		infoTableRight.defaults().padBottom(Gdx.graphics.getHeight() * 0.02f);
		infoTableRight.setX(Gdx.graphics.getWidth() * 0.8f);
		infoTableRight.setY(Gdx.graphics.getHeight() * 0.45f);
		
		
		Label str = new Label("STR",statusSt);
		str.setColor(statusColor);
		str.setAlignment(Align.right);
		infoTableRight.add(str);
		strInfo.setAlignment(Align.center);
		infoTableRight.add(strInfo);
		infoTableRight.row();
		
		Label inte = new Label("INT",statusSt);
		inte.setColor(statusColor);
		inte.setAlignment(Align.right);
		infoTableRight.add(inte);
		inteInfo.setAlignment(Align.center);
		infoTableRight.add(inteInfo);
		infoTableRight.row();
		
		Label con = new Label("CON",statusSt);
		con.setColor(statusColor);
		con.setAlignment(Align.right);
		infoTableRight.add(con);
		conInfo.setAlignment(Align.center);
		infoTableRight.add(conInfo);
		infoTableRight.row();
		
		Label lck = new Label("LCK",statusSt);
		lck.setColor(statusColor);
		lck.setAlignment(Align.right);
		infoTableRight.add(lck);
		lckInfo.setAlignment(Align.center);
		infoTableRight.add(lckInfo);
		infoTableRight.row();
		
		Label vod3 = new Label("",lStyle);
		infoTableRight.add(vod3);
		infoTableRight.row();
		
		Label dmg = new Label("DMG",statusSt);
		dmg.setColor(statusColor);
		dmg.setAlignment(Align.right);
		infoTableRight.add(dmg);
		dmgInfo.setAlignment(Align.center);
		infoTableRight.add(dmgInfo);
		infoTableRight.row();
		
		Label def = new Label("DEF",statusSt);
		def.setColor(statusColor);
		def.setAlignment(Align.right);
		infoTableRight.add(def);
		defInfo.setAlignment(Align.center);
		infoTableRight.add(defInfo);
		infoTableRight.row();
		
		
		p.addActor(infoTableRight);
		
		
		Table optionTable = new Table();
		optionTable.defaults().width( Gdx.graphics.getWidth() * 0.2f );
		optionTable.defaults().height( Gdx.graphics.getHeight() * 0.05f );
		optionTable.setX( Gdx.graphics.getWidth() / 2 );
		optionTable.setY( Gdx.graphics.getHeight() / 2 );
		
		
		Button equip = new Button(style);
		equip.add(new Label("EQUIP",lStyle));
		equip.center();
		equip.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(1);
				
			}
		});
		
		Button grimoire = new Button(style);
		grimoire.add(new Label("GRIMOIRE", lStyle));
		grimoire.center();
		grimoire.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(3);
			}

		});
		
		Button options = new Button(style);
		options.add(new Label("OPTIONS", lStyle));
		options.center();
		options.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				menuController.status(2);
			}

		});

		Button back = new Button(style);
		back.add(new Label("BACK",lStyle));
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
		optionTable.add(equip);
		optionTable.row();
		optionTable.add(options);
		optionTable.row();
		optionTable.add(grimoire);
		optionTable.row();
		optionTable.add(back);
		
		p.addActor(optionTable);
		
		return p;
		
	}
}
