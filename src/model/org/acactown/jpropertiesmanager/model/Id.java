package org.acactown.jpropertiesmanager.model;

import java.util.Random;

/**
 * Default Id for all Model Classes
 * 
 * @version 1.0
 * @author acactown - acactown@gmail.com
 */
public class Id {
    
    private final String id;

    public Id() {
        id = Integer.toString(
             new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE)
        );
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {   
            return false;
        }
        final Id other = (Id) obj;
        if ((this.id == null) ? (other.getId() != null) : !this.id.equals(other.getId())) {
            return false;
        } 
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}
