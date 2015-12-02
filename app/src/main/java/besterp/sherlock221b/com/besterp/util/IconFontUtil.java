package besterp.sherlock221b.com.besterp.util;

import android.graphics.Typeface;

import besterp.sherlock221b.com.besterp.App;

/**
 * Created by sherlock on 15/12/1.
 */
public class IconFontUtil {

    private static Typeface iconfont;
    public static Typeface getIconFontTypeFace() {
        if (iconfont == null) {
            iconfont = Typeface.createFromAsset(App.getContext().getAssets(), "iconfont/iconfont.ttf");
        }
        return iconfont;
    }
}
