package com.an.textdocreader.database.generatedDAO;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.an.textdocreader.database.Area;
import com.an.textdocreader.database.Contact;
import com.an.textdocreader.database.Images;

import com.an.textdocreader.database.generatedDAO.AreaDao;
import com.an.textdocreader.database.generatedDAO.ContactDao;
import com.an.textdocreader.database.generatedDAO.ImagesDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig areaDaoConfig;
    private final DaoConfig contactDaoConfig;
    private final DaoConfig imagesDaoConfig;

    private final AreaDao areaDao;
    private final ContactDao contactDao;
    private final ImagesDao imagesDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        areaDaoConfig = daoConfigMap.get(AreaDao.class).clone();
        areaDaoConfig.initIdentityScope(type);

        contactDaoConfig = daoConfigMap.get(ContactDao.class).clone();
        contactDaoConfig.initIdentityScope(type);

        imagesDaoConfig = daoConfigMap.get(ImagesDao.class).clone();
        imagesDaoConfig.initIdentityScope(type);

        areaDao = new AreaDao(areaDaoConfig, this);
        contactDao = new ContactDao(contactDaoConfig, this);
        imagesDao = new ImagesDao(imagesDaoConfig, this);

        registerDao(Area.class, areaDao);
        registerDao(Contact.class, contactDao);
        registerDao(Images.class, imagesDao);
    }
    
    public void clear() {
        areaDaoConfig.clearIdentityScope();
        contactDaoConfig.clearIdentityScope();
        imagesDaoConfig.clearIdentityScope();
    }

    public AreaDao getAreaDao() {
        return areaDao;
    }

    public ContactDao getContactDao() {
        return contactDao;
    }

    public ImagesDao getImagesDao() {
        return imagesDao;
    }

}
