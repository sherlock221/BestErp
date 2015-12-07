package besterp.sherlock221b.com.besterp.db.model;

import besterp.sherlock221b.com.besterp.db.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "PRODUCT_STANDARD".
 */
public class ProductStandard {

    private Long id;
    private String standardName;
    private boolean isDelete;
    /** Not-null value. */
    private java.util.Date crtTime;
    /** Not-null value. */
    private java.util.Date updateTime;
    private Long productId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ProductStandardDao myDao;

    private Product product;
    private Long product__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public ProductStandard() {
    }

    public ProductStandard(Long id) {
        this.id = id;
    }

    public ProductStandard(Long id, String standardName, boolean isDelete, java.util.Date crtTime, java.util.Date updateTime, Long productId) {
        this.id = id;
        this.standardName = standardName;
        this.isDelete = isDelete;
        this.crtTime = crtTime;
        this.updateTime = updateTime;
        this.productId = productId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProductStandardDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
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