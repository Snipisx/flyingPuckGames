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
	
	public VideoMenu(ButtonStyle button, LabelStyle label, SelectBoxStyle selectBox,LabelStyle statusLabel){
		labelStatusStyle = statusLabel;
		boxStyle = selectBox;
		buttonStandard = button;
		labelMenusStyle = label;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
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
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);
		
		p.addActor(background);		
		
		Label title = new Label("VIDEO CONFIGURATION",labelStatusStyle);
		title.setBounds(WIDTH * 0.38f, HEIGHT * 0.65f, WIDTH * 0.25f,HEIGHT * 0.1f);
		
		p.addActor(title);
		
		Label resolution = new Label("RESOLUTION",labelMenusStyle);
		resolution.setBounds(WIDTH * 0.38f, HEIGHT * 0.55f, WIDTH * 0.25f,HEIGHT * 0.1f);
		
		p.addActor(resolution);
		
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
		String resolutions[] = new String[5];
		resolutions[0] = " 1024*768";
		resolutions[1] = " 1280*720";
		resolutions[2] = " 1366*768";
		resolutions[3] = " 1600*900";
		resolutions[4] = " 1920*1080";
		
		final SelectBox resolutionBox = new SelectBox(resolutions,boxStyle);
		
		resolutionBox.setBounds(WIDTH * 0.55f, HEIGHT * 0.575f, WIDTH * 0.13f, HEIGHT * 0.05f);
		resolutionBox.setSelection(2);
		p.addActor(resolutionBox);
		
		
		Button save = new Button(new Label("APPLY",labelMenusStyle),buttonStandard);
		
		save.setBounds(WIDTH * 0.40f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.05f);
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
		
		back.setBounds(WIDTH * 0.55f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.05f);
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

	public void setResolution(float width, float height) {
		WIDTH = width;
		HEIGHT = height;		
	}
}
