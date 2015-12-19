package besterp.sherlock221b.com.besterp.task;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;
import java.util.Map;

import besterp.sherlock221b.com.besterp.cons.ResultCode;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/12/14.
 */
public class SaveProductTask extends AsyncTask<Object,Void,Integer> {

    private LoadingDialog dialog;
    private  DataFinishListener dataFinishListener;


    public interface DataFinishListener {
        void dataFinishSuccessfully(int status);
    }

    public SaveProductTask(Activity activity) {
        this.dialog = new LoadingDialog(activity);
    }

    public void setFinishListener(DataFinishListener dataFinishListener) {
        this.dataFinishListener = dataFinishListener;
    }


    //第一个执行方法
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.dialog.show();
    }

    @Override
    protected Integer doInBackground(Object... params) {
        //被取消了退出循环
        if(isCancelled()){return null; }
        Product product = (Product) params[0];
        List<String> productStandardTagList = (List<String>)params[1];
        try {
            DbUtil.getProductService().save(product);
            for(String tag :  productStandardTagList ){
                ProductStandard productStandard = new ProductStandard();
                productStandard.setUpdateTime(new Date());
                productStandard.setCrtTime(new Date());
                productStandard.setIsDelete(false);
                productStandard.setStandardName(tag);
                productStandard.setProductId(product.getId());
                //存储
                DbUtil.getProductStandardService().saveOrUpdate(productStandard);
            }

            Thread.sleep(2000);
            return ResultCode.SUCCESS;
        }
        catch (Exception e){
            return ResultCode.DB_ERROR;
        }
        finally {

        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        //取消了退出
        if(isCancelled()) return;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        this.dialog.dismiss();
        //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
        if(dataFinishListener != null)
            dataFinishListener.dataFinishSuccessfully(integer);
        super.onPostExecute(integer);


    }


}


