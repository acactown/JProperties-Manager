package org.acactown.jpropertiesmanager.view;

import org.acactown.jpropertiesmanager.controller.MainApp;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.acactown.jpropertiesmanager.model.tab.Tab;

/**
 * The Application's main frame.
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class MainView extends FrameView {

    public MainView( final SingleFrameApplication app , final List<Tab> tabs ) {
        super( app );
        initComponents();
        initStatusBar();
        initTaskMonitor();
        updateTabs( tabs );
    }

    /**
     * Status bar initialization - message timeout, idle icon and busy animation, etc.
     */
    private void initStatusBar() {
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger( "StatusBar.messageTimeout" );
        messageTimer = new Timer( messageTimeout , new ActionListener() {

            @Override
            public void actionPerformed( final ActionEvent e ) {
                statusMessageLabel.setText( "" );
            }
        } );
        messageTimer.setRepeats( false );
        int busyAnimationRate = resourceMap.getInteger( "StatusBar.busyAnimationRate" );
        for ( int i = 0 ; i < busyIcons.length ; i++ ) {
            busyIcons[i] = resourceMap.getIcon( "StatusBar.busyIcons[" + i + "]" );
        }
        busyIconTimer = new Timer( busyAnimationRate , new ActionListener() {

            @Override
            public void actionPerformed( final ActionEvent e ) {
                busyIconIndex = ( busyIconIndex + 1 ) % busyIcons.length;
                statusAnimationLabel.setIcon( busyIcons[busyIconIndex] );
            }
        } );
        idleIcon = resourceMap.getIcon( "StatusBar.idleIcon" );
        statusAnimationLabel.setIcon( idleIcon );
        progressBar.setVisible( false );

    }

    /**
     * Connecting action tasks to status bar via TaskMonitor.
     */
    private void initTaskMonitor() {
        TaskMonitor taskMonitor = new TaskMonitor( getApplication().getContext() );
        taskMonitor.addPropertyChangeListener( new PropertyChangeListener() {

            @Override
            public void propertyChange( final PropertyChangeEvent evt ) {
                String propertyName = evt.getPropertyName();
                if ( "started".equals( propertyName ) ) {
                    if ( !busyIconTimer.isRunning() ) {
                        statusAnimationLabel.setIcon( busyIcons[0] );
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible( true );
                    progressBar.setIndeterminate( true );
                } else if ( "done".equals( propertyName ) ) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon( idleIcon );
                    progressBar.setVisible( false );
                    progressBar.setValue( 0 );
                } else if ( "message".equals( propertyName ) ) {
                    String text = ( String ) ( evt.getNewValue() );
                    statusMessageLabel.setText( ( text == null ) ? "" : text );
                    messageTimer.restart();
                } else if ( "progress".equals( propertyName ) ) {
                    int value = ( Integer ) ( evt.getNewValue() );
                    progressBar.setVisible( true );
                    progressBar.setIndeterminate( false );
                    progressBar.setValue( value );
                }
            }
        } );
    }

    /**
     * Update Main GUI Window with given tabs.
     *
     * @param tabs 
     */
    private void updateTabs( final List<Tab> tabs ) {
    }

    @Action
    public void showAboutBox() {
        if ( aboutBox == null ) {
            JFrame mainFrame = MainApp.getInstance().getMainFrame();
            aboutBox = new AboutBox( mainFrame );
            aboutBox.setLocationRelativeTo( mainFrame );
        }
        MainApp.getInstance().show( aboutBox );
    }

    @SuppressWarnings ( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTabbedPaneMain = new javax.swing.JTabbedPane();
        jLabelLogo = new javax.swing.JLabel();
        jPanelComments = new javax.swing.JPanel();
        jLabelComments = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        jTabbedPaneMain.setName("jTabbedPaneMain"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(MainView.class);
        jLabelLogo.setIcon(resourceMap.getIcon("jLabelLogo.icon")); // NOI18N
        jLabelLogo.setText(resourceMap.getString("jLabelLogo.text")); // NOI18N
        jLabelLogo.setName("jLabelLogo"); // NOI18N

        jPanelComments.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelComments.setName("jPanelComments"); // NOI18N

        jLabelComments.setText("..."); // NOI18N
        jLabelComments.setAutoscrolls(true);
        jLabelComments.setName("jLabelComments"); // NOI18N

        javax.swing.GroupLayout jPanelCommentsLayout = new javax.swing.GroupLayout(jPanelComments);
        jPanelComments.setLayout(jPanelCommentsLayout);
        jPanelCommentsLayout.setHorizontalGroup(
            jPanelCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelComments, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCommentsLayout.setVerticalGroup(
            jPanelCommentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCommentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelComments, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelComments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLogo)
                .addGap(18, 18, 18))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelComments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelLogo)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(MainView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 487, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelComments;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanelComments;
    private javax.swing.JTabbedPane jTabbedPaneMain;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private Timer messageTimer;
    private Timer busyIconTimer;
    private Icon idleIcon;
    private Icon[] busyIcons = new Icon[ 15 ];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
