package com.sampler.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private static final int CELL_WIDTH = 200;
    public static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH; // 1080

    // abstract window toolkit
    // enables us to embed libgdx app or game into java desktop Swing app
    private LwjglAWTCanvas lwjglAWTCanvas;

    private JList sampleList;
    private JPanel controlPanel;

    public GdxSamplerLauncher() throws HeadlessException {
        init();
    }

    private void init() {

        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if(lwjglAWTCanvas != null) {
                    // calls dispose and ends operation
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // tell window JFrame to resize and layout our components
        pack();
        setVisible(true);

//        launchSample("com.sampler.InputPollingSample");
    }

    private void createControlPanel() {
        // aligns our components in rows and cells like in Exel
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // constraints for the scroll pane added below
        c.gridx = 0; // column
        c.gridy = 0; // row
        c.fill = GridBagConstraints.VERTICAL;
        // every component put below out list will not
        // take too much space
        c.weighty = 1;  // wieght used in filling empty space

        sampleList = new JList(
                new String[] {  "com.sampler.InputPollingSample" }
        );
        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // launch sample on double click
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2) {
                    launchSelectedSample();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, c);  // add scroll pane to panel with constraints

        // constraints for the button
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL; // fill horizontally
        c.weighty = 0;

        JButton launchButton = new JButton("Launch Sample");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchSelectedSample();
            }
        });

        controlPanel.add(launchButton, c);
    }

    private void launchSelectedSample() {
        String sampleName = (String) sampleList.getSelectedValue();

        if(sampleName == null | sampleName.isEmpty()){
            System.out.println("Sample name is empty. cannot launch.");
        }

        launchSample(sampleName);
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
            // full class name... package...class name
            Class<ApplicationListener> clazz = ClassReflection.forName(name);

            // create new instance of our sample class
            sample = ClassReflection.newInstance(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create sample with name= " + name, e);
        }

        // constructor for LWJGLAWWTCanvas accepts our sample
        // pass the Sample which implements ApplicationListener
        // to lwjglAWTCanvas for display
        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
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
