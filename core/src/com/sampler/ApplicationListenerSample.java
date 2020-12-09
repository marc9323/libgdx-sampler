package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

import common.SampleBase;
import common.SampleInfo;

public class ApplicationListenerSample extends SampleBase {

	private static final Logger log = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(ApplicationListenerSample.class);

	private boolean renderInterrupted = true;

	@Override
	public void create() {
		// initialize game and load resources
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		log.debug("create()");
	}

	@Override
	public void resize(int width, int height) {
		// handle setting a new screen size - on desktop for resize and start up
		log.debug("Resize() width= " + width + " height= " + height);
	}

	@Override
	public void render() {
		// render the game elements - called 60 times per second, 60 fps
		if(renderInterrupted){
			log.debug("render()");
			renderInterrupted = false;
		}
	}

	@Override
	public void pause() {
		// used to save game state when focus is lost, back button or mouse out
		// actual gameplay pause is done manually
		log.debug("pause()");
		renderInterrupted = true;
	}

	@Override
	public void resume() {
		// used to handle the game returning from being paused and restores game state
		log.debug("resume()");
		renderInterrupted = true;
	}

	@Override
	public void dispose() {
		// prevent memory leaks - call for garbage collection
		// free resources and clean up our game
		log.debug("dispose()");
	}
}
