package com.an.textdocreader.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * imageId -> id (Long) (PrimaryKey)
     * imagePath -> image
     *
     * contactId -> (Usage for localDB)
     */

    @Id
    private Long imageId;
    private String imagePath;

    private Long contactId;

    @Generated(hash = 92564449)
    public Images(Long imageId, String imagePath, Long contactId) {
        this.imageId = imageId;
        this.imagePath = imagePath;
        this.contactId = contactId;
    }

    @Generated(hash = 1787213703)
    public Images() {
    }

    public Long getImageId() {
        return this.imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getContactId() {
        return this.contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
