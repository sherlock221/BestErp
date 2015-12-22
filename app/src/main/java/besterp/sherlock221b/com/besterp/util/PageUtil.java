package besterp.sherlock221b.com.besterp.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;


/**
 * Created by sherlock on 15/12/15.
 */
public class PageUtil {

    public static void forwardActivityWithParams(Activity activity, Class<?> clazz, String key, Parcelable value) {
        Intent intent = new Intent(activity, clazz);
        Bundle b = new Bundle();
        b.putParcelable(key, value);
        intent.putExtras(b);
        activity.startActivity(intent);
    }

    public static void forwardActivity(Activity activity, Class<?> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    public static void forwardActivityForResult(Activity activity, Class<?> clazz,int requestCode) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivityForResult(intent,requestCode);
    }

    public static  Parcelable getParcelable(String key,Intent intent){
        Bundle bundle = intent.getExtras();
        if(bundle == null){
            return null;
        }
        return bundle.getParcelable(key);
    }
}
