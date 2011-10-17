package org.acactown.jpropertiesmanager.model.property;

import org.acactown.jpropertiesmanager.model.Id;
import org.acactown.jpropertiesmanager.model.source.Source;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Map.Entry;
import java.util.Properties;

/**
 *
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class PropertyFile extends Id implements PropertyFileOperations , ClipboardOwner {

    private String name;
    private final String path;
    private Properties properties;
    private final Source source;

    public PropertyFile( final Source source , final String path , final String name ) throws Exception {
        this.source = source;
        this.path = path;
        this.name = name;
        properties = source.load( path );

        if ( properties == null ) {
            StringBuilder builder = new StringBuilder();

            builder.append( "The file [" ).append( path ).append( "]" );
            builder.append( " from source [" ).append( source.getNameWithSource() ).append( "]" );
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

    public final Source getSource() {
        return source;
    }

    @Override
    public void lostOwnership( final Clipboard clipboard , final Transferable contents ) {
        // Nothing to do!
    }

    @Override
    public final boolean copyToClipboard() {
        if ( canCopyToClipboard() ) {
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
    public final boolean load() {
        if ( canLoad() ) {
            Properties tmpProperties = source.load( path );

            if ( tmpProperties == null ) {
                return false;
            } else {
                properties = tmpProperties;

                return true;
            }
        } else {
            throw new UnsupportedOperationException( "Not supported." );
        }
    }

    @Override
    public final boolean save() {
        if ( canSave() ) {
            return source.save( path , properties );
        } else {
            throw new UnsupportedOperationException( "Not supported." );
        }
    }

    @Override
    public final void edit( final Properties editProperties ) {
        if ( canEdit() ) {
            properties.clear();
            editProperties.putAll( properties );
        }
    }
}
