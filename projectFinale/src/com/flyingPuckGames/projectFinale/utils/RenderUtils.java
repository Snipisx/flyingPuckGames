package com.flyingPuckGames.projectFinale.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderUtils {
	
	/**
	 * This method clears the screen.
	 */
	public static void clearScreen() {
		
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}
	
	/**
	 * Retrieves a local Asset.
	 * @param path
	 * @return
	 */
	public static TextureRegion localAsset(String path){
		return new TextureRegion(new Texture (Gdx.files.internal(path)));
	}
	
	/**
	 * Retrieves a external Asset.
	 * @param path
	 * @return
	 */
	public static TextureRegion externalAsset(String path){
		return new TextureRegion(new Texture (Gdx.files.external(path)));
	}

}
