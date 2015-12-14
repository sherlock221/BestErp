package besterp.sherlock221b.com.besterp.db.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Index;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by sherlock on 15/12/2.
 * 生成数据库表结构
 */
public class GreenDaoGenerator {

    public static void main(String[] args) throws Exception {

        //两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(12, "besterp.sherlock221b.com.besterp.db.model");
        schema.setDefaultJavaPackageDao("besterp.sherlock221b.com.besterp.db.dao");

        schema.enableKeepSectionsByDefault();
        //schema.enableActiveEntitiesByDefault();
        //ActiveRecord
        addEntity(schema);
        new DaoGenerator().generateAll(schema, "./app/src/main/java");
    }

    private static void addEntity(Schema schema) {

        //商品表
        Entity product = schema.addEntity("Product");
        product.addIdProperty().primaryKey();
        product.addStringProperty("productName").notNull();
        product.addStringProperty("productDesc");

        //sortKey 字段排序
        Property sortKeyProperty = product.addStringProperty("sortKey").getProperty();

        //单位
        product.addStringProperty("productUnit");

        //商品被添加次数
        product.addIntProperty("productUseCount");
        //进货添加次数
        product.addIntProperty("productPurchaseUseCount");
        //销售添加次数
        product.addIntProperty("productSaleUseCount");

        product.addBooleanProperty("isDelete").notNull();
        product.addDateProperty("crtTime").notNull();
        product.addDateProperty("updateTime").notNull();


        //添加索引加速查询
        Index productIndex = new Index();
        productIndex.addProperty(sortKeyProperty);
        product.addIndex(productIndex);


        //规格表
        Entity standard = schema.addEntity("ProductStandard");
        standard.addIdProperty().primaryKey();
        standard.addStringProperty("standardName");
        standard.addBooleanProperty("isDelete").notNull();
        standard.addDateProperty("crtTime").notNull();
        standard.addDateProperty("updateTime").notNull();


        //一个商品多个规格  1对多
        Property productIdPK = standard.addLongProperty("productId").getProperty();
        product.addToMany(standard, productIdPK).setName("standards");
        standard.addToOne(product, productIdPK);


        //客户表
        Entity custom = schema.addEntity("Custom");
        custom.addIdProperty().primaryKey();
        custom.addStringProperty("customName").notNull();
        custom.addStringProperty("customAddress");
        custom.addStringProperty("customConcat");
        custom.addStringProperty("customIcon");
        //哪一类客户
        custom.addIntProperty("customType").notNull();


        custom.addBooleanProperty("isDelete").notNull();
        custom.addDateProperty("crtTime").notNull();
        custom.addDateProperty("updateTime").notNull();


        //库存
        Entity store = schema.addEntity("Store");
        store.addIdProperty().primaryKey();
        store.addIntProperty("storeNumber").notNull();

        store.addBooleanProperty("isDelete").notNull();
        store.addDateProperty("crtTime").notNull();
        store.addDateProperty("updateTime").notNull();

        //外键
        Property storeProductIdFK = store.addLongProperty("productId").getProperty();
        Property storeStandardIdFK = store.addLongProperty("standardId").getProperty();
        store.addToOne(standard, storeStandardIdFK);
        store.addToOne(product, storeProductIdFK);


        //销售清单
        Entity saleAccount = schema.addEntity("SaleAccount");
        saleAccount.addIdProperty().primaryKey();
        saleAccount.addIntProperty("saleAccountNumber").notNull();
        saleAccount.addDoubleProperty("saleAccountPrice").notNull();
        saleAccount.addDoubleProperty("saleAccountTotal").notNull();
        saleAccount.addDateProperty("saleDate").notNull();
        saleAccount.addStringProperty("desc");

        saleAccount.addBooleanProperty("isDelete").notNull();
        saleAccount.addDateProperty("crtTime").notNull();
        saleAccount.addDateProperty("updateTime").notNull();


        //外键
        Property scaleProductIdFK = saleAccount.addLongProperty("productId").getProperty();
        Property scaleStandardIdFK = saleAccount.addLongProperty("standardId").getProperty();
        Property scaleCustomIdFK = saleAccount.addLongProperty("customId").getProperty();


        saleAccount.addToOne(product, scaleProductIdFK);
        saleAccount.addToOne(standard, scaleStandardIdFK);
        saleAccount.addToOne(custom, scaleCustomIdFK);


        //进货清单

        Entity purchaserAccount = schema.addEntity("PurchaserAccount");
        purchaserAccount.addIdProperty().primaryKey();
        purchaserAccount.addIntProperty("purchaserAccountNumber").notNull();
        purchaserAccount.addDoubleProperty("purchaserAccountPrice").notNull();
        purchaserAccount.addDoubleProperty("purchaserAccountTotal").notNull();
        purchaserAccount.addDateProperty("purchaserDate").notNull();

        purchaserAccount.addStringProperty("desc");


        purchaserAccount.addBooleanProperty("isDelete").notNull();
        purchaserAccount.addDateProperty("crtTime").notNull();
        purchaserAccount.addDateProperty("updateTime").notNull();


        //外键
        Property purchaserProductIdFK = purchaserAccount.addLongProperty("productId").getProperty();
        Property purchaserStandardIdFK = purchaserAccount.addLongProperty("standardId").getProperty();
        Property purchaserCustomIdFK = purchaserAccount.addLongProperty("customId").getProperty();

        purchaserAccount.addToOne(product, purchaserProductIdFK);
        purchaserAccount.addToOne(standard, purchaserStandardIdFK);
        purchaserAccount.addToOne(custom, purchaserCustomIdFK);


    }

}
