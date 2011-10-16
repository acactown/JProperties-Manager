package org.acactown.jpropertiesmanager.model;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class MainModel {
    
    public static final String LOG4J_PROPERTIES_PATH = "org/acactown/jpropertiesmanager/model/resources/log4j.properties";
    
    /**
     * 
     * @param args Maybe the command line arguments
     */
    public static void main(String[] args) {
        PropertyConfigurator.configure(ClassLoader.getSystemResource(LOG4J_PROPERTIES_PATH));
    }
}
