package com.flyingPuckGames.projectFinale.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	public enum EntityType {
		GenericEntity, PlayerEntity, EnemyEntity, ProjectilEntity
	}
	
	protected int nextId = 0;
	protected static Random random = new Random();
	protected int id;
	
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	
	protected static float entityRectangleX;
	protected static float entityRectangleY;
	protected static Texture texture;
	protected static Rectangle bounds;
		
}
