package besterp.sherlock221b.com.besterp.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;
import java.util.List;
import java.util.Map;

import besterp.sherlock221b.com.besterp.cons.ResultCode;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.db.dao.SaleAccountDao;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import besterp.sherlock221b.com.besterp.util.GoodLogger;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/12/14.
 */
public class SaveProductTask extends LoadingTask<Integer> {

    private GoodLogger log = GoodLogger.sherlockLog();

    public SaveProductTask(Context context) {
        super(context);
    }

    @Override
    protected Integer doInBackground(Object... params) {
        //被取消了退出循环
        if(isCancelled()){return null; }

        Product product = (Product) params[0];
        List<String> productStandardTagList = (List<String>)params[1];



        try {

            //检测商品名称是否存在
            Product existProduct =DbUtil.getProductService().queryBuilder()
                    .where(ProductDao.Properties.ProductName.eq(product.getProductName())).unique();
            if(existProduct != null){
                return ResultCode.IS_EXISTS;
            }

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
            log.e(e);
            return ResultCode.DB_ERROR;
        }

    }



}


