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

	
	
	private LabelStyle labelStatusStyle;
	private ButtonStyle buttonStandard;
	private LabelStyle labelMenusStyle;
	private ButtonStyle buttonMore;
	private ButtonStyle buttonLess;
	
	public SoundMenu(ButtonStyle button, LabelStyle label,LabelStyle statusLabel,ButtonStyle buttonMore,ButtonStyle buttonLess){
		labelStatusStyle = statusLabel;
		buttonStandard = button;
		labelMenusStyle = label;
		this.buttonLess = buttonLess;
		this.buttonMore = buttonMore;
	}
	
	public Group create(final MenuController menuController,final Boolean onMenu){
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
		
		Label title = new Label("SOUND CONFIGURATION",labelStatusStyle);
		title.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.65f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(title);
		
		Label resolution = new Label("LEVEL",labelMenusStyle);
		resolution.setBounds(Gdx.graphics.getWidth() * 0.38f, Gdx.graphics.getHeight() * 0.55f, Gdx.graphics.getWidth() * 0.25f,Gdx.graphics.getHeight() * 0.1f);
		
		p.addActor(resolution);
		
		final Label volumeText = new Label("ON",labelMenusStyle);
		
		Button volumeOn = new Button(volumeText,buttonStandard);
		
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
		
		
		Label setVolumeLevel = new Label("SET VOLUME",labelMenusStyle);
		setVolumeLevel.setBounds(Gdx.graphics.getWidth() * 0.35f, Gdx.graphics.getHeight() * 0.5f,Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.1f);
		p.addActor(setVolumeLevel);
		
		
		Table setVolume = new Table();
		setVolume.setBounds(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight()*0.5f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.1f);
		final Label volume = new Label("0",labelMenusStyle);
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
		
		Button save = new Button(new Label("APPLY",labelMenusStyle),buttonStandard);
		
		save.setBounds(Gdx.graphics.getWidth() * 0.35f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		save.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event,Actor actor) {
				if(onMenu){
					menuController.mainMenu(2);
				}else{
					menuController.status(2);	
				}
				menuController.setSoundSettings(Integer.parseInt(volume.getText().toString()),volumeText.getText().toString().equals("ON"));
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
