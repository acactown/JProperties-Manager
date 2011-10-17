package org.acactown.jpropertiesmanager.model.location.remote;

import org.apache.log4j.Logger;
import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class SSHLocation extends RemoteLocation {

    public static final Integer DEFAULT_PORT = 22;
    private static final Logger LOG = Logger.getLogger( SSHLocation.class );

    public SSHLocation() {
    }

    public SSHLocation( final String host , final Integer port , final String username ,
                        final String authorization , final SecurityType securityType , final String name ,
                        final String comment ) {
        super( host , port , username , authorization , securityType , name , comment );
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
        return "SSH - " + getName();
    }
}
