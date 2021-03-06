package besterp.sherlock221b.com.besterp.db.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import besterp.sherlock221b.com.besterp.db.model.Custom;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;

import besterp.sherlock221b.com.besterp.db.model.PurchaserAccount;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PURCHASER_ACCOUNT".
*/
public class PurchaserAccountDao extends AbstractDao<PurchaserAccount, Long> {

    public static final String TABLENAME = "PURCHASER_ACCOUNT";

    /**
     * Properties of entity PurchaserAccount.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PurchaserAccountNumber = new Property(1, int.class, "purchaserAccountNumber", false, "PURCHASER_ACCOUNT_NUMBER");
        public final static Property PurchaserAccountPrice = new Property(2, double.class, "purchaserAccountPrice", false, "PURCHASER_ACCOUNT_PRICE");
        public final static Property PurchaserAccountTotal = new Property(3, double.class, "purchaserAccountTotal", false, "PURCHASER_ACCOUNT_TOTAL");
        public final static Property PurchaserDate = new Property(4, java.util.Date.class, "purchaserDate", false, "PURCHASER_DATE");
        public final static Property Desc = new Property(5, String.class, "desc", false, "DESC");
        public final static Property IsDelete = new Property(6, boolean.class, "isDelete", false, "IS_DELETE");
        public final static Property CrtTime = new Property(7, java.util.Date.class, "crtTime", false, "CRT_TIME");
        public final static Property UpdateTime = new Property(8, java.util.Date.class, "updateTime", false, "UPDATE_TIME");
        public final static Property ProductId = new Property(9, Long.class, "productId", false, "PRODUCT_ID");
        public final static Property StandardId = new Property(10, Long.class, "standardId", false, "STANDARD_ID");
        public final static Property CustomId = new Property(11, Long.class, "customId", false, "CUSTOM_ID");
    };

    private DaoSession daoSession;


    public PurchaserAccountDao(DaoConfig config) {
        super(config);
    }
    
    public PurchaserAccountDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PURCHASER_ACCOUNT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PURCHASER_ACCOUNT_NUMBER\" INTEGER NOT NULL ," + // 1: purchaserAccountNumber
                "\"PURCHASER_ACCOUNT_PRICE\" REAL NOT NULL ," + // 2: purchaserAccountPrice
                "\"PURCHASER_ACCOUNT_TOTAL\" REAL NOT NULL ," + // 3: purchaserAccountTotal
                "\"PURCHASER_DATE\" INTEGER NOT NULL ," + // 4: purchaserDate
                "\"DESC\" TEXT," + // 5: desc
                "\"IS_DELETE\" INTEGER NOT NULL ," + // 6: isDelete
                "\"CRT_TIME\" INTEGER NOT NULL ," + // 7: crtTime
                "\"UPDATE_TIME\" INTEGER NOT NULL ," + // 8: updateTime
                "\"PRODUCT_ID\" INTEGER," + // 9: productId
                "\"STANDARD_ID\" INTEGER," + // 10: standardId
                "\"CUSTOM_ID\" INTEGER);"); // 11: customId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PURCHASER_ACCOUNT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PurchaserAccount entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPurchaserAccountNumber());
        stmt.bindDouble(3, entity.getPurchaserAccountPrice());
        stmt.bindDouble(4, entity.getPurchaserAccountTotal());
        stmt.bindLong(5, entity.getPurchaserDate().getTime());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(6, desc);
        }
        stmt.bindLong(7, entity.getIsDelete() ? 1L: 0L);
        stmt.bindLong(8, entity.getCrtTime().getTime());
        stmt.bindLong(9, entity.getUpdateTime().getTime());
 
        Long productId = entity.getProductId();
        if (productId != null) {
            stmt.bindLong(10, productId);
        }
 
        Long standardId = entity.getStandardId();
        if (standardId != null) {
            stmt.bindLong(11, standardId);
        }
 
        Long customId = entity.getCustomId();
        if (customId != null) {
            stmt.bindLong(12, customId);
        }
    }

    @Override
    protected void attachEntity(PurchaserAccount entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PurchaserAccount readEntity(Cursor cursor, int offset) {
        PurchaserAccount entity = new PurchaserAccount( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // purchaserAccountNumber
            cursor.getDouble(offset + 2), // purchaserAccountPrice
            cursor.getDouble(offset + 3), // purchaserAccountTotal
            new java.util.Date(cursor.getLong(offset + 4)), // purchaserDate
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // desc
            cursor.getShort(offset + 6) != 0, // isDelete
            new java.util.Date(cursor.getLong(offset + 7)), // crtTime
            new java.util.Date(cursor.getLong(offset + 8)), // updateTime
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // productId
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // standardId
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11) // customId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PurchaserAccount entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPurchaserAccountNumber(cursor.getInt(offset + 1));
        entity.setPurchaserAccountPrice(cursor.getDouble(offset + 2));
        entity.setPurchaserAccountTotal(cursor.getDouble(offset + 3));
        entity.setPurchaserDate(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setDesc(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIsDelete(cursor.getShort(offset + 6) != 0);
        entity.setCrtTime(new java.util.Date(cursor.getLong(offset + 7)));
        entity.setUpdateTime(new java.util.Date(cursor.getLong(offset + 8)));
        entity.setProductId(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setStandardId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setCustomId(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PurchaserAccount entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PurchaserAccount entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getProductDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getProductStandardDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getCustomDao().getAllColumns());
            builder.append(" FROM PURCHASER_ACCOUNT T");
            builder.append(" LEFT JOIN PRODUCT T0 ON T.\"PRODUCT_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN PRODUCT_STANDARD T1 ON T.\"STANDARD_ID\"=T1.\"_id\"");
            builder.append(" LEFT JOIN CUSTOM T2 ON T.\"CUSTOM_ID\"=T2.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected PurchaserAccount loadCurrentDeep(Cursor cursor, boolean lock) {
        PurchaserAccount entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Product product = loadCurrentOther(daoSession.getProductDao(), cursor, offset);
        entity.setProduct(product);
        offset += daoSession.getProductDao().getAllColumns().length;

        ProductStandard productStandard = loadCurrentOther(daoSession.getProductStandardDao(), cursor, offset);
        entity.setProductStandard(productStandard);
        offset += daoSession.getProductStandardDao().getAllColumns().length;

        Custom custom = loadCurrentOther(daoSession.getCustomDao(), cursor, offset);
        entity.setCustom(custom);

        return entity;    
    }

    public PurchaserAccount loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<PurchaserAccount> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<PurchaserAccount> list = new ArrayList<PurchaserAccount>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<PurchaserAccount> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<PurchaserAccount> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
