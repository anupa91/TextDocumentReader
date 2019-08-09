package com.an.textdocreader.common;

import android.app.Application;

import com.an.textdocreader.BuildConfig;
import com.an.textdocreader.database.generatedDAO.DaoMaster;
import com.an.textdocreader.database.generatedDAO.DaoSession;
import com.facebook.stetho.Stetho;

import org.greenrobot.greendao.database.Database;

public class textDocReaderApplication extends Application {

    private static final String TAG = textDocReaderApplication.class.getSimpleName();
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "text_doc_reader_db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        // Stetho for local DB view and network monitor
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);//chrome://inspect/#devices
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}