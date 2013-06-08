package com.flyingPuckGames.projectFinale.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.MenuScreen;

public class OptionMenu {

	private ButtonStyle buttonStandard;
	private LabelStyle labelMenusStyle;
	private float WIDTH;
	private float HEIGHT;
	
	public OptionMenu(ButtonStyle button,LabelStyle label){
		buttonStandard = button;
		labelMenusStyle = label;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}
	/*
	 * Method that create the window
	 */
	public Group create(final MenuController menuController,final boolean onMenu) {
		System.out.println(WIDTH + "      " + HEIGHT);
		Group p = new Group();
		
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					if(onMenu){
						menuController.mainMenu(5);
					}else{
						menuController.status(4);
					}
					
				}
				
				return true;
			}
		});

		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/statusBack.png")))));
		background.setBounds(0, 0, WIDTH, HEIGHT);
		background.setColor(0.5f,0.5f, 0.5f, 0.8f);

		p.addActor(background);
		
		Table mainTable = new Table();
		mainTable.defaults().width(WIDTH * 0.2f);
		mainTable.defaults().height(HEIGHT* 0.05f);
		mainTable.setX(WIDTH / 2);
		mainTable.setY(HEIGHT / 2);
		if(!(Gdx.app.getType() == ApplicationType.Android)){
			
			Button video = new Button(buttonStandard);
			video.add(new Label("VIDEO", labelMenusStyle));
			video.center();
			video.addListener(new ChangeListener() {
	
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(onMenu){
						menuController.optionsMenu(1);
					}else{
						menuController.optionsGame(1);
					}
				}
	
			});
	
			mainTable.add(video);
			mainTable.row();
		}
		Button sound = new Button(buttonStandard);
		sound.add(new Label("SOUND", labelMenusStyle));
		sound.center();
		sound.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(onMenu){
					menuController.optionsMenu(2);
				}else{
					menuController.optionsGame(2);
				}
				
			}

		});

		mainTable.add(sound);
		mainTable.row();

		Button back = new Button(buttonStandard);
		back.add(new Label("BACK", labelMenusStyle));
		back.center();
		back.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(onMenu){
					menuController.mainMenu(5);
				}else{
					menuController.status(4);
				}
			}
		});
		mainTable.add(back);
		mainTable.row();

		p.addActor(mainTable);
		
		return p;
	}
	
	public void setResolution(float width, float height) {
		WIDTH = width;
		HEIGHT = height;
	}
}
