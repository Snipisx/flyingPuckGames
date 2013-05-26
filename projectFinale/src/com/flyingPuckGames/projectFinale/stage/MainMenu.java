package com.flyingPuckGames.projectFinale.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MainMenu {

	private ButtonStyle style;
	private LabelStyle lStyle;
	public MainMenu(ButtonStyle button,LabelStyle label){
		style = button;
		lStyle = label;
	}
	
	public Group create(final MenuScreen menu) {

		Group p = new Group();
		
		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.5f);
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.395f) ,(Gdx.graphics.getHeight() * 0.34f), Gdx.graphics.getWidth()*0.21f, Gdx.graphics.getHeight()*0.3f);
		
		p.addActor(backMenu);
		final Table mainTable = new Table();
		mainTable.defaults().width(Gdx.graphics.getWidth() * 0.2f);
		mainTable.defaults().height(Gdx.graphics.getHeight()* 0.05f);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button play = new Button(style);
		play.add(new Label("PLAY", lStyle));
		play.center();
		play.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(1);
			}

		});

		mainTable.add(play);
		mainTable.row();

		Button options = new Button(style);
		options.add(new Label("OPTIONS", lStyle));
		options.setColor(new Color(100, 100, 100, 100));
		options.center();
		options.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				menu.changeMenu(2);
			}

		});

		mainTable.add(options);
		mainTable.row();

		Button grimoire = new Button(style);
		grimoire.add(new Label("GRIMOIRE", lStyle));
		grimoire.center();
		grimoire.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(3);
			}

		});

		mainTable.add(grimoire);
		mainTable.row();

		Button exit = new Button(style);
		exit.add(new Label("CLOSE GAME", lStyle));
		exit.center();
		exit.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(4);
			}

		});
		mainTable.add(exit);
		mainTable.row();

		p.addActor(mainTable);
		return p;

	}
}
