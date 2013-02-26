package com.flyingPuckGames.projectFinale.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class plahCharacter {
	// plahChar
	private static final float plahRectangleWidth = 16;
	private static final float plahRectangleHeight = 16;
	private static float plahRectangleX;
	private static float plahRectangleY;
	private static Texture plahCharText;
	private static Rectangle plahCharRect;

	public plahCharacter() {
		plahCharRect = new Rectangle();
		plahCharRect.width = plahRectangleWidth;
		plahCharRect.height = plahRectangleHeight;
		plahCharRect.x = 16 + 16 / 2;
		plahCharRect.y = 32;
		plahCharText = new Texture(Gdx.files.internal("data/plahCharacter.png"));
	}

	public static float getPlahRectangleX() {
		return plahRectangleX;
	}

	public static void setPlahRectangleX(float plahRectangleX) {
		plahCharacter.plahRectangleX = plahRectangleX;
	}

	public static float getPlahRectangleY() {
		return plahRectangleY;
	}

	public static void setPlahRectangleY(float plahRectangleY) {
		plahCharacter.plahRectangleY = plahRectangleY;
	}

	public Texture getPlahCharText() {
		return plahCharText;
	}

	public static void setPlahCharText(Texture plahCharText) {
		plahCharacter.plahCharText = plahCharText;
	}

	public Rectangle getPlahCharRect() {
		return plahCharRect;
	}

	public static void setPlahCharRect(Rectangle plahCharRect) {
		plahCharacter.plahCharRect = plahCharRect;
	}

	public static float getPlahrectanglewidth() {
		return plahRectangleWidth;
	}

	public static float getPlahrectangleheight() {
		return plahRectangleHeight;
	}

}
