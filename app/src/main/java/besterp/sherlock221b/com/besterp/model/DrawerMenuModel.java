package besterp.sherlock221b.com.besterp.model;

import java.io.Serializable;

import besterp.sherlock221b.com.besterp.cons.MenuActivityEnum;

/**
 * Created by sherlock on 15/12/1.
 */
public class DrawerMenuModel implements Serializable {

    private int menuIcon;
    private String menuName;
    private MenuActivityEnum menuType;


    public MenuActivityEnum getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuActivityEnum menuType) {
        this.menuType = menuType;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
