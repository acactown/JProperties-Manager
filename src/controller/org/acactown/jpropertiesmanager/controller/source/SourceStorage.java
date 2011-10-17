package org.acactown.jpropertiesmanager.controller.source;

import org.acactown.jpropertiesmanager.model.source.local.LocalSource;
import org.acactown.jpropertiesmanager.model.source.remote.FTPSource;
import org.acactown.jpropertiesmanager.model.source.remote.S3Source;
import org.acactown.jpropertiesmanager.model.source.remote.SSHSource;
import org.acactown.jpropertiesmanager.model.storage.Storage;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlRootElement ( name = "sources" )
public final class SourceStorage extends Storage {

    @XmlElement ( name = "ftp" )
    private ArrayList<FTPSource> ftpSources;
    @XmlElement ( name = "s3" )
    private ArrayList<S3Source> s3Sources;
    @XmlElement ( name = "ssh" )
    private ArrayList<SSHSource> sshSources;
    @XmlElement ( name = "local" )
    private LocalSource localSource;
    private static final Logger LOG = Logger.getLogger( SourceStorage.class );

    private SourceStorage() {
    }

    public synchronized static SourceStorage getInstance() {
        if ( instance == null ) {
            instance = new SourceStorage();
        }

        return ( SourceStorage ) instance;
    }

    public LocalSource getLocalSource() {
        return localSource;
    }

    public void setLocalSource( final LocalSource localSource ) {
        this.localSource = localSource;
    }

    public void addSSHSource( final SSHSource source ) {
        if ( sshSources == null ) {
            sshSources = new ArrayList<SSHSource>( 1 );
        }

        sshSources.add( source );
    }

    public void addFTPSource( final FTPSource source ) {
        if ( ftpSources == null ) {
            ftpSources = new ArrayList<FTPSource>( 1 );
        }

        ftpSources.add( source );
    }

    public void addS3Source( final S3Source source ) {
        if ( s3Sources == null ) {
            s3Sources = new ArrayList<S3Source>( 1 );
        }

        s3Sources.add( source );
    }

    public ArrayList<FTPSource> getFtpSources() {
        return ftpSources;
    }

    public void setFtpSources( final ArrayList<FTPSource> ftpSources ) {
        this.ftpSources = ftpSources;
    }

    public ArrayList<S3Source> getS3Sources() {
        return s3Sources;
    }

    public void setS3Sources( final ArrayList<S3Source> s3Sources ) {
        this.s3Sources = s3Sources;
    }

    public ArrayList<SSHSource> getSshSources() {
        return sshSources;
    }

    public void setSshSources( final ArrayList<SSHSource> sshSources ) {
        this.sshSources = sshSources;
    }
}
