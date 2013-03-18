package com.flyingPuckGames.projectFinale.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	public enum EntityType {
		GenericEntity,PlayerEntity,EnemyEntity,ProjectileEntity
	};
	protected EntityType entityType = EntityType.GenericEntity;
	
	protected int nextId = 0;
	protected static Random random = new Random();
	protected int id;
	protected EntityType entityState = EntityType.GenericEntity;
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2();
	public static Texture texture;
	

	
		
}
