package com.flyingPuckGames.projectFinale.stage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.screens.GameScreen;

public class VideoMenu {

	
	
	private LabelStyle labelStatusStyle;
	private SelectBoxStyle boxStyle;
	private ButtonStyle buttonStandard;
	private LabelStyle labelMenusStyle;
	
	public VideoMenu(ButtonStyle button, LabelStyle label, SelectBoxStyle selectBox,LabelStyle statusLabel){
		labelStatusStyle = statusLabel;
		boxStyle = selectBox;
		buttonStandard = button;
		labelMenusStyle = label;
	}
	
	

	public Group create(final MenuController menuController, final boolean onMenu){
		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					if(onMenu){
						menuController.mainMenu(2);
					}else{
						menuController.status(2);
					}
				}
				return true;
			}
		});
	
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);
		
		p.addActor(background);		
		
		Label title = new Label("VIDEO CONFIGURATION",labelStatusStyle);
		title.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.65f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(title);
		
		Label resolution = new Label("RESOLUTION",labelMenusStyle);
		resolution.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.55f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(resolution);
		
//		DisplayMode displays[] = Gdx.graphics.getDisplayModes();
//		int numberOfDisplays = displays.length;
//		float arrayOfDisplays[][] = new float[numberOfDisplays][2];
//		int i = 0;
//		for(DisplayMode a : displays){
//			
//			arrayOfDisplays[i][0] = a.width;
//			arrayOfDisplays[i][1] = a.height;
//			System.out.println("Width = " + a.width + " Height = " + a.height);
//			i++;
//		}
		
		String resolutions[] = new String[5];
		resolutions[0] = " 800*600";
		resolutions[1] = " 1024*768";
		resolutions[2] = " 1280*1024";
		resolutions[3] = " 1366*768";
		resolutions[4] = " 1600*1024";
		
		final SelectBox resolutionBox = new SelectBox(resolutions,boxStyle);
		
		resolutionBox.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.575f, Gdx.graphics.getWidth() * 0.09f, Gdx.graphics.getHeight() * 0.05f);
		resolutionBox.setSelection(2);
		p.addActor(resolutionBox);
		
		
		Button save = new Button(new Label("APPLY",labelMenusStyle),buttonStandard);
		
		save.setBounds(Gdx.graphics.getWidth() * 0.40f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		save.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				if(onMenu){
					menuController.mainMenu(2);
				}else{
					menuController.status(2);	
				}
				menuController.setVideoSettings(resolutionBox.getSelectionIndex());
			}
		});
		p.addActor(save);
		
		Button back = new Button(new Label("BACK",labelMenusStyle),buttonStandard);
		
		back.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				if(onMenu){
					menuController.mainMenu(2);
				}else{
					menuController.status(2);
				}
				
			}
		});
		
		p.addActor(back);
	
		return p;
	}
}
