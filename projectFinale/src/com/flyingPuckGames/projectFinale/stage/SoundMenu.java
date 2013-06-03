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
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.screens.GameScreen;

public class SoundMenu {

	
	
	private LabelStyle statusSt;
	private SliderStyle slider;
	private ButtonStyle style;
	private LabelStyle lStyle;
	private ButtonStyle buttonMore;
	private ButtonStyle buttonLess;
	
	public SoundMenu(ButtonStyle button, LabelStyle label, SliderStyle slider,LabelStyle statusLabel,ButtonStyle buttonMore,ButtonStyle buttonLess){
		statusSt = statusLabel;
		this.slider = slider;
		style = button;
		lStyle = label;
		this.buttonLess = buttonLess;
		this.buttonMore = buttonMore;
	}
	
	public Group createGame(final MenuController menuController){
		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					menuController.status(2);
				}
				return true;
			}
		});
	
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);
		
		p.addActor(background);		
		
		Label title = new Label("SOUND CONFIGURATION",statusSt);
		title.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.65f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(title);
		
		Label resolution = new Label("LEVEL",lStyle);
		resolution.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.55f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(resolution);
		
		final Label volumeText = new Label("ON",lStyle);
		
		Button volumeOn = new Button(volumeText,style);
		
		volumeOn.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.575f, Gdx.graphics.getWidth() * 0.09f, Gdx.graphics.getHeight() * 0.05f);
		
		volumeOn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(volumeText.getText().toString().equals("ON")){
					volumeText.setText("OFF");
				}else{
					volumeText.setText("ON");
				}
			}
		});
		
		
		p.addActor(volumeOn);
		
		
		Label setVolumeLevel = new Label("SET VOLUME",lStyle);
		setVolumeLevel.setBounds(Gdx.graphics.getWidth() * 0.35f, Gdx.graphics.getHeight() * 0.5f,Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.1f);
		p.addActor(setVolumeLevel);
		
		
		Table setVolume = new Table();
		setVolume.setBounds(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight()*0.5f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.1f);
		final Label volume = new Label("0",lStyle);
		Button less = new Button(buttonLess);
		less.setWidth(Gdx.graphics.getWidth() * 0.3f);
		less.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				int a = Integer.parseInt(volume.getText().toString());
				if(a == 0){
					return;
				}else{
					a--;
					volume.setText(Integer.toString(a));
				}
				
			}
		});
		setVolume.row();
		setVolume.add(less).expand();
		volume.setWidth(Gdx.graphics.getWidth() * 0.1f);
		setVolume.add(volume).fill().padLeft(1f).padRight(1f);
		Button more = new Button(buttonMore);
		more.setWidth(Gdx.graphics.getWidth() * 0.3f);
		more.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				int a = Integer.parseInt(volume.getText().toString());
				if(a == 10){
					return;
				}else{
					a++;
					volume.setText(Integer.toString(a));
				}
				
			}
		});
		
		setVolume.add(more).expand();
		
		p.addActor(setVolume);
		
		Button save = new Button(new Label("APPLY",lStyle),style);
		
		save.setBounds(Gdx.graphics.getWidth() * 0.40f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		save.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				menuController.status(2);
				//menuController.setSoundSettings((int) volume.getValue());
			}
		});
		p.addActor(save);
		
		Button back = new Button(new Label("BACK",lStyle),style);
		
		back.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				menuController.status(2);
			}
		});
		
		p.addActor(back);
	
		return p;
	}
	
	public Group createMenu(final MenuController menuController){
		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					menuController.mainMenu(2);
				}
				
				return true;
			}
		});
	
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);
		
		p.addActor(background);		
		
		Label title = new Label("VIDEO CONFIGURATION",statusSt);
		title.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.65f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(title);
		
		Label resolution = new Label("RESOLUTION",lStyle);
		resolution.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.55f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(resolution);
		
		String resolutions[] = new String[5];
		resolutions[0] = " 960*540";
		resolutions[1] = " 1024@576";
		resolutions[2] = " 1280*720";
		resolutions[3] = " 1366*768";
		resolutions[4] = " 1600*900";
		
//		final SelectBox resolutionBox = new SelectBox(resolutions,boxStyle);
//		
//		resolutionBox.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.575f, Gdx.graphics.getWidth() * 0.09f, Gdx.graphics.getHeight() * 0.05f);
//		resolutionBox.setSelection(2);
//		p.addActor(resolutionBox);
		
		
		Button save = new Button(new Label("APPLY",lStyle),style);
		
		save.setBounds(Gdx.graphics.getWidth() * 0.40f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		save.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				menuController.mainMenu(2);
//				menuController.setVideoSettings(resolutionBox.getSelectionIndex());
			}
		});
		p.addActor(save);
		
		Button back = new Button(new Label("BACK",lStyle),style);
		
		back.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				menuController.mainMenu(2);
			}
		});
		
		p.addActor(back);
	
		return p;
	}
}
