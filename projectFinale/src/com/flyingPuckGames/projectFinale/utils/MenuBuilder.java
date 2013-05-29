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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.SecondGameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.stage.EquipMenu;
import com.flyingPuckGames.projectFinale.stage.MainMenu;
import com.flyingPuckGames.projectFinale.stage.OptionMenu;
import com.flyingPuckGames.projectFinale.stage.StatusMenu;
import com.flyingPuckGames.projectFinale.stage.VideoMenu;

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
	private SelectBoxStyle boxStyle = new SelectBoxStyle();
	private VideoMenu videoMenu;
	
	public void setStyles(){
		//labels
		FreeTypeFontGenerator f = new FreeTypeFontGenerator(Gdx.files.internal("fonts/akiras_font.ttf"));
		fontMenus = f.generateFont(12);
		fontStatus = f.generateFont(16);
		fontName = f.generateFont(32);
		f.dispose();
		
		boxStyle.font = fontMenus;
		boxStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox.png"))));
		boxStyle.listBackground = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/negro.png"))));
		boxStyle.listSelection = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox1.png"))));
		boxStyle.itemSpacing = 1f;
		boxStyle.fontColor = Color.valueOf("2BCEBF");
		
		statusSt.font = fontStatus;
		statusSt.fontColor = Color.valueOf("ededed");
		
		lStyle.font = fontMenus;
		lStyle.fontColor = Color.valueOf("ededed");
		
		statusColor = Color.valueOf("2BCEBF");
		
		nameStyle.font = fontName;
		nameStyle.fontColor = Color.valueOf("FF4FB5");
		//nameStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/negro.png"))));
		
		//Buttons
		style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selected2.png"))));
		style.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/hover2.png"))));
	}
	
	public void init(){
		mainMenu = new MainMenu(style,lStyle);
		optionMenu = new OptionMenu(style,lStyle);
		statusMenu = new StatusMenu(style,lStyle,statusSt,nameStyle,statusColor);
		equipMenu = new EquipMenu(style,lStyle);
		videoMenu = new VideoMenu(style,lStyle,boxStyle,statusSt);
	}

	public Group mainMenu(final MenuScreen menu) {
		Group main = mainMenu.create(menu);
		return main ;
	}

	public Group optionMenu(final GameScreen gameScreen){
		Group option = optionMenu.createGame(gameScreen);
		return option;
	}

	public Group optionMenu(final MenuScreen game) {
		Group option = optionMenu.createMenu(game);
		return option;
	}

	public Group statusMenu(final GameScreen gameScreen){
		Group status = statusMenu.create(gameScreen);
		return status;
	}
	
	
	public Group equipMenu(final GameScreen gameScreen){
		Group equip = equipMenu.create(gameScreen);
		return equip;
	}
	
	public Group videoOptions(final GameScreen gameScreen){
		Group video = videoMenu.create(gameScreen);
			
		return video;


	}
	
	
}
