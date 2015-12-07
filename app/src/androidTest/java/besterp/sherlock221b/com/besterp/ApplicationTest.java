package besterp.sherlock221b.com.besterp;

import android.app.Application;
import android.test.ApplicationTestCase;

import besterp.sherlock221b.com.besterp.db.DbCore;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.service.CustomService;
import besterp.sherlock221b.com.besterp.service.ProductService;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    private ProductService productService;
    private CustomService customService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DbCore.init(getContext());
        DbCore.enableQueryBuilderLog();

        productService = DbUtil.getProductService();
        customService =DbUtil.getCustomService();

    }


    public void testSave(){

        Product product = new Product();
        product.setProductName("钳子");
        product.setProductNumber(1);
        product.setProductPrice(3.2);
        product.setProductTotal(3.2);

        productService.save(product);

    }
}