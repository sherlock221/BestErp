package besterp.sherlock221b.com.besterp.service;

import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.SaleAccount;
import de.greenrobot.dao.AbstractDao;

/**
 * Created by sherlock on 15/12/2.
 */
public class SaleAccountService extends BaseService<SaleAccount,Long> {
    public SaleAccountService(AbstractDao dao) {
        super(dao);
    }
}
