package org.acactown.jpropertiesmanager.model.source;

import org.acactown.jpropertiesmanager.model.Id;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class Source extends Id {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String comment;
    public static final String OLD_EXTENTION = ".old";

    public Source() {
    }

    public Source( final String name , final String comment ) {
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

    public final Properties load( final String file ) {
        Properties properties = reload( file );
        if ( properties != null ) {
            Properties sortProperties = new Properties() {

                private static final long serialVersionUID = -8604519353989867555L;

                @Override
                public Set<Object> keySet() {
                    return Collections.unmodifiableSet( new TreeSet<Object>( super.keySet() ) );
                }
            };
            sortProperties.putAll( properties );
            properties.clear();
            properties.putAll( sortProperties );
        }

        return properties;
    }

    public abstract String getNameWithSource();

    public abstract boolean save( final String file , final Properties properties );

    protected abstract Properties reload( final String file );
}
