package com.flyingPuckGames.projectFinale.model.enemy;

import java.util.ArrayList;
import java.util.List;

import com.flyingPuckGames.projectFinale.utils.JSONParser;

public class Grimoire {

	
	private List<Enemy> enemyList = new ArrayList<Enemy>();
	
	public Grimoire(){
		
//		for(int i = 1; i < 10; i++){
//			enemyList.add(new Enemy(i));
//		}
//		
//		
//		JSONParser g = new JSONParser();
//		
//		
//		g.saveGrimoire(this);
		
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
}
