package besterp.sherlock221b.com.besterp.db.model;

import besterp.sherlock221b.com.besterp.db.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;
import besterp.sherlock221b.com.besterp.db.dao.StoreDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "STORE".
 */
public class Store {

    private Long id;
    private int storeNumber;
    private boolean isDelete;
    /** Not-null value. */
    private java.util.Date crtTime;
    /** Not-null value. */
    private java.util.Date updateTime;
    private Long productId;
    private Long standardId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient StoreDao myDao;

    private ProductStandard productStandard;
    private Long productStandard__resolvedKey;

    private Product product;
    private Long product__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Store() {
    }

    public Store(Long id) {
        this.id = id;
    }

    public Store(Long id, int storeNumber, boolean isDelete, java.util.Date crtTime, java.util.Date updateTime, Long productId, Long standardId) {
        this.id = id;
        this.storeNumber = storeNumber;
        this.isDelete = isDelete;
        this.crtTime = crtTime;
        this.updateTime = updateTime;
        this.productId = productId;
        this.standardId = standardId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStoreDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    /** Not-null value. */
    public java.util.Date getCrtTime() {
        return crtTime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCrtTime(java.util.Date crtTime) {
        this.crtTime = crtTime;
    }

    /** Not-null value. */
    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getStandardId() {
        return standardId;
    }

    public void setStandardId(Long standardId) {
        this.standardId = standardId;
    }

    /** To-one relationship, resolved on first access. */
    public ProductStandard getProductStandard() {
        Long __key = this.standardId;
        if (productStandard__resolvedKey == null || !productStandard__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProductStandardDao targetDao = daoSession.getProductStandardDao();
            ProductStandard productStandardNew = targetDao.load(__key);
            synchronized (this) {
                productStandard = productStandardNew;
            	productStandard__resolvedKey = __key;
            }
        }
        return productStandard;
    }

    public void setProductStandard(ProductStandard productStandard) {
        synchronized (this) {
            this.productStandard = productStandard;
            standardId = productStandard == null ? null : productStandard.getId();
            productStandard__resolvedKey = standardId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Product getProduct() {
        Long __key = this.productId;
        if (product__resolvedKey == null || !product__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProductDao targetDao = daoSession.getProductDao();
            Product productNew = targetDao.load(__key);
            synchronized (this) {
                product = productNew;
            	product__resolvedKey = __key;
            }
        }
        return product;
    }

    public void setProduct(Product product) {
        synchronized (this) {
            this.product = product;
            productId = product == null ? null : product.getId();
            product__resolvedKey = productId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}