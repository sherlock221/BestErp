package besterp.sherlock221b.com.besterp.ui.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.volley.Request;

import besterp.sherlock221b.com.besterp.util.net.RequestManager;
import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/12/18.
 */
public class BaseActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //activity销毁时候取消请求
        RequestManager.cancelAll(this);
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            return doubleClick.onKeyDown(keyCode, event);
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * actionbar 图标点击
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //设置标题
    public void setTitle(CharSequence text) {
        getSupportActionBar().setTitle(text);
    }
    public void setTitle(int resId) {
        getSupportActionBar().setTitle(resId);
    }
    //发起请求
    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }
    //取消所有请求
    protected void cancelRequest() {
        RequestManager.cancelAll(this);
    }


}
