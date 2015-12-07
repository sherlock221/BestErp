package besterp.sherlock221b.com.besterp.db.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "CUSTOM".
 */
public class Custom {

    private Long id;
    /** Not-null value. */
    private String customName;
    private String customAddress;
    private String customConcat;
    private String customIcon;
    private int customType;
    private boolean isDelete;
    /** Not-null value. */
    private java.util.Date crtTime;
    /** Not-null value. */
    private java.util.Date updateTime;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Custom() {
    }

    public Custom(Long id) {
        this.id = id;
    }

    public Custom(Long id, String customName, String customAddress, String customConcat, String customIcon, int customType, boolean isDelete, java.util.Date crtTime, java.util.Date updateTime) {
        this.id = id;
        this.customName = customName;
        this.customAddress = customAddress;
        this.customConcat = customConcat;
        this.customIcon = customIcon;
        this.customType = customType;
        this.isDelete = isDelete;
        this.crtTime = crtTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getCustomName() {
        return customName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomAddress() {
        return customAddress;
    }

    public void setCustomAddress(String customAddress) {
        this.customAddress = customAddress;
    }

    public String getCustomConcat() {
        return customConcat;
    }

    public void setCustomConcat(String customConcat) {
        this.customConcat = customConcat;
    }

    public String getCustomIcon() {
        return customIcon;
    }

    public void setCustomIcon(String customIcon) {
        this.customIcon = customIcon;
    }

    public int getCustomType() {
        return customType;
    }

    public void setCustomType(int customType) {
        this.customType = customType;
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

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
