package com.flyingPuckGames.projectFinale.model.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.flyingPuckGames.projectFinale.controller.InventoryController;

public class ItemWidget extends Group {
	
	private InventoryController inventoryController;
	private Label nameLabel;
	private Label quantityLabel;
	private Item item;
	private ItemWidget widget;
	
	public ItemWidget(InventoryController inventoryController,Item item){
		this.item = item;
		this.inventoryController = inventoryController;
		this.setWidth(Gdx.graphics.getWidth() * 0.33f);
		this.setHeight(Gdx.graphics.getHeight() * 0.1f);
		widget = this;
		
	}

	public void init(){
		nameLabel = new Label("???????",inventoryController.getNormalLabelStyle());
		quantityLabel = new Label("",inventoryController.getNormalLabelStyle());
		addActor(nameLabel);
		addActor(quantityLabel);
		quantityLabel.setX(this.getWidth() * 0.6f);
		quantityLabel.setY(this.getHeight() * 0.7f);
		nameLabel.setY(this.getHeight() * 0.5f);
		setListeners();
		if(item.isAcquired()){
			setInfoItem();
		}
	}
	
	public void setListeners(){
		nameLabel.addListener(new InputListener() {
			
	        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(item.isEquiped()){
					System.out.println(item.getIdItem());
					inventoryController.equipedItemSelected(widget);
				}else{
					inventoryController.noEquipedItemSelected(widget);
				}
	        	return false;
	        }	        
		});
	}
	
	public void setInfoItem(){
		nameLabel.setText(item.getName().toUpperCase());
		quantityLabel.setText(item.getQuantity().toString());
	}
	
	
	public Label getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(String label) {
		nameLabel.setText(label);
	}

	public Label getQuantityLabel() {
		return quantityLabel;
	}

	public void setQuantityLabel(String quantity) {
		this.quantityLabel.setText(quantity);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
		setNameLabel(item.getName().toUpperCase());
		setQuantityLabel(item.getQuantity().toString());
		if(item.isEquiped()){
			item.setEquiped(false);
		}else{
			item.setEquiped(true);
		}
	}
	
	public void setNameLabelStyle(LabelStyle style){
		nameLabel.setStyle(style);
	}

}
