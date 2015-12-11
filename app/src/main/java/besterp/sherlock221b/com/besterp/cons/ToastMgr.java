package besterp.sherlock221b.com.besterp.cons;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import besterp.sherlock221b.com.besterp.R;

/**
 * Created by sherlock on 15/12/10.
 */
public enum ToastMgr{
    builder;
    private View view;
    private TextView tv;
    private Toast toast;

    /**
     * 初始化Toast
     * @param context
     */
    public void init(Context context){
        view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
        tv = (TextView) view.findViewById(R.id.toast_textview);
        toast = new Toast(context);
        toast.setView(view);
    }
    /**
     * 显示Toast
     * @param content
     * @param duration Toast持续时间
     */
    public void display(CharSequence content , int duration){
        if (content.length()!=0) {
            tv.setText(content);
            toast.setDuration(duration);
            toast.setGravity(Gravity.BOTTOM, 0, 64);
            toast.show();
        }
    }
}