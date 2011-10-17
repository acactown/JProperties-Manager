package org.acactown.jpropertiesmanager.model.property;

import org.acactown.jpropertiesmanager.model.id.Id;
import org.acactown.jpropertiesmanager.model.location.Location;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class PropertiesFile extends Id implements PropertiesFileOperations , ClipboardOwner {

    private String name;
    private Properties properties;
    private final String path;
    private final Location location;

    public PropertiesFile( final Location location , final String path , final String name ) throws Exception {
        this.location = location;
        this.path = path;
        this.name = name;
        properties = location.loadProperties( path );
        if ( properties == null ) {
            StringBuilder builder = new StringBuilder();
            builder.append( "The file [" ).append( path ).append( "]" );
            builder.append( " from Location [" ).append( location.getNameWithLocation() ).append( "]" );
            builder.append( " could not be loaded" );

            throw new Exception( builder.toString() );
        }
    }

    public final String getName() {
        return name;
    }

    public final void setName( final String name ) {
        this.name = name;
    }

    public final String getPath() {
        return path;
    }

    public final Properties getProperties() {
        return properties;
    }

    public final Location getLocation() {
        return location;
    }

    @Override
    public void lostOwnership( final Clipboard clipboard , final Transferable contents ) {
        // Nothing to do!
    }

    @Override
    public final boolean copyPropertiesToClipboard() {
        if ( canCopyPropertiesToClipboard() ) {
            StringBuilder builder = new StringBuilder();
            for ( Entry<Object , Object> entry : properties.entrySet() ) {
                builder.append( entry.getKey() ).append( " = " ).append( entry.getValue() ).append( "\n" );
            }
            StringSelection stringSelection = new StringSelection( builder.toString() );
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents( stringSelection , this );

            return true;
        } else {
            throw new UnsupportedOperationException( "Not supported." );
        }
    }

    @Override
    public final boolean loadProperties() {
        if ( canLoadProperties() ) {
            Properties tmpProperties = location.loadProperties( path );
            if ( tmpProperties == null ) {
                return false;
            } else {
                properties.clear();
                properties.putAll( tmpProperties );
                sortProperties();

                return true;
            }
        } else {
            throw new UnsupportedOperationException( "Not supported." );
        }
    }

    @Override
    public final boolean saveProperties() {
        if ( canSaveProperties() ) {
            return location.saveProperties( path , properties );
        } else {
            throw new UnsupportedOperationException( "Not supported." );
        }
    }

    @Override
    public final void editProperties( final Properties editProperties ) {
        if ( canEditProperties() ) {
            properties.clear();
            properties.putAll( editProperties );
        }
    }

    @Override
    public final void sortProperties() {
        if ( properties != null ) {
            Properties sortedProperties = new Properties() {

                private static final long serialVersionUID = -8604519353989867555L;

                @Override
                public Set<Object> keySet() {
                    return Collections.unmodifiableSet( new TreeSet<Object>( super.keySet() ) );
                }
            };
            sortedProperties.putAll( properties );
            properties.clear();
            properties.putAll( sortedProperties );
        }
    }
}
