package besterp.sherlock221b.com.besterp.db;


import besterp.sherlock221b.com.besterp.db.dao.CustomDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.service.CustomService;
import besterp.sherlock221b.com.besterp.service.ProductService;

/**
 * Created by sherlock on 15/12/2.
 */
public class DbUtil {


    private static ProductService productService;
    private static CustomService customService;


    private static ProductDao getProductDao() {
        return DbCore.getDaoSession().getProductDao();
    }

    private static CustomDao getCustomDao() {
        return DbCore.getDaoSession().getCustomDao();
    }

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

}
