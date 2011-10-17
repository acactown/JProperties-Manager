package org.acactown.jpropertiesmanager.model.storage;

import org.acactown.jpropertiesmanager.controller.MainApp;
import org.acactown.jpropertiesmanager.model.id.Id;
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
public abstract class Storage extends Id {

    protected static Object instance = null;
    private final String STORAGE_NAME = getClass().getSimpleName();
    private final String STORAGE_FILE = MainApp.STORAGE_DIRECTORY + File.separator + STORAGE_NAME + ".xml";
    private static final Logger LOG = Logger.getLogger( Storage.class );

    /**
     * Load Storage from disk.
     *
     * @return <code>TRUE</code> if Storage was successful loaded, <code>FALSE</code> otherwise
     */
    public final boolean loadStorage() {
        boolean wasLoaded = false;
        try {
            File config = new File( STORAGE_FILE );
            if(!config.exists()){
                return wasLoaded;
            }
            JAXBContext context = JAXBContext.newInstance( getClass() );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            instance = unmarshaller.unmarshal( new FileReader( config ) );
            wasLoaded = true;
        } catch ( Exception ex ) {
            LOG.error( "Error loading saved config for Storage [" + STORAGE_NAME + "]" , ex );
        }

        return wasLoaded;
    }

    /**
     * Save Storage to disk.
     *
     * @return <code>TRUE</code> if Storage was successful saved, <code>FALSE</code> otherwise
     */
    public final boolean saveStorage() {
        boolean wasSaved = false;
        Writer writter = null;
        try {
            File config = new File( STORAGE_FILE );
            if(!config.exists()){
                MainApp.getInstance().checkDirectories();
                config.createNewFile();
            }
            writter = new OutputStreamWriter( new FileOutputStream( config ) );
            JAXBContext context = JAXBContext.newInstance( getClass() );
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT , Boolean.TRUE );
            marshaller.marshal( this , writter );
            wasSaved = true;
        } catch ( Exception ex ) {
            LOG.error( "Error writing config for Storage [" + STORAGE_NAME + "]" , ex );
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
