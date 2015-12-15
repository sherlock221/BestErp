package besterp.sherlock221b.com.besterp.util;

import android.content.Context;

import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/12/14.
 */
public class DialogUtil {

    private static LoadingDialog dialog;


    public  static LoadingDialog getLoadingDialog (Context context){
        if(dialog == null)
            dialog = new LoadingDialog(context);
        return dialog;
    }




}
