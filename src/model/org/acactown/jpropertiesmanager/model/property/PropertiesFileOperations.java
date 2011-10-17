package org.acactown.jpropertiesmanager.model.property;

import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
interface PropertiesFileOperations {

    boolean canSaveProperties();

    boolean canCopyPropertiesToClipboard();

    boolean canLoadProperties();

    boolean canEditProperties();

    boolean saveProperties();

    boolean copyPropertiesToClipboard();

    boolean loadProperties();

    void editProperties( final Properties properties );

    void sortProperties();
}
