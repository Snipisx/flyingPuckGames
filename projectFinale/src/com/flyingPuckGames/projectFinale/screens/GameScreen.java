package com.flyingPuckGames.projectFinale.screens;


import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.flyingPuckGames.projectFinale.MegaGame;
import com.flyingPuckGames.projectFinale.model.World;
import com.flyingPuckGames.projectFinale.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor {

	private World 			world;
	private WorldRenderer 	renderer;
	private MegaGame		megaGame;
//	private PlayerController	controller;
	
	private int W, H;
	
	public GameScreen(MegaGame megaGame) {
		this.megaGame = megaGame;
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world, false, this.megaGame);
//		controller = new BobController(world);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

//		controller.update(delta);
		renderer.render(delta);
	}
	
	@Override
	public void resize(int W, int H) {
		renderer.setSize(W, H);
		this.W = W;
		this.H = H;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	// * InputProcessor methods ***************************//

//	@Override
//	public boolean keyDown(int keycode) {
//		if (keycode == Keys.LEFT)
//			controller.leftPressed();
//		if (keycode == Keys.RIGHT)
//			controller.rightPressed();
//		if (keycode == Keys.Z)
//			controller.jumpPressed();
//		if (keycode == Keys.X)
//			controller.firePressed();
//		return true;
//	}

//	@Override
//	public boolean keyUp(int keycode) {
//		if (keycode == Keys.LEFT)
//			controller.leftReleased();
//		if (keycode == Keys.RIGHT)
//			controller.rightReleased();
//		if (keycode == Keys.Z)
//			controller.jumpReleased();
//		if (keycode == Keys.X)
//			controller.fireReleased();
//		if (keycode == Keys.D)
//			renderer.setDebug(!renderer.isDebug());
//		return true;
//	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean touchDown(int x, int y, int pointer, int button) {
//		if (!Gdx.app.getType().equals(ApplicationType.Android))
//			return false;
//		if (x < width / 2 && y > height / 2) {
//			controller.leftPressed();
//		}
//		if (x > width / 2 && y > height / 2) {
//			controller.rightPressed();
//		}
//		return true;
//	}

//	@Override
//	public boolean touchUp(int x, int y, int pointer, int button) {
//		if (!Gdx.app.getType().equals(ApplicationType.Android))
//			return false;
//		if (x < width / 2 && y > height / 2) {
//			controller.leftReleased();
//		}
//		if (x > width / 2 && y > height / 2) {
//			controller.rightReleased();
//		}
//		return true;
//	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean touchMoved(int x, int y) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
