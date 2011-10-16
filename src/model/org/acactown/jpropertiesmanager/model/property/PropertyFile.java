package org.acactown.jpropertiesmanager.model.property;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Map.Entry;
import java.util.Properties;
import org.acactown.jpropertiesmanager.model.Id;
import org.acactown.jpropertiesmanager.model.source.Source;

/**
 *
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public abstract class PropertyFile extends Id implements PropertyFileOperations, ClipboardOwner {

    private Properties properties;
    private final Source source;
    private final String path;
    private String name;

    public PropertyFile(Source source, String path, String name) throws Exception {
        this.source = source;
        this.path = path;
        this.name = name;
        properties = source.load(path);
        if (properties == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("The file [").append(path).append("]");
            builder.append(" from source [").append(source.getNameWithSource()).append("]");
            builder.append(" could not be loaded");
            throw new Exception(builder.toString());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public Properties getProperties() {
        return properties;
    }

    public Source getSource() {
        return source;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //Nothing to do!
    }

    @Override
    public boolean copyToClipboard() {
        if (canCopyToClipboard()) {
            StringBuilder builder = new StringBuilder();
            for (Entry<Object, Object> entry : properties.entrySet()) {
                builder.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
            StringSelection stringSelection = new StringSelection(builder.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, this);

            return true;
        } else {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    @Override
    public boolean load() {
        if (canLoad()) {
            Properties tmpProperties = source.load(path);
            if (tmpProperties == null) {
                return false;
            } else {
                properties = tmpProperties;
                return true;
            }
        } else {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    @Override
    public boolean save() {
        if (canSave()) {
            return source.save(path, properties);
        } else {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    @Override
    public void edit(Properties properties) {
        if (canEdit()) {
            this.properties = properties;
        }
    }
}
