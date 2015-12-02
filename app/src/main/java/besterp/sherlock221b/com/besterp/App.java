package besterp.sherlock221b.com.besterp;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by sherlock on 15/11/29.
 */
public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext(){
        return appContext;
    }




}
