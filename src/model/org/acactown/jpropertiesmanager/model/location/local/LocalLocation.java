package org.acactown.jpropertiesmanager.model.location.local;

import org.acactown.jpropertiesmanager.model.location.Location;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class LocalLocation extends Location {

    private String path;
    private static final int BUFFER_SIZE = 1024;
    private static final Logger LOG = Logger.getLogger( LocalLocation.class );

    public LocalLocation() {
    }

    public LocalLocation( final String path , final String name , final String comment ) {
        super( name , comment );
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath( final String path ) {
        this.path = path;
    }

    @Override
    public Properties loadProperties( final String file ) {
        LOG.debug( "Loading properties from file [" + file + "]" );
        Properties props = new Properties();
        try {
            props.load( new FileInputStream( file ) );
        } catch ( Exception ex ) {
            LOG.error( "Error loading properties from file [" + file + "]" , ex );

            return null;
        }

        return props;
    }

    @Override
    public boolean saveProperties( final String file , final Properties properties ) {
        LOG.debug( "Saving properties in file [" + file + "]" );
        try {
            if ( !new File( file ).exists() ) {
                properties.store( new FileOutputStream( file ) , null );
            }
            InputStream in = new FileInputStream( file );
            OutputStream out = new FileOutputStream( file + OLD_EXTENTION );
            byte[] buf = new byte[ BUFFER_SIZE ];
            int len;
            while ( ( len = in.read( buf ) ) > 0 ) {
                out.write( buf , 0 , len );
            }
            in.close();
            out.close();
            properties.store( new FileOutputStream( file ) , null );

            return true;
        } catch ( Exception ex ) {
            LOG.error( "Error saving properties in file [" + file + "]" , ex );

            return false;
        }
    }

    @Override
    public String getNameWithLocation() {
        return "Local - " + getName();
    }
}
