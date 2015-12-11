package besterp.sherlock221b.com.besterp.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import besterp.sherlock221b.com.besterp.App;
import besterp.sherlock221b.com.besterp.cons.ToastMgr;


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


    public static void show(int resId, int duration) {
        Toast.makeText(App.getContext(), resId, duration).show();
    }

    public static void show(String message, int duration) {
        Toast.makeText(App.getContext(), message, duration).show();
    }


    public static void showShort(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    //2秒
    public static void showShort(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //3.5秒
    public static void showLong(int resId) {
        Toast.makeText(App.getContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(App.getContext(), message, Toast.LENGTH_LONG).show();
    }


    /**
     * 自定义toast
     * @param content
     */
    public static void  toast(String content){
        if (content == null) {
            return;
        }else {
            ToastMgr.builder.display(content, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 显示toast，自己定义显示长短。
     * param1:activity  传入context
     * param2:word   我们需要显示的toast的内容
     * param3:time length  long类型，我们传入的时间长度（如500）
     */
//    public static void showToast(final String word, final long time) {
//        activity.runOnUiThread(new Runnable() {
//            public void run() {
//                final Toast toast = Toast.makeText(App.getContext(), word, Toast.LENGTH_LONG);
//                toast.show();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        toast.cancel();
//                    }
//                }, time);
//            }
//        });
//    }
}
