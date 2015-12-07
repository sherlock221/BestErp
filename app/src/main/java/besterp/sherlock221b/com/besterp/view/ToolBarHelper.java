package besterp.sherlock221b.com.besterp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.util.ToastUtils;

/**
 * Created by sherlock on 15/11/29.
 */
public class ToolBarHelper {

    /*上下文，创建view的时候需要用到*/
    private Context mContext;

    /*base view*/
    private FrameLayout mContentView;

    /*用户定义的view*/
    private View mUserView;

    /*toolbar*/
    private Toolbar baseToolBar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    private DrawerLayout baseDrawerLayout;
    private ListView baseDrawerMenu;


    private LinearLayout baseMainContent;



    /*
    * 两个属性
    * 1、toolbar是否悬浮在窗口之上
    * 2、toolbar的高度获取
    * */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };


    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化整个内容*/
        initContentView();
        /*初始化view */
        initToView();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
    }


    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);

    }

    private void initToView() {
        View view = mInflater.inflate(R.layout.base_main, mContentView);
        baseToolBar = (Toolbar) view.findViewById(R.id.base_toolbar);
        baseDrawerLayout = (DrawerLayout) view.findViewById(R.id.base_drawer_layout);
        baseDrawerMenu = (ListView) view.findViewById(R.id.base_drawer_menu);
        baseMainContent = (LinearLayout) view.findViewById(R.id.base_main_content);
    }


    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);

        /*获取主题中定义的悬浮标志*/
        boolean overly = typedArray.getBoolean(0, false);
        /*获取主题中定义的toolbar的高度*/
        int toolBarSize = (int) typedArray.getDimension(1, (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();

        /*如果是悬浮状态，则不需要设置间距*/
        params.topMargin = overly ? 0 : toolBarSize;
        ToastUtils.showShort(String.format("%d", toolBarSize));

        baseMainContent.addView(mUserView, params);
    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getBaseToolBar() {
        return baseToolBar;
    }

    public DrawerLayout getBaseDrawerLayout() {
        return baseDrawerLayout;
    }

    public ListView getBaseDrawerMenu() {
        return baseDrawerMenu;
    }


}
