package com.an.textdocreader.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * contactId -> id (Long) (PrimaryKey)
     * attribute1 -> attribute1
     * attribute2 -> attribute2
     * enabled -> enabled
     *
     * areaName -> (Usage for localDB)
     */

    @Id
    private Long contactId;
    private String attribute1, attribute2, areaName;
    private boolean enabled;
    @Generated(hash = 1972988477)
    public Contact(Long contactId, String attribute1, String attribute2,
            String areaName, boolean enabled) {
        this.contactId = contactId;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.areaName = areaName;
        this.enabled = enabled;
    }
    @Generated(hash = 672515148)
    public Contact() {
    }
    public Long getContactId() {
        return this.contactId;
    }
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
    public String getAttribute1() {
        return this.attribute1;
    }
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }
    public String getAttribute2() {
        return this.attribute2;
    }
    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
    public String getAreaName() {
        return this.areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public boolean getEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
