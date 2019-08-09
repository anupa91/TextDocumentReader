package com.an.textdocreader.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * areaId -> id (Long) (PrimaryKey)
     * name -> name
     * enabled -> enabled
     */

    @Id
    private Long areaId;
    private String name;
    private boolean enabled;
    @Generated(hash = 451181364)
    public Area(Long areaId, String name, boolean enabled) {
        this.areaId = areaId;
        this.name = name;
        this.enabled = enabled;
    }
    @Generated(hash = 179626505)
    public Area() {
    }
    public Long getAreaId() {
        return this.areaId;
    }
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
