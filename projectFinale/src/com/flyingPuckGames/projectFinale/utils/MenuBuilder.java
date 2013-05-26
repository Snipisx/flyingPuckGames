package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.stage.EquipMenu;
import com.flyingPuckGames.projectFinale.stage.MainMenu;
import com.flyingPuckGames.projectFinale.stage.OptionMenu;
import com.flyingPuckGames.projectFinale.stage.StatusMenu;

public class MenuBuilder {

	
	
	private BitmapFont fontMenus;
	private BitmapFont fontStatus;
	private BitmapFont fontName;
	private ButtonStyle style = new ButtonStyle();
	private LabelStyle lStyle = new LabelStyle();
	private LabelStyle statusSt = new LabelStyle();
	private LabelStyle nameStyle = new LabelStyle();
	private Color statusColor;
	private MainMenu mainMenu;
	private OptionMenu optionMenu;
	private StatusMenu statusMenu;
	private EquipMenu equipMenu;
	
	public void setStyles(){
		//labels
		FreeTypeFontGenerator f = new FreeTypeFontGenerator(Gdx.files.internal("fonts/akiras_font.ttf"));
		fontMenus = f.generateFont(12);
		fontStatus = f.generateFont(16);
		fontName = f.generateFont(32);
		f.dispose();
		
		statusSt.font = fontStatus;
		statusSt.fontColor = Color.valueOf("ededed");
		
		lStyle.font = fontMenus;
		lStyle.fontColor = Color.valueOf("ededed");
		
		statusColor = Color.valueOf("2BCEBF");
		
		nameStyle.font = fontName;
		nameStyle.fontColor = Color.valueOf("FF4FB5");
		
		//Buttons
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selected2.png"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/hover2.png"))));
	}
	
	public void init(){
		mainMenu = new MainMenu(style,lStyle);
		optionMenu = new OptionMenu(style,lStyle);
		statusMenu = new StatusMenu(style,lStyle,statusSt,nameStyle,statusColor);
		equipMenu = new EquipMenu(style,lStyle);
	}

	public Group mainMenu(final MenuScreen menu) {
		Group main = mainMenu.create(menu);
		return main ;
	}

	public Group optionMenu(final GameScreen game){
		Group option = optionMenu.createGame(game);
		return option;
	}

	public Group optionMenu(final MenuScreen game) {
		Group option = optionMenu.createMenu(game);
		return option;
	}

	public Group statusMenu(final GameScreen game){
		Group status = statusMenu.create(game);
		return status;
	}
	
	
	public Group equipMenu(final GameScreen game){
		Group equip = equipMenu.create(game);
		return equip;
	}
	
	public Group videoOptions(final MenuScreen game){
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
