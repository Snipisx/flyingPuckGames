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
	private float WIDTH;
	private float HEIGHT;
	private Label volumeText;
	private Label volume;
	
	
	public SoundMenu(ButtonStyle button, LabelStyle label,LabelStyle statusLabel,ButtonStyle buttonMore,ButtonStyle buttonLess){
		labelStatusStyle = statusLabel;
		buttonStandard = button;
		labelMenusStyle = label;
		this.buttonLess = buttonLess;
		this.buttonMore = buttonMore;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}
	
	
	
	private void setInfo(MenuController menuController){
		String[] info = menuController.getSoundSettings();
		System.out.println(info[0] + "   " + info[1]);
		volumeText = new Label(info[0],labelMenusStyle);
		volume = new Label(info[1],labelMenusStyle);
	}
	
	public Group create(final MenuController menuController,final Boolean onMenu){
		setInfo(menuController);
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
		
		Label title = new Label("SOUND CONFIGURATION",labelStatusStyle);
		title.setBounds(WIDTH * 0.38f, HEIGHT * 0.65f, WIDTH * 0.25f,HEIGHT * 0.1f);
		
		p.addActor(title);
		
		Label resolution = new Label("LEVEL",labelMenusStyle);
		resolution.setBounds(WIDTH * 0.38f, HEIGHT * 0.55f, WIDTH * 0.25f,HEIGHT * 0.1f);
		
		p.addActor(resolution);
		
		Button volumeOn = new Button(volumeText,buttonStandard);
		
		volumeOn.setBounds(WIDTH * 0.50f, HEIGHT * 0.575f, WIDTH * 0.09f, HEIGHT * 0.05f);
		
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
		setVolumeLevel.setBounds(WIDTH * 0.35f, HEIGHT * 0.5f,WIDTH * 0.2f, HEIGHT * 0.1f);
		p.addActor(setVolumeLevel);
		
		
		Table setVolume = new Table();
		setVolume.setBounds(WIDTH * 0.5f, HEIGHT*0.5f, WIDTH * 0.1f, HEIGHT * 0.1f);
		
		Button less = new Button(buttonLess);
		less.setWidth(WIDTH * 0.3f);
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
		more.setWidth(WIDTH * 0.3f);
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
		
		save.setBounds(WIDTH * 0.35f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.05f);
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
		
		back.setBounds(WIDTH * 0.50f, HEIGHT * 0.45f, WIDTH * 0.1f, HEIGHT * 0.05f);
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
