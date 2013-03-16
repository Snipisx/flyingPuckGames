package com.flyingPuckGames.projectFinale;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "projectFinale - " + MegaGame.VERSION ;
		cfg.useGL20 = true;
		cfg.width = 1900;
		cfg.height = 1060;
		
		new LwjglApplication(new MegaGame(), cfg);
	}
}
