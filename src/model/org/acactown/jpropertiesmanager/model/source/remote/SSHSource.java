package org.acactown.jpropertiesmanager.model.source.remote;

import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class SSHSource extends RemoteSource {

    private SSHSecurity security;

    public enum SSHSecurity {
        PASSWORD, IDENTIFY, NOTHING;
    }
    
    public static final int DEFAULT_PORT = 22;
    
    private static final Logger LOG = Logger.getLogger(SSHSource.class);

    public SSHSource(SSHSecurity security, String host, int port, String username, String password, String name, String comment) {
        super(host, port, username, password, name, comment);
        this.security = security;
    }

    public SSHSecurity getSecurity() {
        return security;
    }

    public void setSecurity(SSHSecurity security) {
        this.security = security;
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
        return "SSH - " + getName();
    }
}
