package com.flyingPuckGames.projectFinale.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.flyingPuckGames.projectFinale.model.Player;
import com.flyingPuckGames.projectFinale.model.Player.State;
import com.flyingPuckGames.projectFinale.model.SolidTile;
import com.flyingPuckGames.projectFinale.model.World;

public class PlayerController implements InputProcessor {
	
	enum Keysa {
		LEFT, RIGHT, JUMP, FIRE
	}
	
	
	private static final long LONG_JUMP_PRESS 	= 150l;
	private static final float ACCELERATION 	= 5f;
	private static final float GRAVITY 			= -20f;
	private static final float MAX_JUMP_SPEED	= 7f;
	private static final float DAMP 			= 0.90f;
	private static final float MAX_VEL 			= 4f;
	
	
	private World 	world;
	private Player 	player;
	private long	jumpPressedTime;
	private boolean jumpingPressed;
	private boolean grounded = false;
	public InputMultiplexer inputSystem;
	
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	
	static Map<Keysa, Boolean> keys = new HashMap<Keysa, Boolean>();
	static {
		keys.put(Keysa.LEFT, false);
		keys.put(Keysa.RIGHT, false);
		keys.put(Keysa.JUMP, false);
		keys.put(Keysa.FIRE, false);
	};
	
	
	private Array<SolidTile> collidable = new Array<SolidTile>();

	public PlayerController(World world) {
		this.world = world;
		this.player = world.getPlayer();
	}
	
	public void leftPressed() {
		keys.get(keys.put(Keysa.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keysa.RIGHT, true));
	}

	public void jumpPressed() {
		keys.get(keys.put(Keysa.JUMP, true));
	}

	public void firePressed() {
		keys.get(keys.put(Keysa.FIRE, false));
	}

	public void leftReleased() {
		keys.get(keys.put(Keysa.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keysa.RIGHT, false));
	}

	public void jumpReleased() {
		keys.get(keys.put(Keysa.JUMP, false));
		jumpingPressed = false;
	}

	public void fireReleased() {
		keys.get(keys.put(Keysa.FIRE, false));
	}

	
	public void update(float delta) {
		processInput();
		
		if (grounded && player.getState().equals(State.JUMPING)) {
			player.setState(State.IDLE);
		}
		
		player.getAcceleration().y = GRAVITY;
		
		player.getAcceleration().mul(delta);
		
		player.getVelocity().add(player.getAcceleration().x, player.getAcceleration().y);
		
		checkCollisionWithBlocks(delta);
		
		player.getVelocity().x *= DAMP;
		
		if (player.getVelocity().x > MAX_VEL) {
			player.getVelocity().x = MAX_VEL;
		}
		if (player.getVelocity().x < -MAX_VEL) {
			player.getVelocity().x = -MAX_VEL;
		}
		player.update(delta);
	}

	private void checkCollisionWithBlocks(float delta) {
		player.getVelocity().mul(delta);
		Rectangle playerRect = rectPool.obtain();
		playerRect.set(player.getBounds().x, player.getBounds().y, player.getBounds().width, player.getBounds().height);
		int startX, endX;
		int startY = (int) player.getBounds().y;
		int endY = (int) (player.getBounds().y + player.getBounds().height);
		if (player.getVelocity().x < 0) {
			startX = endX = (int) Math.floor(player.getBounds().x + player.getVelocity().x);
		} else {
			startX = endX = (int) Math.floor(player.getBounds().x + player.getBounds().width + player.getVelocity().x);
		}
		populateCollidableBlocks(startX, startY, endX, endY);
		playerRect.x += player.getVelocity().x;
		world.getCollisionRects().clear();
		for (SolidTile tile : collidable) {
			if (tile == null) continue;
			if (playerRect.overlaps(tile.getBounds())) {
				player.getVelocity().x = 0;
				world.getCollisionRects().add(tile.getBounds());
				break;
			}
		}
		playerRect.x = player.getPosition().x;
		startX = (int) player.getBounds().x;
		endX = (int) (player.getBounds().x + player.getBounds().width);
		if (player.getVelocity().y < 0) {
			startY = endY = (int) Math.floor(player.getBounds().y + player.getVelocity().y);
		} else {
			startY = endY = (int) Math.floor(player.getBounds().y + player.getBounds().height + player.getVelocity().y);
		}
		
		populateCollidableBlocks(startX, startY, endX, endY);
		playerRect.y += player.getVelocity().y;
		for (SolidTile tile : collidable) {
			if (tile == null) continue;
			if (playerRect.overlaps(tile.getBounds())) {
				if (player.getVelocity().y < 0) {
					grounded = true;
				}
				player.getVelocity().y = 0;
				world.getCollisionRects().add(tile.getBounds());
				break;
			}
		}
		playerRect.y = player.getPosition().y;
		player.getPosition().add(player.getVelocity());
		player.getBounds().x = player.getPosition().x;
		player.getBounds().y = player.getPosition().y;
		player.getVelocity().mul(1 / delta);
	}

