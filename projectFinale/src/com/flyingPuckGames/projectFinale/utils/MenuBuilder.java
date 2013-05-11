package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MenuBuilder {

	public Table mainMenu(final MenuScreen menu){
		
		BitmapFont font = new BitmapFont();
		font.setUseIntegerPositions(true);
		
		ButtonStyle style = new ButtonStyle();
		style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;
		
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = font;
		
		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth()/2);
		mainTable.setY(Gdx.graphics.getHeight()/2);
		
		Button play = new Button(style);
		play.add(new Label("Play",lStyle));
		play.center();
		play.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(1);
			}
			
		});
		
		mainTable.add(play);
		mainTable.row();
		
		Button options = new Button(style);
		options.add(new Label("Options(In construction)",lStyle));
		options.center();
		options.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(2);
			}
			
		});
		
		mainTable.add(options);
		mainTable.row();
		
		Button grimoire = new Button(style);
		grimoire.add(new Label("Grimoire(In construction)",lStyle));
		grimoire.center();
		grimoire.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(3);
			}
			
		});
		
		mainTable.add(grimoire);
		mainTable.row();
		
		Button exit = new Button(style);
		exit.add(new Label("Close game",lStyle));
		exit.center();
		exit.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(4);
			}
			
		});
		mainTable.add(exit);
		mainTable.row();
		
		return mainTable;
		
	}

	public Actor gameMenu(MenuScreen menuScreen) {
		return null;
	}
	
}