package com.flyingPuckGames.projectFinale.utils;

import java.awt.Event;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MenuBuilder {

	
	
	private BitmapFont font = new BitmapFont();

	public Group mainMenu(final MenuScreen menu) {

		Group p = new Group();
		font.setUseIntegerPositions(true);
		ButtonStyle style = new ButtonStyle();
		//style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonover.png"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = font;

		Image img = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		img.setColor(0.2f, 0.2f, 0.2f, 0.5f);
		img.setBounds(((float)Gdx.graphics.getWidth() * 0.22f) ,((float)Gdx.graphics.getHeight() * 0.10f), img.getWidth(), img.getHeight());
//		img.setX(250);
//		img.setY(50);
		
		p.addActor(img);
		final Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button play = new Button(style);
		play.add(new Label("Play", lStyle));
		play.center();
		play.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(1);
			}

		});

		mainTable.add(play);
		mainTable.row();

		Button options = new Button(style);
		options.add(new Label("Options(In construction)", lStyle));
		options.setColor(new Color(100, 100, 100, 100));
		options.center();
		options.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				menu.changeMenu(2);
			}

		});

		mainTable.add(options);
		mainTable.row();

		Button grimoire = new Button(style);
		grimoire.add(new Label("Grimoire(In construction)", lStyle));
		grimoire.center();
		grimoire.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(3);
			}

		});

		mainTable.add(grimoire);
		mainTable.row();

		Button exit = new Button(style);
		exit.add(new Label("Close game", lStyle));
		exit.center();
		exit.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				menu.changeScreen(4);
			}

		});
		mainTable.add(exit);
		mainTable.row();

		p.addActor(mainTable);
		return p;

	}

	public Group gameMenu(final GameScreen game) {

		Group p = new Group();

		font.setUseIntegerPositions(true);
		ButtonStyle style = new ButtonStyle();
		// style.up = new TextureRegionDrawable(new TextureRegion(new
		// Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonover.png"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;

		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.8f);
		backMenu.setBounds(((float)Gdx.graphics.getWidth() * 0.22f) ,((float)Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());
//		img.setX(250);
//		img.setY(50);

		p.addActor(backMenu);
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = font;

		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);
		Button play = new Button(style);
		play.add(new Label("Resume", lStyle));
		play.center();
		play.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				game.onMenu = false;
				game.setInputProcessor();
			}

		});

		mainTable.add(play);
		mainTable.row();

		Button inventory = new Button(style);
		inventory.add(new Label("Status", lStyle));
		inventory.center();
		inventory.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				game.changeMenu1(3);
			}

		});

		mainTable.add(inventory);
		mainTable.row();

		Button options = new Button(style);
		options.add(new Label("Options(In construction)", lStyle));
		options.center();
		options.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				game.changeMenu1(2);
			}

		});

		mainTable.add(options);
		mainTable.row();

		Button grimorio = new Button(style);
		grimorio.add(new Label("Grimorio(In construction)", lStyle));
		grimorio.center();
		grimorio.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

			}

		});

		mainTable.add(grimorio);
		mainTable.row();

		Button exit = new Button(style);
		exit.add(new Label("Close game", lStyle));
		exit.center();
		exit.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				// TODO
			}

		});
		mainTable.add(exit);
		mainTable.row();

		p.addActor(mainTable);

		return p;
	}

	public Group optionMenu(final GameScreen game) {

		Group p = new Group();
		font.setUseIntegerPositions(true);
		ButtonStyle style = new ButtonStyle();
		// style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonover.png"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;

		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.8f);
		backMenu.setBounds(((float)Gdx.graphics.getWidth() * 0.22f) ,((float)Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());
	//	backMenu.setX(250);
	//	backMenu.setY(50);

		p.addActor(backMenu);
		
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = font;

		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("Video Options", lStyle));
		video.center();
		video.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				// game.changeMenu2();
			}

		});

		mainTable.add(video);
		mainTable.row();

		Button sound = new Button(style);
		sound.add(new Label("Sound Options", lStyle));
		sound.center();
		sound.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				// game.changeMenu2();
			}

		});

		mainTable.add(sound);
		mainTable.row();

		Button back = new Button(style);
		back.add(new Label("Back", lStyle));
		back.center();
		back.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				game.changeMenu1(1);
			}

		});
		mainTable.add(back);
		mainTable.row();

		p.addActor(mainTable);

		return p;

	}

	public Group optionMenu(final MenuScreen game) {

		Group p = new Group();
		font.setUseIntegerPositions(true);
		ButtonStyle style = new ButtonStyle();
		// style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonover.png"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;

		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.8f);
		backMenu.setBounds(((float)Gdx.graphics.getWidth() * 0.22f) ,((float)Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());
//		img.setX(250);
//		img.setY(50);

		p.addActor(backMenu);
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = font;

		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("Video Options", lStyle));
		video.center();
		video.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {
				// game.changeMenu2();
			}

		});

		mainTable.add(video);
		mainTable.row();

		Button sound = new Button(style);
		sound.add(new Label("Sound Options", lStyle));
		sound.center();
		sound.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				// game.changeMenu2();
			}

		});

		mainTable.add(sound);
		mainTable.row();

		Button back = new Button(style);
		back.add(new Label("Back", lStyle));
		back.center();
		back.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				game.changeMenu(1);
			}

		});
		mainTable.add(back);
		mainTable.row();

		p.addActor(mainTable);

		return p;

	}

	public Group statusMenu(final GameScreen game){
		
		
		Group p = new Group();
		
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setColor(0.8f,0.8f,0.8f,0.8f);
		background.setX(0);
		background.setY(0);
		
		p.addActor(background);
		
		font.setUseIntegerPositions(true);
		ButtonStyle style = new ButtonStyle();
		// style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonover.png"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;
		
		
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = font;
		
		Label nameChar = new Label("Name Character", lStyle);
		nameChar.setX( (float) (background.getWidth() * 0.05 + nameChar.getWidth()) );
		nameChar.setY( (float) (background.getHeight() * 0.95 - nameChar.getHeight()) );
		
		p.addActor(nameChar);
		
		
		
		Table optionTable = new Table();
		optionTable.defaults().width( 100 );
		optionTable.defaults().height( 50 );
		optionTable.setX( Gdx.graphics.getWidth() / 2 );
		optionTable.setY( Gdx.graphics.getHeight() / 2 );
		
		
		Button equip = new Button(style);
		equip.add(new Label("Equip",lStyle));
		equip.center();
		equip.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeMenuStatus(1);
				
			}
		});
		
		Button inventory = new Button(style);
		inventory.add(new Label("Inventory",lStyle));
		inventory.center();
		inventory.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeMenuStatus(2);
				
			}
		});
		Button back = new Button(style);
		back.add(new Label("Back",lStyle));
		back.center();
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeMenuStatus(3);
				
			}
		});
		optionTable.add(equip);
		optionTable.row();
		optionTable.add(inventory);
		optionTable.row();
		optionTable.add(back);
		
		p.addActor(optionTable);
		
		return p;
		
	}
	
	
}
