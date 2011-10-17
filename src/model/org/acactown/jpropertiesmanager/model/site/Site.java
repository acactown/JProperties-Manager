package org.acactown.jpropertiesmanager.model.site;

import org.acactown.jpropertiesmanager.model.Id;
import org.acactown.jpropertiesmanager.model.property.PropertyFile;
import java.util.List;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class Site extends Id {

    private String name;
    private String comment;
    private List<PropertyFile> propertyFiles;

    @Override
    public Site clone() {
        throw new UnsupportedOperationException( "Not supported." );
    }
}
