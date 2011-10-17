package org.acactown.jpropertiesmanager.model.property;

import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
interface PropertyFileOperations {

    boolean canSave();

    boolean canCopyToClipboard();

    boolean canLoad();

    boolean canEdit();

    boolean save();

    boolean copyToClipboard();

    boolean load();

    void edit( final Properties properties );
}
