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
	private JSONParser parser;
	private Item itemSelected;
	private Item itemToChange;
	private PlayerStatus playerStatus;
	
	
	public InventoryController(BitmapFont font,EquipMenu equipMenu){
		this.equipMenu = equipMenu;
		setStyles(font);
		
	}
	
	private void setStyles(BitmapFont font) {
		
		normalLabelStyle.font = font;
		selectedLabelStyle.font = font;
		changeLabelStyle.font = font;
		selectedLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selected2.png"))));
		changeLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textures/selectBox1.png"))));
		
	}
	

	public void equipedItemSelected(Integer itemId){
		
		itemSelected = playerStatus.getItem(itemId);
		setSelectedDescription(itemSelected);
		showChangeEquiped();
	}
	

	public void noEquipedItemSelected(Integer itemId) {
		if(itemsBloqued){
			return;
		}
		itemSelected = playerStatus.getItem(itemId);
		setSelectedDescription(itemSelected);
		showEquipItem();
		
		
	}
	
	private void showEquipItem() {
		
	}


	private void showChangeEquiped() {
		
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

	public void setPlayer(PlayerStatus playerStatus) {
		this.playerStatus = playerStatus;
	}


	
	
}
