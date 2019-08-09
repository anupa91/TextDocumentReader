package com.an.textdocreader.database.generatedDAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.an.textdocreader.database.Contact;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTACT".
*/
public class ContactDao extends AbstractDao<Contact, Long> {

    public static final String TABLENAME = "CONTACT";

    /**
     * Properties of entity Contact.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ContactId = new Property(0, Long.class, "contactId", true, "_id");
        public final static Property Attribute1 = new Property(1, String.class, "attribute1", false, "ATTRIBUTE1");
        public final static Property Attribute2 = new Property(2, String.class, "attribute2", false, "ATTRIBUTE2");
        public final static Property AreaName = new Property(3, String.class, "areaName", false, "AREA_NAME");
        public final static Property Enabled = new Property(4, boolean.class, "enabled", false, "ENABLED");
    }


    public ContactDao(DaoConfig config) {
        super(config);
    }
    
    public ContactDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONTACT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: contactId
                "\"ATTRIBUTE1\" TEXT," + // 1: attribute1
                "\"ATTRIBUTE2\" TEXT," + // 2: attribute2
                "\"AREA_NAME\" TEXT," + // 3: areaName
                "\"ENABLED\" INTEGER NOT NULL );"); // 4: enabled
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONTACT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long contactId = entity.getContactId();
        if (contactId != null) {
            stmt.bindLong(1, contactId);
        }
 
        String attribute1 = entity.getAttribute1();
        if (attribute1 != null) {
            stmt.bindString(2, attribute1);
        }
 
        String attribute2 = entity.getAttribute2();
        if (attribute2 != null) {
            stmt.bindString(3, attribute2);
        }
 
        String areaName = entity.getAreaName();
        if (areaName != null) {
            stmt.bindString(4, areaName);
        }
        stmt.bindLong(5, entity.getEnabled() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Contact entity) {
        stmt.clearBindings();
 
        Long contactId = entity.getContactId();
        if (contactId != null) {
            stmt.bindLong(1, contactId);
        }
 
        String attribute1 = entity.getAttribute1();
        if (attribute1 != null) {
            stmt.bindString(2, attribute1);
        }
 
        String attribute2 = entity.getAttribute2();
        if (attribute2 != null) {
            stmt.bindString(3, attribute2);
        }
 
        String areaName = entity.getAreaName();
        if (areaName != null) {
            stmt.bindString(4, areaName);
        }
        stmt.bindLong(5, entity.getEnabled() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Contact readEntity(Cursor cursor, int offset) {
        Contact entity = new Contact( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // contactId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // attribute1
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // attribute2
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // areaName
            cursor.getShort(offset + 4) != 0 // enabled
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Contact entity, int offset) {
        entity.setContactId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAttribute1(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAttribute2(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAreaName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEnabled(cursor.getShort(offset + 4) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Contact entity, long rowId) {
        entity.setContactId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Contact entity) {
        if(entity != null) {
            return entity.getContactId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Contact entity) {
        return entity.getContactId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
