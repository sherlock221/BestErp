package besterp.sherlock221b.com.besterp.service;

import java.util.List;

import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import de.greenrobot.dao.AbstractDao;

/**
 * Created by sherlock on 15/12/2.
 */
public class ProductStandardService extends BaseService<ProductStandard,Long> {
    public ProductStandardService(AbstractDao dao) {
        super(dao);
    }


    public void deleteProductStandard(List<ProductStandard> productStandardList){
        this.delete(productStandardList);
    }

    public void deleteProductStandard(ProductStandard productStandard){
        this.delete(productStandard);
    }

    public  List<ProductStandard> getProductStandardList(long productId){
        return DbUtil.getProductStandardService().queryBuilder().where(ProductStandardDao.Properties.ProductId.eq(productId)).list();

    }
}
