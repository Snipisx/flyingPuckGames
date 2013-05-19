package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class MenuBuilder {

	
	
	private BitmapFont font = new BitmapFont();
	private ButtonStyle style = new ButtonStyle();
	private LabelStyle lStyle = new LabelStyle();
	private TextFieldStyle  tt = new TextFieldStyle();
	
	public void setStyles(){
		font.setUseIntegerPositions(true);
		// style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonup.jpg"))));
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonpressed.jpg"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/buttonover.png"))));
		style.unpressedOffsetX = 5f;
		style.pressedOffsetX = style.unpressedOffsetX + 1f;
		style.pressedOffsetY = -1f;
		lStyle.font = font;
		tt.font = font;
		tt.fontColor = Color.YELLOW;
		
	}
	

	public Group mainMenu(final MenuScreen menu) {

		Group p = new Group();
		
		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.5f);
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.22f) ,(Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());
		
		p.addActor(backMenu);
		final Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.defaults().height(50);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button play = new Button(style);
		play.add(new Label("Play", lStyle));
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
		options.add(new Label("Options(In construction)", lStyle));
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
		grimoire.add(new Label("Grimoire(In construction)", lStyle));
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
		exit.add(new Label("Close game", lStyle));
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

	public Group gameMenu(final GameScreen game) {

		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					game.changeMenu(4);
					
				}
				return true;
			}
		});
		
		Image backMenu = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/gris.png")))));
		backMenu.setColor(0.2f, 0.2f, 0.2f, 0.8f);
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.22f) ,(Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());

		p.addActor(backMenu);

		
		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);
		Button play = new Button(style);
		play.add(new Label("Resume", lStyle));
		play.center();
		play.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.changeMenu(4);
				game.cont = 0;
			}

		});

		mainTable.add(play);
		mainTable.row();

		Button inventory = new Button(style);
		inventory.add(new Label("Status", lStyle));
		inventory.center();
		inventory.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				game.changeMenu(3);
			}

		});

		mainTable.add(inventory);
		mainTable.row();

		Button options = new Button(style);
		options.add(new Label("Options(In construction)", lStyle));
		options.center();
		options.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				game.changeMenu(2);
			}

		});

		mainTable.add(options);
		mainTable.row();

		Button grimorio = new Button(style);
		grimorio.add(new Label("Grimorio(In construction)", lStyle));
		grimorio.center();
		grimorio.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

			}

		});

		mainTable.add(grimorio);
		mainTable.row();

		Button exit = new Button(style);
		exit.add(new Label("Close game", lStyle));
		exit.center();
		exit.addListener(new ChangeListener() {

			@Override
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
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.22f) ,(Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());

		p.addActor(backMenu);
		
		
		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("Video Options", lStyle));
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
		sound.add(new Label("Sound Options", lStyle));
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
		back.add(new Label("Back", lStyle));
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

	public Group optionMenu(final MenuScreen game) {

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
		backMenu.setBounds((Gdx.graphics.getWidth() * 0.22f) ,(Gdx.graphics.getHeight() * 0.10f), backMenu.getWidth(), backMenu.getHeight());


		p.addActor(backMenu);

		Table mainTable = new Table();
		mainTable.defaults().width(300);
		mainTable.setX(Gdx.graphics.getWidth() / 2);
		mainTable.setY(Gdx.graphics.getHeight() / 2);

		Button video = new Button(style);
		video.add(new Label("Video Options", lStyle));
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
		sound.add(new Label("Sound Options", lStyle));
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
		back.add(new Label("Back", lStyle));
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

	public Group statusMenu(final GameScreen game){
		
		
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
		
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setColor(0.8f,0.8f,0.8f,0.8f);
		background.setX(0);
		background.setY(0);
		
		p.addActor(background);

		
		
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
	
	
	public Group equipMenu(final GameScreen game){
		
		Group p = new Group();
		
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				System.out.println("holatio");
				if(keycode == Keys.ESCAPE){
					game.changeMenuStatus(4);
				}
				
				return true;
			}
			
			});
		
		
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/equip.png")))));
		background.setColor(0.8f,0.8f,0.8f,0.8f);
		background.setBounds(0, 0, background.getWidth(), background.getHeight());
		
		p.addActor(background);
		
		Table equipedTab = new Table();
		equipedTab.setName("equip");
		equipedTab.defaults().width( 150 );
		equipedTab.defaults().height( 50 );
		equipedTab.setBounds(Gdx.graphics.getWidth() * 0.45f, Gdx.graphics.getHeight() * 0.55f, Gdx.graphics.getWidth() * 0.2f,Gdx.graphics.getHeight() * 0.5f );
		
		
		Label weapon = new Label("weapon", lStyle);
		
		equipedTab.add(weapon);
		equipedTab.row();
		Label armor = new Label("armor", lStyle);

		equipedTab.add(armor);
		equipedTab.row();
		Label accesory1 = new Label("accesory1", lStyle);

		equipedTab.add(accesory1);
		equipedTab.row();
		Label accesory2 = new Label("accesory2", lStyle);

		equipedTab.add(accesory2);
		equipedTab.row();
		Label accesory3 = new Label("accesory3", lStyle);

		equipedTab.add(accesory3);
		equipedTab.row();	
		
		p.addActor(equipedTab);
		
		
		Table inventoryTab = new Table();
		inventoryTab.defaults().height(50);
		inventoryTab.defaults().width(Gdx.graphics.getWidth() * 0.15f);
		
		
		
		Label item1 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item1);
		Label item1Amount = new Label("0",lStyle);
		inventoryTab.add(item1Amount);
		Label item2 = new Label("Espada de la ostia2",lStyle);
		inventoryTab.add(item2);
		Label item2Amount = new Label("0",lStyle);
		inventoryTab.add(item2Amount);
		Label item3 = new Label("Espada de la ostia3",lStyle);
		inventoryTab.add(item3);
		Label item3Amount = new Label("0",lStyle);
		inventoryTab.add(item3Amount);
		inventoryTab.row();
		Label item4 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item4);
		Label item4Amount = new Label("0",lStyle);
		inventoryTab.add(item4Amount);
		Label item5 = new Label("item2",lStyle);
		inventoryTab.add(item5);
		Label item5Amount = new Label("0",lStyle);
		inventoryTab.add(item5Amount);
		Label item6 = new Label("item3",lStyle);
		inventoryTab.add(item6);
		Label item6Amount = new Label("0",lStyle);
		inventoryTab.add(item6Amount);
		inventoryTab.row();
		Label item7 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item7);
		Label item7Amount = new Label("0",lStyle);
		inventoryTab.add(item7Amount);
		Label item8 = new Label("item2",lStyle);
		inventoryTab.add(item8);
		Label item8Amount = new Label("0",lStyle);
		inventoryTab.add(item8Amount);
		Label item9 = new Label("item3",lStyle);
		inventoryTab.add(item9);
		Label item9Amount = new Label("0",lStyle);
		inventoryTab.add(item9Amount);
		inventoryTab.row();
		Label item10 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item10);
		Label item11Amount = new Label("0",lStyle);
		inventoryTab.add(item11Amount);
		Label item12 = new Label("Espada de la ostia2",lStyle);
		inventoryTab.add(item12);
		Label item12Amount = new Label("0",lStyle);
		inventoryTab.add(item12Amount);
		Label item13 = new Label("Espada de la ostia3",lStyle);
		inventoryTab.add(item13);
		Label item13Amount = new Label("0",lStyle);
		inventoryTab.add(item13Amount);
		inventoryTab.row();
		Label item14 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item14);
		Label item14Amount = new Label("0",lStyle);
		inventoryTab.add(item14Amount);
		Label item15 = new Label("item2",lStyle);
		inventoryTab.add(item15);
		Label item15Amount = new Label("0",lStyle);
		inventoryTab.add(item15Amount);
		Label item16 = new Label("item3",lStyle);
		inventoryTab.add(item16);
		Label item16Amount = new Label("0",lStyle);
		inventoryTab.add(item16Amount);
		inventoryTab.row();
		Label item17 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item17);
		Label item17Amount = new Label("0",lStyle);
		inventoryTab.add(item17Amount);
		Label item18 = new Label("item2",lStyle);
		inventoryTab.add(item18);
		Label item18Amount = new Label("0",lStyle);
		inventoryTab.add(item18Amount);
		Label item19 = new Label("item3",lStyle);
		inventoryTab.add(item19);
		Label item19Amount = new Label("0",lStyle);
		inventoryTab.add(item19Amount);
		inventoryTab.row();
		
		ScrollPane inventory = new ScrollPane(inventoryTab);
		inventory.setBounds(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.25f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()*0.22f);
		inventory.setScrollingDisabled(true, false);
		p.addActor(inventory);

		
		
		return p;
	}
	
	
}
