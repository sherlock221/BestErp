package besterp.sherlock221b.com.besterp.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import besterp.sherlock221b.com.besterp.R;
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

    public  static void popupItem(Context context,String[] arrs,DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setItems(arrs, onClickListener);
        //参数都设置完成了，创建并显示出来
        builder.create().show();

    }

    public static void editTextDialog(Context context,String editTextStr,String positiveBtnText,  final EditTextDialogListener editTextDialogListener){
        View updateDialogView = LayoutInflater.from(context).inflate(R.layout.edit_text_dialog,null);

       final TextView textView;
        if(!ValidateUtil.isEmpty(editTextStr)){
             textView = (TextView) updateDialogView.findViewById(R.id.edit_text);
            textView.setText(editTextStr);
            textView.requestFocus();
        }
        else{
            textView = null;
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setView(updateDialogView);

        DialogInterface.OnClickListener  dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = "";
                if(textView != null){
                    text =  textView.getText().toString();
                }
                editTextDialogListener.onResult(dialog, which,text);
            }
        };

            //设置确定按钮
        builder.setPositiveButton(positiveBtnText,dialogClickListener);

        //设置取消按钮
        builder.setNegativeButton("取消", dialogClickListener);
        builder.show();
    }


    public interface  EditTextDialogListener{
        void onResult(DialogInterface dialog, int which,String text);
    }


}

