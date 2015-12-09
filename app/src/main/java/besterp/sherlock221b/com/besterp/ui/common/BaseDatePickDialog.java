package besterp.sherlock221b.com.besterp.ui.common;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * Created by sherlock on 15/12/10.
 */
public class BaseDatePickDialog extends DatePickerDialog {


    public BaseDatePickDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public BaseDatePickDialog(Context context, int theme, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, listener, year, monthOfYear, dayOfMonth);
    }

    @Override
    protected void onStop() {
//        super.onStop();
        //4.3系统以后 onDateSet 回调用2次  屏蔽掉stop 已解决这个问题
    }
}
