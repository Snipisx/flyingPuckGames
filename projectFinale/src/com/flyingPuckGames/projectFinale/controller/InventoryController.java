package com.flyingPuckGames.projectFinale.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.player.Inventory;
import com.flyingPuckGames.projectFinale.model.player.Item;
import com.flyingPuckGames.projectFinale.model.player.ItemWidget;
import com.flyingPuckGames.projectFinale.model.player.PlayerStatus;
import com.flyingPuckGames.projectFinale.stage.EquipMenu;
import com.flyingPuckGames.projectFinale.utils.JSONParser;

public class InventoryController {
	
	private MegaGame megaGame;
	private EquipMenu equipMenu;
	private Integer selectedItem;
	private LabelStyle normalLabelStyle = new LabelStyle();
	private LabelStyle selectedLabelStyle = new LabelStyle();
	private LabelStyle changeLabelStyle = new LabelStyle();
	private boolean itemsBloqued;
	private ItemWidget itemSelected;
	private ItemWidget itemToChange;
	
	
	
	/**
	 * Create new InvetoryController and initialize the styles.
	 * @param font
	 * @param equipMenu
	 */
	public InventoryController(BitmapFont font,EquipMenu equipMenu){
		this.equipMenu = equipMenu;
		setStyles(font);
		itemsBloqued = true;
		
	}
	
	private void setStyles(BitmapFont font) {
		
		normalLabelStyle.font = font;
		selectedLabelStyle.font = font;
		changeLabelStyle.font = font;
		selectedLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/hover3.png"))));
		changeLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox1.png"))));
		
	}
	

	public void equipedItemSelected(ItemWidget selected){
		if(itemsBloqued){
			itemSelected = selected;
			setSelectedDescription(itemSelected.getItem());
			itemSelected.setNameLabelStyle(selectedLabelStyle);
			showChangeEquiped();
		}
	}
	

	public void noEquipedItemSelected(ItemWidget widget) {
		if(!itemsBloqued){
			
			itemSelected = widget;
			setSelectedDescription(itemSelected.getItem());
			showEquipItem();
		}
		
	}
	
	private void showEquipItem() {
		equipItem();
	}

	public void equipItem(){
		itemsBloqued = true;
		Item adicional = itemSelected.getItem();
		itemToChange.setNameLabelStyle(normalLabelStyle);
		itemSelected.setItem(itemToChange.getItem());
		
		itemToChange.setItem(adicional);
		
	}

	private void showChangeEquiped() {
		changeEquiped();
	}


	public void changeEquiped(){
		itemToChange = itemSelected;
		itemsBloqued = false;
	}
	
	public void setSelectedDescription(Item selected){
		
		equipMenu.setDescription(selected.getDescription());
		
	}


	public LabelStyle getNormalLabelStyle() {
		return normalLabelStyle;
	}


	public void setNormalLabelStyle(LabelStyle normalLabelStyle) {
		this.normalLabelStyle = normalLabelStyle;
	}


	public LabelStyle getSelectedLabelStyle() {
		return selectedLabelStyle;
	}


	public void setSelectedLabelStyle(LabelStyle selectedLabelStyle) {
		this.selectedLabelStyle = selectedLabelStyle;
	}


	public LabelStyle getChangeLabelStyle() {
		return changeLabelStyle;
	}


	public void setChangeLabelStyle(LabelStyle changeLabelStyle) {
		this.changeLabelStyle = changeLabelStyle;
	}

}
