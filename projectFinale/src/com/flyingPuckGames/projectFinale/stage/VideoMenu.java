package com.flyingPuckGames.projectFinale.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.screens.GameScreen;

public class VideoMenu {

	
	
	private LabelStyle statusSt;
	private SelectBoxStyle boxStyle;
	private ButtonStyle style;
	private LabelStyle lStyle;
	
	public VideoMenu(ButtonStyle button, LabelStyle label, SelectBoxStyle selectBox,LabelStyle statusLabel){
		statusSt = statusLabel;
		boxStyle = selectBox;
		style = button;
		lStyle = label;
	}
	
	public Group create(final GameScreen gameScreen){
		Group p = new Group();
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.ESCAPE){
					gameScreen.changeMenuStatus(2);
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
		resolutions[0] = " 960X540";
		resolutions[1] = " 1024X576";
		resolutions[2] = " 1280X720";
		resolutions[3] = " 1366X768";
		resolutions[4] = " 1600X900";
		
		SelectBox resolutionBox = new SelectBox(resolutions,boxStyle);
		
		resolutionBox.setBounds(Gdx.graphics.getWidth() * 0.50f, Gdx.graphics.getHeight() * 0.575f, Gdx.graphics.getWidth() * 0.09f, Gdx.graphics.getHeight() * 0.05f);
		resolutionBox.setSelection(2);
		p.addActor(resolutionBox);
		
		
		Button save = new Button(new Label("APPLY",lStyle),style);
		
		save.setBounds(Gdx.graphics.getWidth() * 0.40f, Gdx.graphics.getHeight() * 0.45f, Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.05f);
		
		p.addActor(save);
		
	
		return p;


	}
}
