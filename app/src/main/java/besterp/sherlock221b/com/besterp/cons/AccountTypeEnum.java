package besterp.sherlock221b.com.besterp.cons;

/**
 * Created by sherlock on 15/12/11.
 */
public enum AccountTypeEnum {

    SALE(1),PURCHASE(2);

    private  int _type;
     AccountTypeEnum(int type) {
        this._type = type;
    }
    public int getType() {
        return _type;
    }


    public static AccountTypeEnum getEnum(int value) {
        switch (value) {
            case 1 :
                return SALE;
            case 2:
                return PURCHASE;
            default:
                return null;
        }
    }
}
