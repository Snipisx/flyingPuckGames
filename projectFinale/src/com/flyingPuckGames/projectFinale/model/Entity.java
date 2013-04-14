package com.flyingPuckGames.projectFinale.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	protected enum EntityType
	{
		GenericEntity,PlayerEntity,EnemyEntity,ProjectileEntity
	};
	
	protected EntityType 	entityType = EntityType.GenericEntity;
	protected Vector2 		position = new Vector2();
	protected Vector2 		velocity = new Vector2();
	protected float			stateTime;

}