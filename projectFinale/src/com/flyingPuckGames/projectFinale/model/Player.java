package com.flyingPuckGames.projectFinale.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {
	
	public enum State {
		IDLE, WALKING, JUMPING
	}
	
	private final float HSIZE = 2f;
	private final float WSIZE = 1f;
	
	private Vector2		acceleration = new Vector2();
	private Rectangle	bounds = new Rectangle();
	private State		state = State.IDLE;
	private boolean		facesRight = true;
	private boolean		longJump = false;
	private Texture 	texture;

	
	public Player(Vector2 position){
		this.position = position;
		this.bounds.x = position.x;
		this.bounds.y = position.y;
		this.bounds.height = HSIZE;
		this.bounds.width = WSIZE;
		this.entityType = EntityType.PlayerEntity;
	}
	
	public void update(float delta) {
		stateTime += delta;
	}

	//Getters & Setters Entity --------------------------/
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
		this.bounds.setX(position.x);
		this.bounds.setY(position.y);
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public float getStateTime() {
		return stateTime;
	}
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	
	//Getters & Setters Player --------------------------/
	public Vector2 getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public State getState() {
		return state;
	}
	public void setState(State newState) {
		this.state = newState;
	}
	public boolean isFacesRight() {
		return facesRight;
	}
	public void setFacesRight(boolean facesRight) {
		this.facesRight = facesRight;
	}
	public boolean isLongJump() {
		return longJump;
	}
	public void setLongJump(boolean longJump) {
		this.longJump = longJump;
	}
	public float getHsize() {
		return HSIZE;
	}
	public float getWsize() {
		return WSIZE;
	}
}
