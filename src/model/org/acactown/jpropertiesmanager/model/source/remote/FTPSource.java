package org.acactown.jpropertiesmanager.model.source.remote;

import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class FTPSource extends RemoteSource {
    
    public static final int DEFAULT_PORT = 21;
    
    private static final Logger LOG = Logger.getLogger(FTPSource.class);

    public FTPSource(String host, int port, String username, String password, String name, String comment) {
        super(host, port, username, password, name, comment);
    }

    @Override
    public boolean save(String file, Properties properties) {
        LOG.debug("Saving properties in file [" + file + "]");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected Properties reload(String file) {
        LOG.debug("Loading properties from file [" + file + "]");
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String getNameWithSource() {
        return "FTP - " + getName();
    }
    
}
