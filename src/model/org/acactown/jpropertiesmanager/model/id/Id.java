package org.acactown.jpropertiesmanager.model.id;

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
    private final int HASH = 5;

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
        final Id OTHER = ( Id ) obj;
        if ( ( this.id == null ) ? ( OTHER.getId() != null ) : !this.id.equals( OTHER.getId() ) ) {
            return false;
        }

        return true;
    }

    @Override
    public final int hashCode() {
        return HASH;
    }
}
