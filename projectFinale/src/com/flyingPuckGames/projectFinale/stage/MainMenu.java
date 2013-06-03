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
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MainMenu {

	private ButtonStyle buttonStandard;
	private LabelStyle labelMenusStyle;
	
	private float WIDTH;
	private float HEIGHT;
	
	public MainMenu(ButtonStyle button,LabelStyle label){
		buttonStandard = button;
		labelMenusStyle = label;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}
	
	public Group create(final MenuController menuController) {

		Group p = new Group();
		
		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.5f);
		backMenu.setBounds((WIDTH * 0.395f) ,(HEIGHT * 0.34f), WIDTH*0.21f, HEIGHT*0.3f);
		
		p.addActor(backMenu);
		final Table mainTable = new Table();
		mainTable.defaults().width(WIDTH * 0.2f);
		mainTable.defaults().height(HEIGHT* 0.05f);
		mainTable.setX(WIDTH / 2);
		mainTable.setY(HEIGHT / 2);

		Button play = new Button(buttonStandard);
		play.add(new Label("PLAY", labelMenusStyle));
		play.center();
		play.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.mainMenu(1);
			}

		});

		mainTable.add(play);
		mainTable.row();

		Button options = new Button(buttonStandard);
		options.add(new Label("OPTIONS", labelMenusStyle));
		options.setColor(new Color(100, 100, 100, 100));
		options.center();
		options.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				menuController.mainMenu(2);
			}

		});

		mainTable.add(options);
		mainTable.row();

		Button grimoire = new Button(buttonStandard);
		grimoire.add(new Label("GRIMOIRE", labelMenusStyle));
		grimoire.center();
		grimoire.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.mainMenu(3);
			}

		});

		mainTable.add(grimoire);
		mainTable.row();

		Button exit = new Button(buttonStandard);
		exit.add(new Label("CLOSE GAME", labelMenusStyle));
		exit.center();
		exit.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.mainMenu(4);
			}

		});
		mainTable.add(exit);
		mainTable.row();

		p.addActor(mainTable);
		return p;

	}
}
