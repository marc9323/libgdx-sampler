package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputPollingSample implements ApplicationListener {

	private static final Logger log = new Logger(InputPollingSample.class.getName(), Logger.DEBUG);
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		log.debug("CREATE");
		camera = new OrthographicCamera();
		viewport = new FitViewport(1080, 720, camera);
		batch = new SpriteBatch();
		// points to working directory assets folder
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

	}

	@Override
	public void resize(int width, int height) {
		// keep viewport in sync with any screen resize
		viewport.update(width, height, true);
	}

	@Override
	public void render() {
		// clear screen
		Gdx.gl.glClearColor(0, 0, 0, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tells batch which camera to use
		// telling batch position, zoom, of camera
		batch.setProjectionMatrix(camera.combined);

		// all drawing must be between
		batch.begin();

		draw();

		batch.end();
	}

	private void draw() {

		// get mouse/touch coordinates
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		// buttons
		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);

		font.draw(batch, "Mouse/Touch:  x=" + mouseX + " y= " + mouseY,
				200f,
				300f);

		if(leftPressed) {
			font.draw(batch, "LEFT PRESSED", 400f, 400f);
		}
		font.draw(batch, rightPressed ? "Right button pressed" : "Right button not pressed",
				20f,
				720 - 80f);

		// keys
		boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
		boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);
		boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.D);
		boolean aPressed = Gdx.input.isKeyPressed(Input.Keys.A);

		font.draw(batch,
				wPressed ? "W pressed" : "W not pressed",
				200f, 200f);

		if(leftPressed){
			System.out.println("LEFT PRESSED");
		}

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}