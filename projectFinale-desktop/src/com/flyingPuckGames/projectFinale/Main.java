package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "projectFinale - " + MegaGame.VERSION ;
		cfg.useGL20 = true;
		cfg.resizable = false;
		cfg.width = 1280;
		cfg.height = 720;
		
		new LwjglApplication(new MegaGame(), cfg);
	}
}
