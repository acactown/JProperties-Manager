package org.acactown.jpropertiesmanager.controller;

import org.acactown.jpropertiesmanager.model.tab.Tab;
import org.acactown.jpropertiesmanager.view.MainView;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.awt.Window;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Main Class of the JProperties-Manager application.
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class MainApp extends SingleFrameApplication {

    private MainView mainView;
    private final String LOG4J_PROPERTIES =
                         "org/acactown/jpropertiesmanager/controller/resources/log4j.properties";
    private final String SESSION_DIRECTORY = WORK_DIRECTORY + File.separator + "session";
    private final String LOG_DIRECTORY = WORK_DIRECTORY + File.separator + "log";
    public static final String WORK_DIRECTORY = System.getProperty( "user.home" ) + File.separator
            + ".JProperties-Manager";
    public static final String TEMP_DIRECTORY = WORK_DIRECTORY + File.separator + "temp";
    public static final String STORAGE_DIRECTORY = WORK_DIRECTORY + File.separator + "storage";

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        checkDirectories();
        loadLogProperties();
        getContext().getLocalStorage().setDirectory( new File( SESSION_DIRECTORY ) );
        mainView = new MainView( this , new ArrayList<Tab>() );
        show( mainView );
    }

    /**
     * Run when the APP is shutdown.
     */
    @Override
    protected void shutdown() {
        deleteDirectory( new File( TEMP_DIRECTORY ) );
        super.shutdown();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow( final Window root ) {
        root.setIconImage( getContext().getResourceMap().getImageIcon( "Application.icon" ).getImage() );
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Main
     */
    public synchronized static MainApp getInstance() {
        return Application.getInstance( MainApp.class );
    }

    /**
     * Load Default Log properties.
     */
    private void loadLogProperties() {
        System.setProperty( "file.encoding" , "UTF-8" );
        System.setProperty( "org.acactown.jpropertiesmamager.log.directory" , LOG_DIRECTORY );
        PropertyConfigurator.configure( ClassLoader.getSystemResource( LOG4J_PROPERTIES ) );
    }

    /**
     * Check if working directories exist.
     */
    public void checkDirectories() {
        List<String> directories = Arrays.asList( WORK_DIRECTORY , STORAGE_DIRECTORY , TEMP_DIRECTORY ,
                                                  SESSION_DIRECTORY );
        File tmpDirectory;
        for ( String directory : directories ) {
            tmpDirectory = new File( directory );
            if ( !tmpDirectory.exists() ) {
                tmpDirectory.mkdir();
            }
        }
    }

    /**
     * Recursive method for delete all content for a given directory.
     *
     * @param directory File directory to be deleted
     * @return <code>TRUE</code> If given directory was successfully deleted <code>FALSE</code> otherwise
     */
    private boolean deleteDirectory( final File directory ) {
        if ( directory.isDirectory() ) {
            String[] children = directory.list();
            for ( int i = 0, max = children.length ; i < max ; i++ ) {
                if ( !deleteDirectory( new File( directory , children[i] ) ) ) {
                    return false;
                }
            }
        }

        return directory.delete();
    }

    /**
     * Main method launching the application.
     *
     * @param args Maybe son arguments for command line
     */
    public static void main( final String[] args ) {
        launch( MainApp.class , args );
    }
}
