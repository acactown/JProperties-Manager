package org.acactown.jpropertiesmanager.view;

import java.awt.Window;
import org.acactown.jpropertiesmanager.controller.MainController;
import org.acactown.jpropertiesmanager.model.MainModel;
import org.apache.log4j.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The Main Class of the JProperties-Manager application.
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class MainApp extends SingleFrameApplication {

    private static final Logger LOG = Logger.getLogger(MainApp.class);

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        ApplicationContext context = getContext();
        MainModel.startup(context);
        MainController.startup(context);
        show(new MainView(this));
    }

    /**
     * Run when the APP is shutdown
     */
    @Override
    protected void shutdown() {
        ApplicationContext context = getContext();
        MainModel.shutdown(context);
        MainController.shutdown(context);
        super.shutdown();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(Window root) {
        root.setIconImage(getContext().getResourceMap().getImageIcon("Application.icon").getImage());
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Main
     */
    public static MainApp getApplication() {
        return Application.getInstance(MainApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(MainApp.class, args);
    }
}
