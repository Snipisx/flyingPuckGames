package com.flyingPuckGames.projectFinale.model.player;

import java.util.ArrayList;


public class PlayerStatus {

	//info Player
	private Integer HP = 5;
	private Integer maxHp = 10;
	private Integer MP = 5;
	private Integer maxMp = 10;
	private float expActual = 10;
	private float expNextLvl = 10;
	private Integer level = 1;
	private String status = "NORMAL";
	private float coins = 999;
	private float time = 200;
	
	//Attributes Player
	private Integer str = 1;
	private Integer inte = 1;
	private Integer con = 1;
	private Integer lck = 1;
	private Integer dmg = 2;
	private Integer def = 2;

	
	//inventory
	private ArrayList<Item> inventory;
	
	
	public PlayerStatus(){
		inventory = new ArrayList<Item>();
	}

	public Integer getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}

	public Integer getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(Integer maxMp) {
		this.maxMp = maxMp;
	}

	public Integer getHP() {
		return HP;
	}

	public void setHP(Integer hP) {
		HP = hP;
	}

	public Integer getMP() {
		return MP;
	}

	public void setMP(Integer mP) {
		MP = mP;
	}

	public float getExpActual() {
		return expActual;
	}

	public void setExpActual(float expActual) {
		this.expActual = expActual;
	}

	public float getExpNextLvl() {
		return expNextLvl;
	}

	public void setExpNextLvl(float expNextLvl) {
		this.expNextLvl = expNextLvl;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getCoins() {
		return coins;
	}

	public void setCoins(float coins) {
		this.coins = coins;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public Integer getStr() {
		return str;
	}

	public void setStr(Integer str) {
		this.str = str;
	}

	public Integer getInte() {
		return inte;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}

	public Integer getCon() {
		return con;
	}

	public void setCon(Integer con) {
		this.con = con;
	}

	public Integer getLck() {
		return lck;
	}

	public void setLck(Integer lck) {
		this.lck = lck;
	}

	public Integer getDmg() {
		return dmg;
	}

	public void setDmg(Integer dmg) {
		this.dmg = dmg;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}

	public ArrayList<Item> getItems() {
		return inventory;
	}
	
	public void setItems(ArrayList<Item> items) {
		this.inventory = items;
	}
	
	public Item getItem(int itemId){
		return inventory.get(itemId);
	}
}
