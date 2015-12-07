package besterp.sherlock221b.com.besterp.service;

import besterp.sherlock221b.com.besterp.db.model.Product;
import de.greenrobot.dao.AbstractDao;

/**
 * Created by sherlock on 15/12/2.
 */
public class ProductService extends BaseService<Product,Long> {
    public ProductService(AbstractDao dao) {
        super(dao);
    }
}
