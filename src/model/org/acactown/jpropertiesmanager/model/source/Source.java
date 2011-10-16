package org.acactown.jpropertiesmanager.model.source;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.acactown.jpropertiesmanager.model.Id;

/**
 * 
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class Source extends Id {

    private String name;
    private String comment;
    public static final String OLD_EXTENTION = ".old";

    public Source(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Properties load(String file) {
        Properties properties = reload(file);
        if (properties != null) {
            Properties sortProperties = new Properties() {

                @Override
                public Set<Object> keySet() {
                    return Collections.unmodifiableSet(new TreeSet<Object>(super.keySet()));
                }
            };
            sortProperties.putAll(properties);
            properties = sortProperties;
        }

        return properties;
    }

    public abstract String getNameWithSource();

    public abstract boolean save(String file, Properties properties);

    protected abstract Properties reload(String file);
}
