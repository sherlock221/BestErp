package besterp.sherlock221b.com.besterp.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/12/14.
 */
public class DialogUtil {



    public  static void simpleConfirm(Context context,String title,String message, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title); //设置标题
        builder.setMessage(message); //设置内容
        //设置确定按钮
        builder.setPositiveButton("确定", onClickListener);
        //设置取消按钮
        builder.setNegativeButton("取消", onClickListener);
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }




}
