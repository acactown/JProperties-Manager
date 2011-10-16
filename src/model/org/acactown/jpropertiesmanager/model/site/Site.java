package org.acactown.jpropertiesmanager.model.site;

import java.util.List;
import org.acactown.jpropertiesmanager.model.Id;
import org.acactown.jpropertiesmanager.model.property.PropertyFile;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class Site extends Id {

    private String name;
    private String comment;
    private List<PropertyFile> propertyFiles;

    @Override
    public Site clone(){
        throw new UnsupportedOperationException("Not supported.");
    } 
}
