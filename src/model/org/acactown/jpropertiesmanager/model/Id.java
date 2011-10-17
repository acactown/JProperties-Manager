package org.acactown.jpropertiesmanager.model;

import java.util.Random;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Default Id for all Model Classes.
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class Id {

    @XmlAttribute
    private final String id;

    public Id() {
        id = Integer.toString( new Random().nextInt( Integer.MAX_VALUE ) );
    }

    public final String getId() {
        return id;
    }

    @Override
    public final boolean equals( final Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Id other = ( Id ) obj;
        if ( ( this.id == null ) ? ( other.getId() != null ) : !this.id.equals( other.getId() ) ) {
            return false;
        }

        return true;
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        return hash;
    }
}
