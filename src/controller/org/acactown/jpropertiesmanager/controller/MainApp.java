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
    private final String LOG4J_PROPERTIES_PATH =
                         "org/acactown/jpropertiesmanager/controller/resources/log4j.properties";
    private final String SESSIONS_DIRECTORY = WORKING_DIRECTORY + File.separator + "sessions";
    private final String LOG_DIRECTORY = WORKING_DIRECTORY + File.separator + "logs";
    public static final String WORKING_DIRECTORY = System.getProperty( "user.home" ) + File.separator
            + ".JProperties-Manager";
    public static final String TMP_DIRECTORY = WORKING_DIRECTORY + File.separator + "temp";
    public static final String CONF_DIRECTORY = WORKING_DIRECTORY + File.separator + "conf";

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        checkWorkingDirectories();
        loadLogProperties();
        loadSavedConfiguration();
        getContext().getLocalStorage().setDirectory( new File( SESSIONS_DIRECTORY ) );
        mainView = new MainView( this , new ArrayList<Tab>() );
        show( mainView );
    }

    /**
     * Run when the APP is shutdown.
     */
    @Override
    protected void shutdown() {
        deleteDirectory( new File( TMP_DIRECTORY ) );
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
        PropertyConfigurator.configure( ClassLoader.getSystemResource( LOG4J_PROPERTIES_PATH ) );
    }

    /**
     * Check if working directories exist.
     */
    public void checkWorkingDirectories() {
        List<String> directories = Arrays.asList( WORKING_DIRECTORY , CONF_DIRECTORY , TMP_DIRECTORY ,
                                                  SESSIONS_DIRECTORY );
        File tmpDirectory;

        for ( String directory : directories ) {
            tmpDirectory = new File( directory );

            if ( !tmpDirectory.exists() ) {
                tmpDirectory.mkdir();
            }
        }
    }

    /**
     * Load Saved or Default Configuration.
     */
    private void loadSavedConfiguration() {
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
                boolean success = deleteDirectory( new File( directory , children[i] ) );

                if ( !success ) {
                    return false;
                }
            }
        }

        return directory.delete();
    }

    /**
     * Main method launching the application.
     * @param args Maybe son arguments for command line
     */
    public static void main( final String[] args ) {
        launch( MainApp.class , args );
    }
}
