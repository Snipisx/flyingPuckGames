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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.flyingPuckGames.projectFinale.screens.GameScreen;
import com.flyingPuckGames.projectFinale.screens.SecondGameScreen;

public class EquipMenu {

	private ButtonStyle style;
	private LabelStyle lStyle;
	
	public EquipMenu(ButtonStyle button, LabelStyle label){
		style = button;
		lStyle = label;
	}
	
	public Group create(final GameScreen gameScreen){
		
		Group p = new Group();
		
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				System.out.println("holatio");
				if(keycode == Keys.ESCAPE){
					gameScreen.changeMenuStatus(4);
				}
				
				return true;
			}
			
			});
		
		
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/equip.png")))));
		background.setColor(0.8f,0.8f,0.8f,0.8f);
		background.setBounds(0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		
		
		p.addActor(background);
		
		Table equipedTab = new Table();
		equipedTab.setName("equip");
		equipedTab.defaults().width( Gdx.graphics.getWidth() * 0.12f );
		equipedTab.defaults().height( Gdx.graphics.getHeight() * 0.08f );
		equipedTab.setBounds(Gdx.graphics.getWidth() * 0.45f, Gdx.graphics.getHeight() * 0.55f, Gdx.graphics.getWidth() * 0.2f,Gdx.graphics.getHeight() * 0.5f );
		
		
		Label weapon = new Label("WEAPON", lStyle);
		
		equipedTab.add(weapon);
		equipedTab.row();
		Label armor = new Label("ARMOR", lStyle);

		equipedTab.add(armor);
		equipedTab.row();
		Label accesory1 = new Label("ACCESORY1", lStyle);

		equipedTab.add(accesory1);
		equipedTab.row();
		Label accesory2 = new Label("ACCESORY2", lStyle);

		equipedTab.add(accesory2);
		equipedTab.row();
		Label accesory3 = new Label("ACCESORY3", lStyle);

		equipedTab.add(accesory3);
		equipedTab.row();	
		
		p.addActor(equipedTab);
		
		
		Button back = new Button((new Label("BACK",lStyle)),style);
		back.setBounds(Gdx.graphics.getWidth() * 0.90f, Gdx.graphics.getHeight() * 0.90f,  Gdx.graphics.getWidth() * 0.1f,Gdx.graphics.getHeight() * 0.05f);
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameScreen.changeMenuStatus(4);
				
			}
		});
		
		p.addActor(back);
		
		Table inventoryTab = new Table();
		inventoryTab.defaults().height(50);
		if(Gdx.app.getType().equals(ApplicationType.Android)){
			inventoryTab.defaults().width(Gdx.graphics.getWidth() * 0.17f);
		}else{
			inventoryTab.defaults().width(Gdx.graphics.getWidth() * 0.17f);
		}
		
		
		Label item1 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item1);
		
		Label item1Amount = new Label("0",lStyle);
		inventoryTab.add(item1Amount);
		inventoryTab.center();
		Label item2 = new Label("Espada de la ostia2",lStyle);
		inventoryTab.add(item2);
		inventoryTab.center();
		Label item2Amount = new Label("0",lStyle);
		inventoryTab.add(item2Amount);
		inventoryTab.center();
		Label item3 = new Label("Espada de la ostia3",lStyle);
		inventoryTab.add(item3);
		inventoryTab.center();
		Label item3Amount = new Label("0",lStyle);
		inventoryTab.add(item3Amount);
		inventoryTab.center();
		inventoryTab.row();
		Label item4 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item4);
		inventoryTab.center();
		Label item4Amount = new Label("0",lStyle);
		inventoryTab.add(item4Amount);
		inventoryTab.center();
		Label item5 = new Label("item2",lStyle);
		inventoryTab.add(item5);
		inventoryTab.center();
		Label item5Amount = new Label("0",lStyle);
		inventoryTab.add(item5Amount);
		inventoryTab.center();
		Label item6 = new Label("item3",lStyle);
		inventoryTab.add(item6);
		inventoryTab.center();
		Label item6Amount = new Label("0",lStyle);
		inventoryTab.add(item6Amount);
		inventoryTab.center();
		inventoryTab.row();
		Label item7 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item7);
		inventoryTab.center();
		Label item7Amount = new Label("0",lStyle);
		inventoryTab.add(item7Amount);
		inventoryTab.center();
		Label item8 = new Label("item2",lStyle);
		inventoryTab.add(item8);
		inventoryTab.center();
		Label item8Amount = new Label("0",lStyle);
		inventoryTab.add(item8Amount);
		inventoryTab.center();
		Label item9 = new Label("item3",lStyle);
		inventoryTab.add(item9);
		inventoryTab.center();
		Label item9Amount = new Label("0",lStyle);
		inventoryTab.add(item9Amount);
		inventoryTab.center();
		inventoryTab.row();
		Label item10 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item10);
		inventoryTab.center();
		Label item11Amount = new Label("0",lStyle);
		inventoryTab.add(item11Amount);
		inventoryTab.center();
		Label item12 = new Label("Espada de la ostia2",lStyle);
		inventoryTab.add(item12);
		inventoryTab.center();
		Label item12Amount = new Label("0",lStyle);
		inventoryTab.add(item12Amount);
		inventoryTab.center();
		Label item13 = new Label("Espada de la ostia3",lStyle);
		inventoryTab.add(item13);
		inventoryTab.center();
		Label item13Amount = new Label("0",lStyle);
		inventoryTab.add(item13Amount);
		inventoryTab.center();
		inventoryTab.row();
		Label item14 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item14);
		inventoryTab.center();
		Label item14Amount = new Label("0",lStyle);
		inventoryTab.add(item14Amount);
		inventoryTab.center();
		Label item15 = new Label("item2",lStyle);
		inventoryTab.add(item15);
		inventoryTab.center();
		Label item15Amount = new Label("0",lStyle);
		inventoryTab.add(item15Amount);
		inventoryTab.center();
		Label item16 = new Label("item3",lStyle);
		inventoryTab.add(item16);
		inventoryTab.center();
		Label item16Amount = new Label("0",lStyle);
		inventoryTab.add(item16Amount);
		inventoryTab.center();
		inventoryTab.row();
		Label item17 = new Label("Espada de la ostia",lStyle);
		inventoryTab.add(item17);
		inventoryTab.center();
		Label item17Amount = new Label("0",lStyle);
		inventoryTab.add(item17Amount);
		inventoryTab.center();
		Label item18 = new Label("item2",lStyle);
		inventoryTab.add(item18);
		inventoryTab.center();
		Label item18Amount = new Label("0",lStyle);
		inventoryTab.add(item18Amount);
		inventoryTab.center();
		Label item19 = new Label("item3",lStyle);
		inventoryTab.add(item19);
		inventoryTab.center();
		Label item19Amount = new Label("0",lStyle);
		inventoryTab.add(item19Amount);
		inventoryTab.center();
		inventoryTab.row();
		
		ScrollPane inventory = new ScrollPane(inventoryTab);
		inventory.setBounds(Gdx.graphics.getWidth() * 0.02f, Gdx.graphics.getHeight() * 0.25f, Gdx.graphics.getWidth()*1.1f, Gdx.graphics.getHeight()*0.22f);
		inventory.setScrollingDisabled(true, false);
		p.addActor(inventory);

		
		
		return p;
	}
}