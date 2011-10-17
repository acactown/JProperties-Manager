package org.acactown.jpropertiesmanager.model.location.remote;

import org.apache.log4j.Logger;
import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class S3Location extends RemoteLocation {

    private static final Logger LOG = Logger.getLogger( S3Location.class );

    public S3Location() {
    }

    public S3Location( final String host , final Integer port , final String username ,
                       final String password , final SecurityType securityType , final String name ,
                       final String comment ) {
        super( host , port , username , password , securityType , name , comment );
    }

    @Override
    public boolean saveProperties( final String file , final Properties properties ) {
        LOG.debug( "Saving properties in file [" + file + "]" );
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public Properties loadProperties( final String file ) {
        LOG.debug( "Loading properties from file [" + file + "]" );
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public String getNameWithLocation() {
        return "S3 - " + getName();
    }
}
