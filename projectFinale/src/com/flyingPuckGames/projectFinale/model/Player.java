package com.flyingPuckGames.projectFinale.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.flyingPuckGames.projectFinale.utils.Constants;

public class Player extends Entity {
	
	public enum State {
		IDLE, WALKING, JUMPING
	}
	
	private final float WSIZE = Constants.PLAYER_WIDTH_IN_UNITS;
	private final float HSIZE = Constants.PLAYER_HEIGHT_IN_UNITS;
	
	private Rectangle bounds 		= new Rectangle();
	private State	state 			= State.IDLE;
	private boolean	facesRight 		= true;
	private boolean grounded		= false;
	private boolean	longJump 		= false;
	private Texture texture;
	 
	
	public Player(Vector2 position){
		this.position = position;
		this.bounds.x = position.x;
		this.bounds.y = position.y;
		this.bounds.width = WSIZE;
		this.bounds.height = HSIZE;
		this.entityType = EntityType.PlayerEntity;
		texture = new Texture(Gdx.files.internal(Constants.TEST_PLAYER_TEXTURE_PATH));
	}
	
	public void update(float delta) {
		stateTime += delta;
	}

	
	//Positions related getters & setters ---------------/
	
	//Position
	public Vector2 getPosition() {
		return position;
	}
	public float getXPosition() {
		return position.x;
	}
	public float getYPosition() {
		return position.y;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public void setXPosition(float x){
		this.position.x = x;
	}
	public void setYPosition(float y){
		this.position.y = y;
	}
	
	//Velocity
	public Vector2 getVelocity() {
		return velocity;
	}
	public float getXVelocity() {
		return velocity.x;
	}
	public float getYVelocity() {
		return velocity.y;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public void setXVelocity(float x){
		this.velocity.x = x;
	}
	public void setYVelocity(float y){
		this.velocity.y = y;
	}
	
	//Bounds
	public Rectangle getBounds() {
		return bounds;
	}
	public Vector2 getBoundsPosition() {
		return new Vector2(bounds.x, bounds.y);
	}
	public float getBoundsXPosition() {
		return bounds.getX();
	}
	public float getBoundsYPosition() {
		return bounds.getY();
	}
	public float getBoundsWidth() {
		return bounds.getWidth();
	}
	public float getBoundsHeight() {
		return bounds.getHeight();
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public void setBounds(float x, float y, float width, float height) {
		this.bounds.set(x, y, width, height);
	}
	public void setBoundsPosition(Vector2 boundsPosition) {
		this.bounds.setPosition(boundsPosition);
	}
	public void setBoundsXPosition(float x){
		this.bounds.setX(x);
	}
	public void setBoundsYPosition(float y){
		this.bounds.setY(y);
	}
	public void setBoundsWidth(float width){
		this.bounds.setWidth(width);
	}
	public void setBoundsHeight(float height){
		this.bounds.setHeight(height);
	}
	
	//Getters & Setters Entity --------------------------/
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
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

	public boolean isGrounded() {
		return grounded;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
}
