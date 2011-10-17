package org.acactown.jpropertiesmanager.model.location.remote;

import org.acactown.jpropertiesmanager.model.location.Location;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
abstract class RemoteLocation extends Location {

    private String host;
    private Integer port;
    private String username;
    private String authorization;
    private SecurityType securityType;

    public RemoteLocation() {
    }

    public RemoteLocation( final String host , final Integer port , final String username ,
                           final String authorization , final SecurityType securityType , final String name ,
                           final String comment ) {
        super( name , comment );
        this.host = host;
        this.port = port;
        this.username = username;
        this.authorization = authorization;
        this.securityType = securityType;
    }

    public final String getHost() {
        return host;
    }

    public final void setHost( final String host ) {
        this.host = host;
    }

    public final String getAuthorization() {
        return authorization;
    }

    public final void setAuthorization( final String authorization ) {
        this.authorization = authorization;
    }

    public final Integer getPort() {
        return port;
    }

    public final void setPort( final Integer port ) {
        this.port = port;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername( final String username ) {
        this.username = username;
    }

    public final SecurityType getSecurityType() {
        return securityType;
    }

    public final void setSecurityType( final SecurityType securityType ) {
        this.securityType = securityType;
    }
}
