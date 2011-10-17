package org.acactown.jpropertiesmanager.model.location;

import org.acactown.jpropertiesmanager.model.id.Id;
import java.util.Properties;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class Location extends Id {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String comment;
    public static final String OLD_EXTENTION = ".old";

    public Location() {
    }

    public Location( final String name , final String comment ) {
        this.name = name;
        this.comment = comment;
    }

    @XmlTransient
    public final String getComment() {
        return comment;
    }

    public final void setComment( final String comment ) {
        this.comment = comment;
    }

    @XmlTransient
    public final String getName() {
        return name;
    }

    public final void setName( final String name ) {
        this.name = name;
    }

    public abstract String getNameWithLocation();

    public abstract boolean saveProperties( final String file , final Properties properties );

    public abstract Properties loadProperties( final String file );
    
}
