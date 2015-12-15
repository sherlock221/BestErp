package besterp.sherlock221b.com.besterp.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import besterp.sherlock221b.com.besterp.R;

/**
 * Created by sherlock on 15/12/14.
 */
public class LoadingDialog extends Dialog {

    private TextView tv;
    private LinearLayout linearLayout;

    public LoadingDialog(Context context) {
        super(context,R.style.loadingDialogStyle);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        tv = (TextView)findViewById(R.id.tv);
        tv.setText(R.string.loading_text);

        //点击窗口外不消失
        setCanceledOnTouchOutside(false);

        //设置透明度
        linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(210);
    }

    public  void setText(String text){
        tv.setText(text);
    }

    public String getText(){
        return String.valueOf(tv.getText());
    }


}
