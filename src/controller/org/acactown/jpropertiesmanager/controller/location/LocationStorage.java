package org.acactown.jpropertiesmanager.controller.location;

import org.acactown.jpropertiesmanager.model.location.local.LocalLocation;
import org.acactown.jpropertiesmanager.model.location.remote.FTPLocation;
import org.acactown.jpropertiesmanager.model.location.remote.S3Location;
import org.acactown.jpropertiesmanager.model.location.remote.SSHLocation;
import org.acactown.jpropertiesmanager.model.storage.Storage;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlRootElement ( name = "locations" )
public final class LocationStorage extends Storage {

    @XmlElement ( name = "local" )
    private LocalLocation localLocation;
    @XmlElementWrapper ( name = "ftpLocations" )
    @XmlElement ( name = "ftp" )
    private ArrayList<FTPLocation> ftpLocations;
    @XmlElementWrapper ( name = "s3Locations" )
    @XmlElement ( name = "s3" )
    private ArrayList<S3Location> s3Locations;
    @XmlElementWrapper ( name = "sshLocations" )
    @XmlElement ( name = "ssh" )
    private ArrayList<SSHLocation> sshLocations;
    private static final Logger LOG = Logger.getLogger( LocationStorage.class );

    private LocationStorage() {
    }

    public synchronized static LocationStorage getInstance() {
        if ( instance == null ) {
            instance = new LocationStorage();
        }

        return ( LocationStorage ) instance;
    }

    public LocalLocation getLocalLocation() {
        return localLocation;
    }

    public void setLocalLocation( final LocalLocation localLocation ) {
        this.localLocation = localLocation;
    }

    public void addSshLocation( final SSHLocation location ) {
        if ( sshLocations == null ) {
            sshLocations = new ArrayList<SSHLocation>( 1 );
        }

        sshLocations.add( location );
    }

    public void addFtpLocation( final FTPLocation location ) {
        if ( ftpLocations == null ) {
            ftpLocations = new ArrayList<FTPLocation>( 1 );
        }

        ftpLocations.add( location );
    }

    public void addS3Location( final S3Location location ) {
        if ( s3Locations == null ) {
            s3Locations = new ArrayList<S3Location>( 1 );
        }

        s3Locations.add( location );
    }

    public ArrayList<FTPLocation> getFtpLocations() {
        return ftpLocations;
    }

    public void setFtpLocations( final ArrayList<FTPLocation> ftpLocations ) {
        this.ftpLocations = ftpLocations;
    }

    public ArrayList<S3Location> getS3Locations() {
        return s3Locations;
    }

    public void setS3Locations( final ArrayList<S3Location> s3Locations ) {
        this.s3Locations = s3Locations;
    }

    public ArrayList<SSHLocation> getSshLocations() {
        return sshLocations;
    }

    public void setSshLocations( final ArrayList<SSHLocation> sshLocations ) {
        this.sshLocations = sshLocations;
    }
}
