package org.acactown.jpropertiesmanager.model.source.remote;

import org.acactown.jpropertiesmanager.model.source.Source;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
abstract class RemoteSource extends Source {

    private String host;
    private int port;
    private String username;
    private String authorization;
    
    public RemoteSource(String host, int port, String username, String authorization, String name, String comment) {
        super(name, comment);
        this.host = host;
        this.port = port;
        this.username = username;
        this.authorization = authorization;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
