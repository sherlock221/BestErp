package besterp.sherlock221b.com.besterp.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;
import besterp.sherlock221b.com.besterp.db.dao.CustomDao;
import besterp.sherlock221b.com.besterp.db.dao.StoreDao;
import besterp.sherlock221b.com.besterp.db.dao.ScaleAccountDao;
import besterp.sherlock221b.com.besterp.db.dao.PurchaserAccountDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 6): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 6;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        ProductDao.createTable(db, ifNotExists);
        ProductStandardDao.createTable(db, ifNotExists);
        CustomDao.createTable(db, ifNotExists);
        StoreDao.createTable(db, ifNotExists);
        ScaleAccountDao.createTable(db, ifNotExists);
        PurchaserAccountDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        ProductDao.dropTable(db, ifExists);
        ProductStandardDao.dropTable(db, ifExists);
        CustomDao.dropTable(db, ifExists);
        StoreDao.dropTable(db, ifExists);
        ScaleAccountDao.dropTable(db, ifExists);
        PurchaserAccountDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(ProductDao.class);
        registerDaoClass(ProductStandardDao.class);
        registerDaoClass(CustomDao.class);
        registerDaoClass(StoreDao.class);
        registerDaoClass(ScaleAccountDao.class);
        registerDaoClass(PurchaserAccountDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
