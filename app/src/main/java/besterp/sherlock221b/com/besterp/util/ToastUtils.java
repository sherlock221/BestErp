package besterp.sherlock221b.com.besterp.util;

import android.content.Context;
import android.widget.Toast;

import besterp.sherlock221b.com.besterp.App;


/**
 * Created by storm on 14-4-19.
 * Toast封装
 */
public class ToastUtils {
    private ToastUtils() {
    }


    private static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    private static void show(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }



    public static void show(int resId,int duration){
        Toast.makeText(App.getContext(), resId, duration).show();
    }

    public static void show(String message,int duration){
        Toast.makeText(App.getContext(), message, duration).show();
    }


    public static void showShort(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_LONG).show();
    }
}
