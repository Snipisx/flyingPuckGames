package com.flyingPuckGames.projectFinale.stage;

import java.util.ArrayList;

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
import com.flyingPuckGames.projectFinale.controller.InventoryController;
import com.flyingPuckGames.projectFinale.controller.MenuController;
import com.flyingPuckGames.projectFinale.model.player.Item;
import com.flyingPuckGames.projectFinale.model.player.ItemWidget;
import com.flyingPuckGames.projectFinale.utils.JSONParser;

public class EquipMenu {

	private ButtonStyle style;
	private LabelStyle lStyle;
	private float WIDTH;
	private float HEIGHT;
	private Table inventoryTab;
	private Table equipedTab;
	private JSONParser parser;
	private InventoryController inventoryController;
	private ArrayList<Item> itemList;
	private Label description;
	
	public EquipMenu(ButtonStyle button, LabelStyle label){
		style = button;
		lStyle = label;
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		parser = new JSONParser();
		inventoryController = new InventoryController(label.font,this);
	}
	
	
	/*
	 * Method that create the window
	 */
	
	public void setTables(){
		int cols = 0;
		description = new Label("-------------",lStyle);
		inventoryTab = new Table();
		inventoryTab.defaults().width(WIDTH * 0.36f);
		inventoryTab.defaults().height(HEIGHT * 0.1f);
		ItemWidget itemWidget;
		
		equipedTab = new Table();
		equipedTab.defaults().width( WIDTH * 0.12f );
		equipedTab.defaults().height( HEIGHT * 0.08f );
		
		ItemWidget equipedItems[] = new ItemWidget[5];
		itemList = new ArrayList<Item>();
		
		for(int i = 0; i < 30;i++){
			if(cols == 3){
				inventoryTab.row();
				cols = 0;
			}
			itemWidget = parser.getItem(i).createItemWidger(inventoryController);
			itemWidget.init();
			itemList.add(itemWidget.getItem());
			if(itemWidget.getItem().isEquiped()){
				Item itemEquiped = itemWidget.getItem();
				if(itemEquiped.getType().equals("weapon")){
					System.out.println("weapon");
					equipedItems[0] = itemWidget;
				}else if(itemEquiped.getType().equals("armor")){
					System.out.println("armor");
					equipedItems[1] = itemWidget;
				}else{
					System.out.println("accesory");
					equipedItems[itemEquiped.getAccesoryPos()] = itemWidget;
				}
			}else{
				inventoryTab.add(itemWidget);
				cols++;
			}
		}
		
		for(int j = 0; j < equipedItems.length;j++){
			equipedTab.add(equipedItems[j]);
			equipedTab.row();
		}
		
		
	}
	
	public Group create(final MenuController menuController){
		setTables();
		Group p = new Group();
		menuController.getPlayerStatus().setItems(itemList);
		inventoryController.setPlayer(menuController.getPlayerStatus());
		p.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				System.out.println("holatio");
				if(keycode == Keys.ESCAPE){
					menuController.status(4);
				}
				
				return true;
			}
			
			});
		
		
		Image background = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/equip.png")))));
		background.setColor(0.8f,0.8f,0.8f,0.8f);
		background.setBounds(0, 0, WIDTH,HEIGHT);

		
		
		p.addActor(background);
		
		
		equipedTab.setBounds(WIDTH * 0.45f, HEIGHT * 0.5f, WIDTH * 0.2f,HEIGHT * 0.5f );
		
		p.addActor(equipedTab);
		
		
		Button back = new Button((new Label("BACK",lStyle)),style);
		back.setBounds(WIDTH * 0.90f, HEIGHT * 0.90f,  WIDTH * 0.1f,HEIGHT * 0.05f);
		back.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				menuController.status(4);
				
			}
		});
		
		p.addActor(back);
		
		
		
		
		
//		Label item1 = new Label("Espada de la ostia",lStyle);
//		inventoryTab.add(item1);
		
		
		ScrollPane inventory = new ScrollPane(inventoryTab);
		inventory.setBounds(WIDTH * 0.02f, HEIGHT * 0.25f, WIDTH*1.1f, HEIGHT*0.22f);
		inventory.setScrollingDisabled(true, false);
		p.addActor(inventory);
		
		
		description.setBounds(WIDTH * 0.2f, HEIGHT * 0.07f, WIDTH*0.9f, HEIGHT*0.05f);
		p.addActor(description);
		return p;
	}

	public void setResolution(float width, float height) {
		WIDTH = width;
		HEIGHT = height;
	}


	public void setDescription(String description) {
		this.description.setText(description);
	}
}
