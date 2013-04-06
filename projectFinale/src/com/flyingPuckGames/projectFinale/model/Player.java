package com.flyingPuckGames.projectFinale.model;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Player extends Entity {
	private EntityType entityType = EntityType.PlayerEntity;

	private float stateTime;
	public boolean facesRight = true;
	private boolean grounded = false;
	private static final float MAX_VELOCITY = 10f;
	private static final float JUMP_VELOCITY = 40f;
	private static final float GRAVITY = -2.5f;
	private static final float DAMPING = 0.87f;
	private static OrthogonalTiledMapRenderer tRenderer;
	private static float WIDTH;
	private static float HEIGHT;
	private Pool<Rectangle> rectPool;
	private Array<Rectangle> tiles;
	private TiledMap tiledMap;
	private Rectangle charRect;

	enum State {
		Standing, Walking, Crouching, Jumping
	}

	public State state = State.Walking;

	public Player(OrthogonalTiledMapRenderer tRenderer,
			Pool<Rectangle> rectPool, Array<Rectangle> tiles, TiledMap tiledMap) {

		texture = new Texture(Gdx.files.internal("data/plahCharacter.png"));
		WIDTH = .999f / 16f * texture.getWidth();
		HEIGHT = .999f * 2 / 16f * texture.getHeight();
		Player.tRenderer = tRenderer;
		this.position.set(2, 3);
		this.rectPool = rectPool;
		this.tiles = tiles;
		this.tiledMap = tiledMap;
	}

	public void updatePlayer(float delta) {

		this.stateTime += delta;

		if ((Gdx.input.isKeyPressed(Keys.DOWN))) {
			HEIGHT = .999f / 16f * texture.getHeight();
		} else {
			HEIGHT = .999f * 2 / 16f * texture.getHeight();
		}

		if ((Gdx.input.isKeyPressed(Keys.SPACE) & this.grounded)
				|| (Gdx.input.isKeyPressed(Keys.UP) & this.grounded)) {
			this.velocity.y += Player.JUMP_VELOCITY;
			state = State.Jumping;
			this.grounded = false;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			this.velocity.x = -Player.MAX_VELOCITY;
			if (this.grounded)
				this.state = State.Walking;
			this.facesRight = false;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)
				|| Gdx.input.isKeyPressed(Keys.D)) {
			this.velocity.x = Player.MAX_VELOCITY;
			if (this.grounded)
				this.state = State.Walking;
			this.facesRight = true;
		}

		this.velocity.add(0, GRAVITY);

		// clamp the velocity to the maximum, x-axis only
		if (Math.abs(this.velocity.x) > Player.MAX_VELOCITY) {
			this.velocity.x = Math.signum(this.velocity.x)
					* Player.MAX_VELOCITY;
		}

		// clamp the velocity to 0 if it's < 1, and set the state to standing
		if (Math.abs(this.velocity.x) < 1) {
			this.velocity.x = 0;
			if (this.grounded)
				this.state = Player.State.Standing;
		}
		// multiply by delta time so we know how far we go
		// in this frame
		this.velocity.mul(delta);

		charRect = rectPool.obtain();
		charRect.set(this.position.x, this.position.y, WIDTH-0.1f,
				HEIGHT-0.1f);
		int startX, startY, endX, endY;
		if (this.velocity.x > 0) {
			startX = endX = (int) (this.position.x + Player.WIDTH + this.velocity.x);
		} else {
			startX = endX = (int) (this.position.x + this.velocity.x);
		}
		startY = (int) (this.position.y);
		endY = (int) (this.position.y + Player.HEIGHT);
		getTiles(startX, startY, endX, endY, tiles);
		charRect.x += this.velocity.x;
		for (Rectangle tile : tiles) {
			if (Intersector.overlapRectangles(charRect, tile)) {
				this.velocity.x = 0;
				System.out.println("[Colision detected in the X axis: "
						+ this.position + "]");
				break;
			}
		}
		charRect.x = this.position.x;

		// if the character is moving upwards, check the tiles to the top of it's
		// top bounding box edge, otherwise check the ones to the bottom
		if (this.velocity.y > 0) {
			startY = endY = (int) (this.position.y + Player.HEIGHT + this.velocity.y);
		} else {
			startY = endY = (int) (this.position.y + this.velocity.y);
		}
		startX = (int) (this.position.x);
		endX = (int) (this.position.x + Player.WIDTH);
		getTiles(startX, startY, endX, endY, tiles);
		charRect.y += this.velocity.y;
		for (Rectangle tile : tiles) {
			if (Intersector.overlapRectangles(this.charRect, tile)) {
				// we actually reset the character y-position here
				// so it is just below/above the tile we collided with
				// this removes bouncing :)
				if (this.velocity.y > 0) {
					this.position.y = tile.y - Player.HEIGHT;
				} else {
					this.position.y = tile.y + tile.height;
					// if we hit the ground, mark us as grounded so we can jump
					this.grounded = true;
				}
				this.velocity.y = 0;
				break;
			}
		}
		rectPool.free(charRect);
		// unscale the velocity by the inverse delta time and set
		// the latest position
		this.position.add(this.velocity);
		this.velocity.mul(1 / delta);

		// Apply damping to the velocity on the x-axis so we don't
		// walk infinitely once a key was pressed
		this.velocity.x *= Player.DAMPING;
		// System.out.println(position.x + " " + position.y );
	}

	public void renderCharacter(float deltaTime) {

		// draw the character, depending on the current velocity
		// on the x-axis, draw the character facing either right
		// or left
		SpriteBatch batch = tRenderer.getSpriteBatch();
		batch.begin();
		if (this.facesRight) {
			batch.draw(texture, this.position.x, this.position.y, WIDTH, HEIGHT);
		} else {
			batch.draw(texture, this.position.x + WIDTH, this.position.y,
					-WIDTH, HEIGHT);
		}
		batch.end();
	}

	private void getTiles(int startX, int startY, int endX, int endY,
			Array<Rectangle> tiles) {
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers()
				.getLayer(0);
		rectPool.freeAll(tiles);
		tiles.clear();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				Cell cell = layer.getCell(x, y);

				if (cell != null) {
					if (cell.getTile().getProperties().containsKey("solid")) {
						// System.out.println(cell.getTile().getProperties().get("solid").toString());
					}
					Rectangle rect = rectPool.obtain();
					rect.set(x, y, 1, 1);
					tiles.add(rect);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "plahCharacter [entityType=" + entityType + ", facesRight="
				+ facesRight + ", grounded=" + grounded + ", state=" + state
				+ ", entityState=" + entityState + ", position=" + position
				+ ", velocity=" + velocity + "]";
	}
}
