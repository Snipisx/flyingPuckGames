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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class OptionMenu {

	private ButtonStyle style;
	private LabelStyle lStyle;
	
	public OptionMenu(ButtonStyle button,LabelStyle label){
		style = button;
		lStyle = label;
	}
	
	
	/*
	 * OptionMenu from gameScreen
	 */
	public Group createGame(final MenuController menuController) {

		Group p = new Group();
		
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					menuController.status(4);
				}
				
				return true;
			}
		});

		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);

		p.addActor(background);
		
		Table mainTable = new Table();
		mainTable.defaults().width(Gdx.graphics.getWidth() * 0.2f);
		mainTable.defaults().height(Gdx.graphics.getHeight()* 0.05f);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("VIDEO", lStyle));
		video.center();
		video.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.optionsGame(1);
			}

		});

		mainTable.add(video);
		mainTable.row();

		Button sound = new Button(style);
		sound.add(new Label("SOUND", lStyle));
		sound.center();
		sound.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				// game.changeMenu2();
			}

		});

		mainTable.add(sound);
		mainTable.row();

		Button back = new Button(style);
		back.add(new Label("BACK", lStyle));
		back.center();
		back.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				menuController.status(4);
			}

		});
		mainTable.add(back);
		mainTable.row();

		p.addActor(mainTable);

		return p;

	}

	/*
	 * OptionMenu from menuScreen
	 */
	
	public Group createMenu(final MenuController menuController) {

		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					menuController.mainMenu(5);
				}
				
				return true;
			}
		});
		

		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);

		p.addActor(background);
		
		Table mainTable = new Table();
		mainTable.defaults().width(Gdx.graphics.getWidth() * 0.22f);
		mainTable.defaults().height(Gdx.graphics.getHeight()* 0.05f);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("VIDEO", lStyle));
		video.center();
		video.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.optionsMenu(1);
			}

		});

		mainTable.add(video);
		mainTable.row();

		Button sound = new Button(style);
		sound.add(new Label("SOUND", lStyle));
		sound.center();
		sound.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				// game.changeMenu2();
			}

		});

		mainTable.add(sound);
		mainTable.row();

		Button back = new Button(style);
		back.add(new Label("BACK", lStyle));
		back.center();
		back.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				menuController.mainMenu(5);
			}

		});
		mainTable.add(back);
		mainTable.row();

		p.addActor(mainTable);

		return p;

	}
}
