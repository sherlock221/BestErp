package besterp.sherlock221b.com.besterp;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.Date;

import javax.xml.parsers.SAXParser;

import besterp.sherlock221b.com.besterp.cons.CustomTypeEnum;
import besterp.sherlock221b.com.besterp.db.DbCore;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Custom;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import besterp.sherlock221b.com.besterp.db.model.SaleAccount;
import besterp.sherlock221b.com.besterp.service.CustomService;
import besterp.sherlock221b.com.besterp.service.ProductService;
import besterp.sherlock221b.com.besterp.service.ProductStandardService;
import besterp.sherlock221b.com.besterp.service.SaleAccountService;
import besterp.sherlock221b.com.besterp.util.DateUtil;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }


    private ProductService productService;
    private CustomService customService;
    private ProductStandardService productStandardService;
    private SaleAccountService saleAccountService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DbCore.init(getContext());
        DbCore.enableQueryBuilderLog();

        productService = DbUtil.getProductService();
        productStandardService = DbUtil.getProductStandardService();
        customService =DbUtil.getCustomService();
        saleAccountService = DbUtil.getSaleAccountService();

    }


    public void testSave(){

        //清除数据
        productService.deleteAll();
        productStandardService.deleteAll();
        customService.deleteAll();
        saleAccountService.deleteAll();

        for(int i=0; i<30;i++){

            if(i % 2 == 0){
                //product
                Product product = new Product();
                product.setProductName("钳子"+i);
                product.setCrtTime(new Date());
                product.setIsDelete(false);
                product.setUpdateTime(new Date());
                product.setProductUnit("把");
                product.setSortKey("Q");
                product.setProductUseCount(0);
                product.setProductPurchaseUseCount(0);
                product.setProductSaleUseCount(0);
                productService.save(product);


                //规格
                ProductStandard standard = new ProductStandard();

                standard.setIsDelete(false);
                standard.setUpdateTime(new Date());
                standard.setCrtTime(new Date());
                standard.setStandardName("10寸");
                standard.setProductId(product.getId());

                productStandardService.saveOrUpdate(standard);

            }

            else{

                //product
                Product product = new Product();
                product.setProductName("手电-"+i);
                product.setCrtTime(new Date());
                product.setIsDelete(false);
                product.setUpdateTime(new Date());
                product.setProductUnit("把");
                product.setSortKey("S");
                product.setProductUseCount(0);
                product.setProductPurchaseUseCount(0);
                product.setProductSaleUseCount(0);
                productService.save(product);

                //规格
                ProductStandard standard = new ProductStandard();
                standard.setIsDelete(false);
                standard.setUpdateTime(new Date());
                standard.setCrtTime(new Date());
                standard.setStandardName("25");
                standard.setProductId(product.getId());
                productStandardService.saveOrUpdate(standard);
            }

        }



        //客户
        Custom custom = new Custom();
        custom.setCrtTime(new Date());
        custom.setUpdateTime(new Date());
        custom.setIsDelete(false);
        custom.setCustomName("龙湖");

        custom.setCustomAddress("陕西西安");
        custom.setCustomType(CustomTypeEnum.USER.getType());
        custom.setCustomConcat("18710987762");
        customService.save(custom);






        //加入销售清单
//        for( int i =0; i<20; i++){
//
//            SaleAccount saleAccount = new SaleAccount();
//            saleAccount.setCrtTime(new Date());
//            saleAccount.setUpdateTime(new Date());
//            saleAccount.setIsDelete(false);
//
//
//            //创建时间
//            saleAccount.setSaleDate(DateUtil.getDateYMDByString("current"));
//
//            saleAccount.setCustomId(custom.getId());
//            saleAccount.setDesc("这是销售清单");
//            saleAccount.setProductId(product.getId());
//            saleAccount.setStandardId(standard.getId());
//            saleAccount.setSaleAccountNumber(1 + i);
//            saleAccount.setSaleAccountPrice(3.2);
//            saleAccount.setSaleAccountTotal(saleAccount.getSaleAccountNumber() * saleAccount.getSaleAccountPrice());
//
//            saleAccountService.save(saleAccount);
//        }






//        for(int i=0; i<20; i++){
//            SaleAccount saleAccount = new SaleAccount()
//        }


    }
}