package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class RenderUtils {
	
	/**
	 * This method clears the screen.
	 */
	public static void clearScreen() {
		Gdx.app.getType();
		if(Gdx.app.getType() == ApplicationType.Android){
			Gdx.graphics.getGL10().glClearColor(0, 0, 0, 0);
			Gdx.graphics.getGL10().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		}else{
			Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
			Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		}
		
		//System.out.println("HolaClear");
	}

}
