package org.acactown.jpropertiesmanager.model.source.remote;

import org.apache.log4j.Logger;
import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class S3Source extends RemoteSource {

    private static final Logger LOG = Logger.getLogger( S3Source.class );

    public S3Source() {
    }

    public S3Source( final String host , final Integer port , final String username , final String password ,
                     final SecurityType securityType , final String name , final String comment ) {
        super( host , port , username , password , securityType , name , comment );
    }

    @Override
    public boolean save( final String file , final Properties properties ) {
        LOG.debug( "Saving properties in file [" + file + "]" );
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    protected Properties reload( final String file ) {
        LOG.debug( "Loading properties from file [" + file + "]" );
        throw new UnsupportedOperationException( "Not supported yet." );
    }

    @Override
    public String getNameWithSource() {
        return "S3 - " + getName();
    }
}