	private void populateCollidableBlocks(int startX, int startY, int endX, int endY) {
		collidable.clear();
		for (int x = startX; x <= endX; x++) {
			for (int y = startY; y <= endY; y++) {
				if (x >= 0 && x < world.getLevel().getWidth() && y >=0 && y < world.getLevel().getHeight()) {
					collidable.add(world.getLevel().get(x, y));
					System.out.println(world.getLevel().get(x,y));
				}
			}
		}
	}
	
	private boolean processInput() {
		if (keys.get(Keysa.JUMP)) {
			if (!player.getState().equals(State.JUMPING)) {
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				player.setState(State.JUMPING);
				player.getVelocity().y = MAX_JUMP_SPEED; 
				grounded = false;
			} else {
				if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
					jumpingPressed = false;
				} else {
					if (jumpingPressed) {
						player.getVelocity().y = MAX_JUMP_SPEED;
					}
				}
			}
		}
		if (keys.get(Keysa.LEFT)) {
			// left is pressed
			player.setFacesRight(false);
			if (!player.getState().equals(State.JUMPING)) {
				player.setState(State.WALKING);
			}
			player.getAcceleration().x = -ACCELERATION;
		} else if (keys.get(Keysa.RIGHT)) {
			// left is pressed
			player.setFacesRight(true);
			if (!player.getState().equals(State.JUMPING)) {
				player.setState(State.WALKING);
			}
			player.getAcceleration().x = ACCELERATION;
		} else {
			if (!player.getState().equals(State.JUMPING)) {
				player.setState(State.IDLE);
			}
			player.getAcceleration().x = 0;

		}
		return false;
	}


	 public void setInputSystems(InputProcessor... processors) {
		  inputSystem = new InputMultiplexer(processors);
		  Gdx.input.setInputProcessor(inputSystem);
	 }

 
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			leftPressed();
		if (keycode == Keys.RIGHT)
			rightPressed();
		if (keycode == Keys.Z)
			jumpPressed();
		if (keycode == Keys.X)
			firePressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			leftReleased();
		if (keycode == Keys.RIGHT)
			rightReleased();
		if (keycode == Keys.Z)
			jumpReleased();
		if (keycode == Keys.X)
			fireReleased();
		if (keycode == Keys.D)
			System.out.println("debug");
			//renderDebugText();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
//		if (!Gdx.app.getType().equals(ApplicationType.Android))
//			return false;
//		if (x < this.W / 2 && y > this.H / 2) {
//			.leftPressed();
//		}
//		if (x > this.W / 2 && y > this.H / 2) {
//			.rightPressed();
//		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
//		if (!Gdx.app.getType().equals(ApplicationType.Android))
//			return false;
//		if (x < this.W / 2 && y > this.H / 2) {
//			.leftReleased();
//		}
//		if (x > this.W / 2 && y > this.H / 2) {
//			.rightReleased();
//		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
