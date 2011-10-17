package org.acactown.jpropertiesmanager.model.tab;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class Tab extends JPanel {

    private String comment;
    private boolean loaded;
    private static final long serialVersionUID = -6761192265025155294L;

    public Tab( final String name , final String comment ) {
        setName( name );
        this.comment = comment;
        loaded = false;
        addComponentListener( new ComponentAdapterImpl() );
    }

    public final String getComment() {
        return comment;
    }

    public final void setComment( final String comment ) {
        this.comment = comment;
    }

    public final boolean wasLoaded() {
        return loaded;
    }

    public final void setLoaded( final boolean loaded ) {
        this.loaded = loaded;
    }

    public abstract void onShow( final ComponentEvent event );

    private final class ComponentAdapterImpl extends ComponentAdapter {

        public ComponentAdapterImpl() {
        }

        @Override
        public void componentShown( final ComponentEvent event ) {
            onShow( event );
        }
    }

}
