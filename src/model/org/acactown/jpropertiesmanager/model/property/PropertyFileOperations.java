package org.acactown.jpropertiesmanager.model.property;

import java.util.Properties;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
interface PropertyFileOperations {
    
    public boolean canSave();
    
    public boolean canCopyToClipboard();
    
    public boolean canLoad();
    
    public boolean canEdit();
    
    public boolean save();
    
    public boolean copyToClipboard();
    
    public boolean load();
    
    public void edit(Properties properties);
}
