package besterp.sherlock221b.com.besterp.cons;

/**
 * Created by sherlock on 15/12/7.
 */
public enum  CustomTypeEnum {
    //供货商
    SUPPLIER(2),
    //客户
    USER(1);
    private  int _type;
    CustomTypeEnum(int type) {
        this._type = type;
    }

    public int getType() {
        return _type;
    }
}
