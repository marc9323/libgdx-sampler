package com.sampler.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    // abstract window toolkit
    private LwjglAWTCanvas lwjglAWTCanvas;

    public GdxSamplerLauncher() throws HeadlessException {
        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // tell window JFrame to resize and layout our components
        pack();
        setVisible(true);

        launchSample("com.sampler.InputPollingSample");
    }

    private void launchSample(String name) {
        System.out.println("launching sample name = + name");

        Container container = getContentPane();

        if(lwjglAWTCanvas != null) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        // use reflection to instantiate sample

        ApplicationListener sample;

        try {
            // get sample class object by name
            Class<ApplicationListener> clazz = ClassReflection.forName(name);

            // create new instance of our sample class
            sample = ClassReflection.newInstance(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create sample with name= " + name, e);
        }

        // constructor for LWJGLAWWTCanvas accepts our sample
        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(WIDTH, HEIGHT);
        // add lwjglAWTCanvas to Swing JFrame
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        // pack the layout again as new stuff was added
        pack();

    }


    // main method
    public static void main(String[] args) {
        // must be used to run JFrame properly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }
}
