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
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.controller.MenuController;

public class VideoMenu {

	
	
	private LabelStyle labelStatusStyle;
	private SelectBoxStyle boxStyle;
	private ButtonStyle buttonStandard;
	private LabelStyle labelMenusStyle;
	private float WIDTH;
	private float HEIGHT;
	private SelectBox resolutionBox;
	
	public VideoMenu(ButtonStyle button, LabelStyle label, SelectBoxStyle selectBox,LabelStyle statusLabel){
		labelStatusStyle = statusLabel;
		boxStyle = selectBox;
		buttonStandard = button;
		labelMenusStyle = label;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}

	
	/*
	 * set the info of the Video Options.
	 */
	private void setInfo(MenuController menuController){
		
		String resolutions[] = new String[4];
		resolutions[0] = " 1280*720";
		resolutions[1] = " 1366*768";
		resolutions[2] = " 1600*900";
		resolutions[3] = " 1920*1080";
		
		resolutionBox = new SelectBox(resolutions,boxStyle);
		resolutionBox.setSelection(menuController.getVideoSettings()); 
		
	}
	/*
	 * Method that create the window
	 */
	
	public Group create(final MenuController menuController, final boolean onMenu){
		setInfo(menuController);
		Group stage = new Group();
		stage.addListener(new InputListener() {
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
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);
		
		stage.addActor(background);		
		
		Label title = new Label("VIDEO CONFIGURATION",labelStatusStyle);
		title.setBounds(WIDTH * 0.38f, HEIGHT * 0.65f, WIDTH * 0.25f,HEIGHT * 0.1f);
		
		stage.addActor(title);
		
		Label resolution = new Label("RESOLUTION",labelMenusStyle);
		resolution.setBounds(WIDTH * 0.38f, HEIGHT * 0.55f, WIDTH * 0.25f,HEIGHT * 0.1f);
		
		stage.addActor(resolution);
		
//		DisplayMode displays[] = Gdx.graphics.getDisplayModes();
//		int numberOfDisplays = displays.length;
//		float arrayOfDisplays[][] = new float[numberOfDisplays][2];
//		int i = 0;
//		for(DisplayMode a : displays){
//			
//			arrayOfDisplays[i][0] = a.width;
//			arrayOfDisplays[i][1] = a.height;
//			System.out.println( a.toString());
//			i++;
//		}
//		
		
		resolutionBox.setBounds(WIDTH * 0.55f, HEIGHT * 0.575f, WIDTH * 0.13f, HEIGHT * 0.05f);
		stage.addActor(resolutionBox);
		
		
		Button saveButton = new Button(new Label("APPLY",labelMenusStyle),buttonStandard);
		
		saveButton.setBounds(WIDTH * 0.40f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.05f);
		saveButton.addListener(new ChangeListener() {
			
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
		stage.addActor(saveButton);
		
		Button backButton = new Button(new Label("BACK",labelMenusStyle),buttonStandard);
		
		backButton.setBounds(WIDTH * 0.55f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.05f);
		backButton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				if(onMenu){
					menuController.mainMenu(2);
				}else{
					menuController.status(2);
				}
				
			}
		});
		
		stage.addActor(backButton);
	
		return stage;
	}

	public void setResolution(float width, float height) {
		WIDTH = width;
		HEIGHT = height;		
	}
}
