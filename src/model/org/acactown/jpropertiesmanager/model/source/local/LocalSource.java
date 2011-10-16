package org.acactown.jpropertiesmanager.model.source.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.acactown.jpropertiesmanager.model.source.Source;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class LocalSource extends Source {

    private String path;
    private static final Logger LOG = Logger.getLogger(LocalSource.class);

    public LocalSource(String path, String name, String comment) {
        super(name, comment);
        this.path = path;
    }

    public String getPath() {        
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Properties reload(String file) {
        LOG.debug("Loading properties from file [" + file + "]");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(file));
        } catch (Exception ex) {
            LOG.error("Error loading properties from file [" + file + "]", ex);
            return null;
        }
        
        return props;
    }

    @Override
    public boolean save(String file, Properties properties) {
        LOG.debug("Saving properties in file [" + file + "]");
        try {
            if (!new File(file).exists()) {
                properties.store(new FileOutputStream(file), null);
            }

            InputStream in = new FileInputStream(file);
            OutputStream out = new FileOutputStream(file + OLD_EXTENTION);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            properties.store(new FileOutputStream(file), null);
            return true;
        } catch (Exception ex) {
            LOG.error("Error saving properties in file [" + file + "]", ex);
            return false;
        }
    }

    @Override
    public String getNameWithSource() {
        return "Local - " + getName();
    }
}
