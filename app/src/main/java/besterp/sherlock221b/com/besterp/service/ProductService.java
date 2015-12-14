package besterp.sherlock221b.com.besterp.service;

import android.database.Cursor;

import besterp.sherlock221b.com.besterp.db.DbCore;
import besterp.sherlock221b.com.besterp.db.model.Product;
import de.greenrobot.dao.AbstractDao;

/**
 * Created by sherlock on 15/12/2.
 */
public class ProductService extends BaseService<Product, Long> {
    public ProductService(AbstractDao dao) {
        super(dao);
    }

    public Cursor queryProduct() {
        String sql = "SELECT * FROM PRODUCT  ORDER BY  SORT_KEY";
        return DbCore.getDaoSession().getDatabase().rawQuery(sql,null);
    }
}
