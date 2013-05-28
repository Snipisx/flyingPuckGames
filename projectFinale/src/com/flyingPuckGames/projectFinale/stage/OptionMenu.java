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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.screens.SecondGameScreen;
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
	public Group createGame(final SecondGameScreen game) {

		Group p = new Group();
		
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					game.changeMenuStatus(4);
				}
				
				return true;
			}
		});

		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.8f);
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.40f) ,(Gdx.graphics.getHeight() * 0.34f), Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getHeight()*0.3f);

		p.addActor(backMenu);
		
		
		Table mainTable = new Table();
		mainTable.defaults().width(Gdx.graphics.getWidth() * 0.2f);
		mainTable.defaults().height(Gdx.graphics.getHeight()* 0.05f);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("VIDEO OPTIONS", lStyle));
		video.center();
		video.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// game.changeMenu2();
			}

		});

		mainTable.add(video);
		mainTable.row();

		Button sound = new Button(style);
		sound.add(new Label("SOUND OPTIONS", lStyle));
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

				game.changeMenu(1);
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
	
	public Group createMenu(final MenuScreen game) {

		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					game.changeMenu(1);
				}
				
				return true;
			}
		});

		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.8f);
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.40f) ,(Gdx.graphics.getHeight() * 0.34f), Gdx.graphics.getWidth()*0.2f, Gdx.graphics.getHeight()*0.3f);


		p.addActor(backMenu);

		Table mainTable = new Table();
		mainTable.defaults().width(Gdx.graphics.getWidth() * 0.22f);
		mainTable.defaults().height(Gdx.graphics.getHeight()* 0.05f);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("VIDEO OPTIONS", lStyle));
		video.center();
		video.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// game.changeMenu2();
			}

		});

		mainTable.add(video);
		mainTable.row();

		Button sound = new Button(style);
		sound.add(new Label("SOUND OPTIONS", lStyle));
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

				game.changeMenu(1);
			}

		});
		mainTable.add(back);
		mainTable.row();

		p.addActor(mainTable);

		return p;

	}
}
