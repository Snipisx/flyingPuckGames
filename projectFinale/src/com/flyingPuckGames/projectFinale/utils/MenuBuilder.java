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
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.SecondGameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;
import com.flyingPuckGames.projectFinale.stage.EquipMenu;
import com.flyingPuckGames.projectFinale.stage.MainMenu;
import com.flyingPuckGames.projectFinale.stage.OptionMenu;
import com.flyingPuckGames.projectFinale.stage.SoundMenu;
import com.flyingPuckGames.projectFinale.stage.StatusMenu;
import com.flyingPuckGames.projectFinale.stage.VideoMenu;

public class MenuBuilder {
	
	private BitmapFont fontMenus;
	private BitmapFont fontStatus;
	private BitmapFont fontName;
	private ButtonStyle style = new ButtonStyle();
	private ButtonStyle buttonMore = new ButtonStyle();
	private ButtonStyle buttonLess = new ButtonStyle();
	private LabelStyle lStyle = new LabelStyle();
	private LabelStyle statusSt = new LabelStyle();
	private LabelStyle nameStyle = new LabelStyle();
	private SliderStyle slider = new SliderStyle();
	private Color statusColor;
	private MainMenu mainMenu;
	private OptionMenu optionMenu;
	private StatusMenu statusMenu;
	private EquipMenu equipMenu;
	private SelectBoxStyle boxStyle = new SelectBoxStyle();
	private VideoMenu videoMenu;
	private SoundMenu soundMenu;
	
	private void setStyles(){
		//labels
		FreeTypeFontGenerator f = new FreeTypeFontGenerator(Gdx.files.internal("fonts/akiras_font.ttf"));
		fontMenus = f.generateFont((int) (Gdx.graphics.getWidth() * 0.012f));
		fontStatus = f.generateFont((int) (Gdx.graphics.getWidth() * 0.016f));
		fontName = f.generateFont((int) (Gdx.graphics.getWidth() * 0.036f));
		f.dispose();
		
		slider.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox.png"))));
		slider.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox1.png"))));
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
		buttonMore.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/more.png"))));
		buttonMore.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/moreDown.png"))));
		buttonMore.pressedOffsetX = Gdx.graphics.getWidth() * 0.3f;
		buttonLess.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/less.png"))));
		buttonLess.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/lessDown.png"))));
		buttonLess.pressedOffsetX = Gdx.graphics.getWidth() * -0.3f;
	}
	
	public void init(){
		setStyles();
		mainMenu = new MainMenu(style,lStyle);
		optionMenu = new OptionMenu(style,lStyle);
		statusMenu = new StatusMenu(style,lStyle,statusSt,nameStyle,statusColor);
		equipMenu = new EquipMenu(style,lStyle);
		videoMenu = new VideoMenu(style,lStyle,boxStyle,statusSt);
		soundMenu = new SoundMenu(style,lStyle,slider,statusSt,buttonMore,buttonLess);
	}

	public Group mainMenu(final MenuController menuController) {
		Group main = mainMenu.create(menuController);
		return main ;
	}

	public Group optionMenu(final MenuController menuController){
		Group option = optionMenu.createGame(menuController);
		return option;
	}

	public Group optionMainMenu(final MenuController menuController) {
		Group option = optionMenu.createMenu(menuController);
		return option;
	}

	public Group statusMenu(final MenuController menuController){
		Group status = statusMenu.create(menuController);
		return status;
	}
	
	
	public Group equipMenu(final MenuController menuController){
		Group equip = equipMenu.create(menuController);
		return equip;
	}
	
	/*
	 * Create the menu of Video Options In-Game
	 */
	public Group videoOptionsGame(final MenuController menuController){
		Group video = videoMenu.createGame(menuController);
		return video;
	}
	/*
	 * Create the menu of Video options OnMainMenu
	 */
	public Group videoOptionsMenu(final MenuController menuController){
		Group video = videoMenu.createMenu(menuController);
		return video;
	}
	
	public Group SoundOptionsGame(final MenuController menuController){
		Group sound = soundMenu.createGame(menuController);
		return sound;
	}
	
}
