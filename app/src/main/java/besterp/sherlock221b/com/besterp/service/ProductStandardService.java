package besterp.sherlock221b.com.besterp.service;

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
}
