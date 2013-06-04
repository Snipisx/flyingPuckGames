package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.stage.EquipMenu;
import com.flyingPuckGames.projectFinale.stage.MainMenu;
import com.flyingPuckGames.projectFinale.stage.OptionMenu;
import com.flyingPuckGames.projectFinale.stage.SoundMenu;
import com.flyingPuckGames.projectFinale.stage.StatusMenu;
import com.flyingPuckGames.projectFinale.stage.VideoMenu;
import com.flyingPuckGames.projectFinale.stage.GrimoireMenu;

public class MenuBuilder {
	
	private BitmapFont fontMenus;
	private BitmapFont fontStatus;
	private BitmapFont fontName;
	private ButtonStyle buttonStandard = new ButtonStyle();
	private ButtonStyle buttonMore = new ButtonStyle();
	private ButtonStyle buttonLess = new ButtonStyle();
	private LabelStyle labelMenusStyle = new LabelStyle();
	private LabelStyle labelStatusStyle = new LabelStyle();
	private LabelStyle nameStyle = new LabelStyle();
	private Color statusColor;
	private MainMenu mainMenu;
	private OptionMenu optionMenu;
	private StatusMenu statusMenu;
	private EquipMenu equipMenu;
	private SelectBoxStyle boxStyle = new SelectBoxStyle();
	private VideoMenu videoMenu;
	private SoundMenu soundMenu;
	private GrimoireMenu grimoireMenu;
	
	private void setStyles(){
		//labels
		
		setFonts(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		boxStyle.font = fontMenus;
		boxStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox.png"))));
		boxStyle.listBackground = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/negro.png"))));
		boxStyle.listSelection = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox1.png"))));
		boxStyle.itemSpacing = 1f;
		boxStyle.fontColor = Color.valueOf("2BCEBF");
		
		labelStatusStyle.font = fontStatus;
		labelStatusStyle.fontColor = Color.valueOf("ededed");
		
		labelMenusStyle.font = fontMenus;
		labelMenusStyle.fontColor = Color.valueOf("ededed");
		
		statusColor = Color.valueOf("2BCEBF");
		
		nameStyle.font = fontName;
		nameStyle.fontColor = Color.valueOf("FF4FB5");
		//nameStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/negro.png"))));
		
		//Buttons
		buttonStandard.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selected2.png"))));
		buttonStandard.over = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/hover2.png"))));
		buttonMore.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/more.png"))));
		buttonMore.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/moreDown.png"))));
		buttonMore.pressedOffsetX = Gdx.graphics.getWidth() * 0.3f;
		buttonLess.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/less.png"))));
		buttonLess.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/lessDown.png"))));
		buttonLess.pressedOffsetX = Gdx.graphics.getWidth() * -0.3f;
	}
	
	public void onResize(float width,float height){
		setFonts(width,height);
		setResolutions(width,height);
	}
	
	public void setResolutions(float width,float height){
		mainMenu.setResolution(width,height);
		optionMenu.setResolution(width,height);
		statusMenu.setResolution(width,height);
		equipMenu.setResolution(width,height);
		videoMenu.setResolution(width,height);
		soundMenu.setResolution(width,height);
		grimoireMenu.setResolution(width,height);
	}
	
	public void setFonts(float width,float height){
		FreeTypeFontGenerator f = new FreeTypeFontGenerator(Gdx.files.internal("fonts/akiras_font.ttf"));
		fontMenus = f.generateFont((int) (width* 0.012f));
		fontStatus = f.generateFont((int) (width * 0.016f));
		fontName = f.generateFont((int) (width * 0.036f));
		f.dispose();
	}
	
	public void init(){
		setStyles();
		mainMenu = new MainMenu(buttonStandard,labelMenusStyle);
		optionMenu = new OptionMenu(buttonStandard,labelMenusStyle);
		statusMenu = new StatusMenu(buttonStandard,labelMenusStyle,labelStatusStyle,nameStyle,statusColor);
		equipMenu = new EquipMenu(buttonStandard,labelMenusStyle);
		videoMenu = new VideoMenu(buttonStandard,labelMenusStyle,boxStyle,labelStatusStyle);
		soundMenu = new SoundMenu(buttonStandard,labelMenusStyle,labelStatusStyle,buttonMore,buttonLess);
		grimoireMenu = new GrimoireMenu(labelMenusStyle, buttonStandard);
	}

	public Group mainMenu(final MenuController menuController) {
		Group main = mainMenu.create(menuController);
		return main ;
	}

	public Group optionMenu(final MenuController menuController, boolean onMenu){
		Group option = optionMenu.create(menuController,onMenu);
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
	 * Create the menu of Video Options 
	 */
	public Group videoOptions(final MenuController menuController, boolean onMenu){
		Group video = videoMenu.create(menuController,onMenu);
		return video;
	}
	/*
	 * Create the menu of Audio options 
	 */

	
	public Group SoundOptions(final MenuController menuController, boolean onMenu){
		Group sound = soundMenu.create(menuController,onMenu);
		return sound;
	}
	
	public Group GrimoireMenu(final MenuController menuController,boolean onMenu){
		Group grimoire = grimoireMenu.create(menuController,onMenu);
		return grimoire;
	}
	
}
