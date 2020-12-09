package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Arrays;

import common.SampleBase;
import common.SampleInfo;

public class GdxReflectionSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxReflectionSample.class);

    private static final Logger log = new Logger(GdxReflectionSample.class.getName(), Logger.DEBUG);

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

       // debugReflection(this.getClass());
        debugReflection(GdxReflectionSample.class);
    }

    @Override
    public void resize(int width, int height) {
        // keep viewport in sync with any screen resize
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

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

        if (leftPressed) {
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

        if (leftPressed) {
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

    private static void debugReflection(Class<?> clazz) {
        Field[] fields = ClassReflection.getDeclaredFields(clazz);
        Method[] methods = ClassReflection.getDeclaredMethods(clazz);

        log.debug("== debug reflection class= " + clazz.getName());

        log.debug("fields-count= " + fields.length);
        for (Field field : fields) {
            log.debug("name= " + field.getName() + " type= " + field.getType());
        }

        log.debug("methods-count= " + methods.length);

        for (Method method : methods) {
            log.debug("name= " + method.getName() + " parameterTypes= " + Arrays.asList(method.getParameterTypes()));
        }

        log.debug("=====");

    }
}
