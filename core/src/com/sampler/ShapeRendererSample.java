package com.sampler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

public class ShapeRendererSample extends SampleBase {

   public static final SampleInfo SAMPLE_INFO = new SampleInfo(ShapeRendererSample.class);

   // world units - not pixels
    private static final float WORLD_HEIGHT = 40f;
    private static final float WORLD_WIDTH = 20f;

    // camera viewport and batch required to draw
    // but here we use ShapeRenderer as opposed to SpriteBatch
    // which is used to draw textures

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        // set input processor to 'this'
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        // NOTE:  not centering camera (... true)
        viewport.update(width, height);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();
        renderer.setProjectionMatrix(camera.combined);
        renderer.setAutoShapeType(true);

        // renderer.begin();

        if(drawGrid) {
            drawGrid();
        }

      //  renderer.end();
    }

    private void drawGrid() {
        // grid made of world units
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.setColor(Color.WHITE);

        int worldWidth = (int)WORLD_WIDTH;
        int worldHeight = (int)WORLD_HEIGHT;

        // each rectangle is a single world unit
        for(int x = -worldWidth; x < worldHeight; x++) {
            renderer.line(x, -worldHeight, x, worldHeight);
        }

        for(int y = -worldHeight; y < worldHeight; y++) {
            renderer.line(-worldWidth, y, worldWidth, y);
        }

        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
