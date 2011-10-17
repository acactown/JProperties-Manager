package org.acactown.jpropertiesmanager.controller.location;

import java.awt.event.ComponentEvent;
import org.acactown.jpropertiesmanager.model.tab.Tab;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class Locations extends Tab {

    private static final long serialVersionUID = 7678477591330994375L;

    public Locations() {
        super( "" , "" );
        initComponents();
    }

    @SuppressWarnings ( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void onShow( final ComponentEvent event ) {
        throw new UnsupportedOperationException( "Not supported yet." );
    }
}
