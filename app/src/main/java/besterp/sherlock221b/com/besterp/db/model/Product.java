package besterp.sherlock221b.com.besterp.db.model;

import java.util.List;
import besterp.sherlock221b.com.besterp.db.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import besterp.sherlock221b.com.besterp.db.dao.ProductDao;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "PRODUCT".
 */
public class Product {

    private Long id;
    /** Not-null value. */
    private String productName;
    private String productDesc;
    private String sortKey;
    private String productUnit;
    private Integer productUseCount;
    private Integer productPurchaseUseCount;
    private Integer productSaleUseCount;
    private boolean isDelete;
    /** Not-null value. */
    private java.util.Date crtTime;
    /** Not-null value. */
    private java.util.Date updateTime;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ProductDao myDao;

    private List<ProductStandard> standards;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String productName, String productDesc, String sortKey, String productUnit, Integer productUseCount, Integer productPurchaseUseCount, Integer productSaleUseCount, boolean isDelete, java.util.Date crtTime, java.util.Date updateTime) {
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.sortKey = sortKey;
        this.productUnit = productUnit;
        this.productUseCount = productUseCount;
        this.productPurchaseUseCount = productPurchaseUseCount;
        this.productSaleUseCount = productSaleUseCount;
        this.isDelete = isDelete;
        this.crtTime = crtTime;
        this.updateTime = updateTime;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProductDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getProductName() {
        return productName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getProductUseCount() {
        return productUseCount;
    }

    public void setProductUseCount(Integer productUseCount) {
        this.productUseCount = productUseCount;
    }

    public Integer getProductPurchaseUseCount() {
        return productPurchaseUseCount;
    }

    public void setProductPurchaseUseCount(Integer productPurchaseUseCount) {
        this.productPurchaseUseCount = productPurchaseUseCount;
    }

    public Integer getProductSaleUseCount() {
        return productSaleUseCount;
    }

    public void setProductSaleUseCount(Integer productSaleUseCount) {
        this.productSaleUseCount = productSaleUseCount;
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

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<ProductStandard> getStandards() {
        if (standards == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProductStandardDao targetDao = daoSession.getProductStandardDao();
            List<ProductStandard> standardsNew = targetDao._queryProduct_Standards(id);
            synchronized (this) {
                if(standards == null) {
                    standards = standardsNew;
                }
            }
        }
        return standards;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetStandards() {
        standards = null;
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
