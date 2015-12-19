package besterp.sherlock221b.com.besterp.db;


import besterp.sherlock221b.com.besterp.db.dao.CustomDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;
import besterp.sherlock221b.com.besterp.db.dao.SaleAccountDao;
import besterp.sherlock221b.com.besterp.db.model.SaleAccount;
import besterp.sherlock221b.com.besterp.service.CustomService;
import besterp.sherlock221b.com.besterp.service.ProductService;
import besterp.sherlock221b.com.besterp.service.ProductStandardService;
import besterp.sherlock221b.com.besterp.service.SaleAccountService;

/**
 * Created by sherlock on 15/12/2.
 */
public class DbUtil {


    private static ProductService productService;
    private static ProductStandardService productStandardService;
    private static CustomService customService;
    private static SaleAccountService saleAccountService;



    //dao
    private static ProductDao getProductDao() {
        return DbCore.getDaoSession().getProductDao();
    }

    private static CustomDao getCustomDao() {
        return DbCore.getDaoSession().getCustomDao();
    }

    private static SaleAccountDao getSaleAccountDao() {
        return DbCore.getDaoSession().getSaleAccountDao();
    }

    private static ProductStandardDao getProductStandardDao() {
        return DbCore.getDaoSession().getProductStandardDao();
    }


    //service
    public static ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService(getProductDao());
        }
        return productService;
    }





    public static CustomService getCustomService() {
        if (customService == null) {
            customService = new CustomService(getCustomDao());
        }
        return customService;
    }

    public static SaleAccountService getSaleAccountService() {
        if (saleAccountService == null) {
            saleAccountService = new SaleAccountService(getSaleAccountDao());
        }
        return saleAccountService;
    }

    //service
    public static ProductStandardService getProductStandardService() {
        if (productStandardService == null) {
            productStandardService = new ProductStandardService(getProductStandardDao());
        }
        return productStandardService;
    }


}
