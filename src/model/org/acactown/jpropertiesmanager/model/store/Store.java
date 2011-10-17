package org.acactown.jpropertiesmanager.model.store;

import org.acactown.jpropertiesmanager.controller.MainApp;
import org.acactown.jpropertiesmanager.model.Id;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class Store extends Id {

    protected static Object instance = null;
    private final String STORE_NAME = getClass().getSimpleName();
    private final String STORE_FILE = MainApp.CONF_DIRECTORY + File.separator + STORE_NAME + ".xml";
    private static final Logger LOG = Logger.getLogger( Store.class );

    /**
     * Load Store Config from disk.
     *
     * @return <code>TRUE</code> if Config was successful loaded, <code>FALSE</code> otherwise
     */
    public final boolean loadConfig() {
        boolean wasLoaded = false;
        try {
            File config = new File( STORE_FILE );
            if(!config.exists()){
                return wasLoaded;
            }
            JAXBContext context = JAXBContext.newInstance( getClass() );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            instance = unmarshaller.unmarshal( new FileReader( config ) );
            wasLoaded = true;
        } catch ( Exception ex ) {
            LOG.error( "Error loading saved config for Store [" + STORE_NAME + "]" , ex );
        }

        return wasLoaded;
    }

    /**
     * Save Store Config to disk.
     *
     * @return <code>TRUE</code> if Config was successful saved, <code>FALSE</code> otherwise
     */
    public final boolean saveConfig() {
        boolean wasSaved = false;
        Writer writter = null;
        try {
            File config = new File( STORE_FILE );
            if(!config.exists()){
                MainApp.getInstance().checkWorkingDirectories();
                config.createNewFile();
            }
            writter = new OutputStreamWriter( new FileOutputStream( config ) );
            JAXBContext context = JAXBContext.newInstance( getClass() );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT , Boolean.TRUE );
            marshaller.marshal( this , writter );
            wasSaved = true;
        } catch ( Exception ex ) {
            LOG.error( "Error writing config for Store [" + STORE_NAME + "]" , ex );
        } finally {
            if ( writter != null ) {
                try {
                    writter.close();
                } catch ( Exception e ) {
                    LOG.error( e );
                }
            }
        }

        return wasSaved;
    }
}
