package com.flyingPuckGames.projectFinale.model.player;

import com.flyingPuckGames.projectFinale.controller.InventoryController;

public class Item {

	private Integer idItem;
	private String name;
	private Integer quantity;
	private String description = "??????";
	private boolean equiped;
	private String type = "object";

	private boolean acquired;
	private int accesoryPos;
	
	public Item(){
		
	}	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public boolean isEquiped() {
		
		return equiped;
	}

	public boolean isAcquired() {
		return acquired;
	}
	
	public void setEquiped(boolean equiped) {
		this.equiped = equiped;
	}

	public void setAcquired(boolean acquired) {
		this.acquired = acquired;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAccesoryPos() {
		return accesoryPos;
	}
	
	public void setAccesoryPos(int pos){
		accesoryPos = pos;
	}

	
}
