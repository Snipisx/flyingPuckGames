package com.flyingPuckGames.projectFinale.model.enemy;



public class Enemy {

	private String name;
	private int level;
	private int hp;
	private int id;
	private String strongAgainst;
	private String inmuneAgainst;
	private String weakAgainst;
	private String absorb;
	private String drop;
	private float exp;
	private String desc;
	
	
	
//	public Enemy(){
////		name = "enemy" + x;
////		level = x;
////		hp = x;
////		id = x;
////		strongAgainst = "strong" + x;
////		inmuneAgainst = "inmune" + x;
////		weakAgainst = "weak" + x;
////		absorb = "absorb" + x;
////		drop = "item" + x;
////		exp = x;
////		desc = "enemyDesc" + x;
//		
//	}



	public Enemy() {
		// TODO Auto-generated constructor stub
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	public int getHp() {
		return hp;
	}



	public void setHp(int hp) {
		this.hp = hp;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getStrongAgainst() {
		return strongAgainst;
	}



	public void setStrongAgainst(String strongAgainst) {
		this.strongAgainst = strongAgainst;
	}



	public String getInmuneAgainst() {
		return inmuneAgainst;
	}



	public void setInmuneAgainst(String inmuneAgainst) {
		this.inmuneAgainst = inmuneAgainst;
	}



	public String getWeakAgainst() {
		return weakAgainst;
	}



	public void setWeakAgainst(String weakAgainst) {
		this.weakAgainst = weakAgainst;
	}



	public String getAbsorb() {
		return absorb;
	}



	public void setAbsorb(String absorb) {
		this.absorb = absorb;
	}



	public String getDrop() {
		return drop;
	}



	public void setDrop(String drop) {
		this.drop = drop;
	}



	public float getExp() {
		return exp;
	}



	public void setExp(float exp) {
		this.exp = exp;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
