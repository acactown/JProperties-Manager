package org.acactown.jpropertiesmanager.model.site;

import org.acactown.jpropertiesmanager.model.id.Id;
import org.acactown.jpropertiesmanager.model.property.PropertiesFile;
import java.util.List;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public final class Site extends Id {

    private String name;
    private String comment;
    private List<PropertiesFile> propertiesFiles;

    @Override
    public Site clone() {
        throw new UnsupportedOperationException( "Not supported." );
    }
}
