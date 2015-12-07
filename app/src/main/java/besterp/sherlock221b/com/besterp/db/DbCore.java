package besterp.sherlock221b.com.besterp.db;

import android.content.Context;

import besterp.sherlock221b.com.besterp.db.dao.DaoMaster;
import besterp.sherlock221b.com.besterp.db.dao.DaoSession;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by sherlock on 15/12/2.
 */
public class DbCore {

    private static final String DEFAULT_DB_NAME = "default.db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static Context mContext;
    private static String DB_NAME;


    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }


    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }

    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());

        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

}
