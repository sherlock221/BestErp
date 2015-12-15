package besterp.sherlock221b.com.besterp.task;

import android.database.Cursor;
import android.os.AsyncTask;

import java.util.List;

import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.util.ToastUtils;

/**
 * Created by sherlock on 15/12/14.
 */
public class SearchProductTask extends AsyncTask<String,Integer,Cursor> {


    private  DataFinishListener dataFinishListener;

    public void setFinishListener(DataFinishListener dataFinishListener) {
        this.dataFinishListener = dataFinishListener;
    }


    //第一个执行方法
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    //第二个执行方法
    @Override
    protected Cursor doInBackground(String... params) {
        //被取消了退出循环
        if(isCancelled()){return null; }

        return DbUtil.getProductService().queryProduct(params[0]);
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        //取消了退出
        if(isCancelled()) return;
        //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
        //但是这里取到的是一个数组,所以要用progesss[0]来取值
        ToastUtils.toast(String.valueOf(values[0]));
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(Cursor products) {
        //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
        if(dataFinishListener != null)
            dataFinishListener.dataFinishSuccessfully(products);

        super.onPostExecute(products);

    }

    public interface DataFinishListener {
        void dataFinishSuccessfully(Cursor cursor);
    }

}


