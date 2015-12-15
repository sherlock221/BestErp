package besterp.sherlock221b.com.besterp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import besterp.sherlock221b.com.besterp.cons.ToastMgr;
import besterp.sherlock221b.com.besterp.db.DbCore;
import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/11/29.
 */
public class App extends Application {

    private static Context appContext;
    private static LoadingDialog dialog;


    @Override
    public void onCreate() {
        super.onCreate();

        //chrome调试
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        appContext = getApplicationContext();
        DbCore.init(this);

        //toast初始化
        ToastMgr.builder.init(getApplicationContext());


    }

    public static Context getContext(){
        return appContext;
    }



}
