package besterp.sherlock221b.com.besterp.service;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import besterp.sherlock221b.com.besterp.db.DbCore;
import besterp.sherlock221b.com.besterp.db.DbUtil;
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

    public Cursor queryProduct(String likeProductName) {
        String sql = "SELECT * FROM PRODUCT AS P  WHERE P.PRODUCT_NAME like ?  ORDER BY  P.SORT_KEY";
        return DbCore.getDaoSession().getDatabase().rawQuery(sql,new String[]{"%"+likeProductName+"%"});
    }

    public  void deleteProduct(Product product) throws Exception{
        SQLiteDatabase db = DbCore.getDaoSession().getDatabase();
        db.beginTransaction();
        try {
            DbUtil.getProductStandardService().deleteProductStandard(product.getStandards());
            this.delete(product);
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            throw  ex;
        } finally {
            db.endTransaction();
        }
    }

}
