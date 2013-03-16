package com.flyingPuckGames.projectFinale.entity;

import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class plahCharacter extends Entity {
	private EntityType entityType = EntityType.PlayerEntity;

	private float stateTime;
	private boolean facesRight = true;
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

	enum State {
		Standing, Walking, Crouching, Jumping
	}

	private State state = State.Walking;

	public plahCharacter(OrthogonalTiledMapRenderer tRenderer,
			Pool<Rectangle> rectPool, Array<Rectangle> tiles, TiledMap tiledMap) {

		texture = new Texture(Gdx.files.internal("data/plahCharacter.png"));
		WIDTH = 1 / 16f * texture.getWidth();
		HEIGHT = 1 / 16f * texture.getHeight();
		plahCharacter.tRenderer = tRenderer;
		this.position.set(2, 3);
		this.rectPool = rectPool;
		this.tiles = tiles;
		this.tiledMap = tiledMap;
	}

	public void updatePlayer(float delta) {
		this.stateTime += delta;

		if (Gdx.input.isKeyPressed(Keys.SPACE) & this.grounded) {
			this.velocity.y += plahCharacter.JUMP_VELOCITY;
			state = State.Jumping;
			this.grounded = false;
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			this.velocity.x = -plahCharacter.MAX_VELOCITY;
			if (this.grounded)
				this.state = State.Walking;
			this.facesRight = false;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)
				|| Gdx.input.isKeyPressed(Keys.D)) {
			this.velocity.x = plahCharacter.MAX_VELOCITY;
			if (this.grounded)
				this.state = State.Walking;
			this.facesRight = true;
		}

		this.velocity.add(0, GRAVITY);

		// clamp the velocity to the maximum, x-axis only
		if (Math.abs(this.velocity.x) > plahCharacter.MAX_VELOCITY) {
			this.velocity.x = Math.signum(this.velocity.x)
					* plahCharacter.MAX_VELOCITY;
		}

		// clamp the velocity to 0 if it's < 1, and set the state to standing
		if (Math.abs(this.velocity.x) < 1) {
			this.velocity.x = 0;
			if (this.grounded)
				this.state = plahCharacter.State.Standing;
		}
		// multiply by delta time so we know how far we go
		// in this frame
		this.velocity.mul(delta);

		Rectangle charRect = rectPool.obtain();
		charRect.set(this.position.x, this.position.y, plahCharacter.WIDTH,
				plahCharacter.HEIGHT);
		int startX, startY, endX, endY;
		if (this.velocity.x > 0) {
			startX = (int) (this.position.x + plahCharacter.WIDTH);
			endX = (int) (this.position.x + plahCharacter.WIDTH + this.velocity.x);
		} else {
			startX = endX = (int) (this.position.x + this.velocity.x);
		}
		startY = (int) (this.position.y);
		endY = (int) (this.position.y + plahCharacter.HEIGHT);
		getTiles(startX, startY, endX, endY, tiles);
		charRect.x += this.velocity.x;
		for (Rectangle tile : tiles) {
			if (charRect.overlaps(tile)) {
				this.velocity.x = 0;
				break;
			}
		}
		charRect.x = this.position.x;

		// if the character is moving upwards, check the tiles to the top of
		// it's
		// top bounding box edge, otherwise check the ones to the bottom
		if (this.velocity.y > 0) {
			startY = endY = (int) (this.position.y + plahCharacter.HEIGHT + this.velocity.y);
		} else {
			startY = endY = (int) (this.position.y + this.velocity.y);
		}
		startX = (int) (this.position.x);
		endX = (int) (this.position.x + plahCharacter.WIDTH);
		getTiles(startX, startY, endX, endY, tiles);
		charRect.y += this.velocity.y;
		for (Rectangle tile : tiles) {
			if (charRect.overlaps(tile)) {
				// we actually reset the character y-position here
				// so it is just below/above the tile we collided with
				// this removes bouncing :)
				System.out.println("Height" + this.velocity.y);
				if (this.velocity.y > 0) {
					System.out.println("Height2");
					this.position.y = tile.y - plahCharacter.HEIGHT;
					// we hit a block jumping upwards, let's destroy it!
					TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap
							.getLayers().getLayer(0);
					layer.setCell((int) tile.x, (int) tile.y, null);
				} else {
					System.out.println("Height3");
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
		this.velocity.x *= plahCharacter.DAMPING;
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
			batch.draw(texture, this.position.x + WIDTH,
					this.position.y, -WIDTH, HEIGHT);
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
