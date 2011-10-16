package org.acactown.jpropertiesmanager.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.application.ApplicationContext;

/**
 * Main for Controller Module
 * 
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class MainController {

    public static final String WORKING_DIRECTORY = System.getProperty("user.home") + File.separator + ".JProperties-Manager";
    public static final String TMP_DIRECTORY = WORKING_DIRECTORY + File.separator + "temp";
    private static final String SESSIONS_DIRECTORY = WORKING_DIRECTORY + File.separator + "sessions";
    private static final String LOG_DIRECTORY = WORKING_DIRECTORY + File.separator + "logs";
    private static final String LOG4J_PROPERTIES_PATH = "org/acactown/jpropertiesmanager/controller/resources/log4j.properties";

    /**
     * Load Default Log properties
     */
    private static void loadLogProperties() {
        System.setProperty("org.acactown.jpropertiesmamager.log.directory", LOG_DIRECTORY);
        PropertyConfigurator.configure(ClassLoader.getSystemResource(LOG4J_PROPERTIES_PATH));
    }

    /**
     * Check if working directories exist
     */
    private static void checkWorkingDirectories() {
        List<String> directories = Arrays.asList(WORKING_DIRECTORY, TMP_DIRECTORY, SESSIONS_DIRECTORY);
        File tmpDirectory;
        for (String directory : directories) {
            tmpDirectory = new File(directory);
            if (!tmpDirectory.exists()) {
                tmpDirectory.mkdir();
            }
        }
    }

    /**
     * Recursive method for delete all content for a given directory
     * 
     * @param directory File directory to be deleted
     * @return <code>TRUE</code> If given directory was successfully deleted <code>FALSE</code> otherwise
     */
    private static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            String[] children = directory.list();
            for (int i = 0, max = children.length ; i < max; i++) {
                boolean success = deleteDirectory(new File(directory, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return directory.delete();
    }

    /**
     * Startup Controller Module
     * 
     * @param context 
     */
    public static void startup(ApplicationContext context) {
        checkWorkingDirectories();
        loadLogProperties();
        context.getLocalStorage().setDirectory(new File(SESSIONS_DIRECTORY));
    }

    /**
     * Shutdown Controller Module
     * 
     * @param context 
     */
    public static void shutdown(ApplicationContext context) {
        deleteDirectory(new File(TMP_DIRECTORY));
    }
}
