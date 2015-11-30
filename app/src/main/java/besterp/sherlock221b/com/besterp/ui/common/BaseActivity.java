package besterp.sherlock221b.com.besterp.ui.common;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.util.net.RequestManager;
import besterp.sherlock221b.com.besterp.view.ToolBarHelper;

/**
 * Created by sherlock on 15/11/28.
 */
public class BaseActivity extends AppCompatActivity {


    //顶部
    public Toolbar toolbar;
    public ToolBarHelper toolBarHelper;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;
    private ListView drawerMenu;


    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter drawerMenuArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        toolBarHelper = new ToolBarHelper(this, layoutResID);

        toolbar = toolBarHelper.getBaseToolBar();
        drawerMenu = toolBarHelper.getBaseDrawerMenu();
        drawerLayout = toolBarHelper.getBaseDrawerLayout();


         /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(toolbar);
        /*自定义的一些操作*/
        onCreateCustomToolBar(toolbar);

        initDrawerLayout();

        setContentView(toolBarHelper.getContentView());
    }

    /*自定义的一些操作*/
    private void onCreateCustomToolBar(Toolbar toolbar) {

        toolbar.setContentInsetsRelative(0, 0);

        //设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void initDrawerLayout() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        //设置菜单列表
        drawerMenuArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        drawerMenu.setAdapter(drawerMenuArrayAdapter);
    }


    /**
     * 设置statusbar 4.4
     * http://www.bubuko.com/infodetail-912443.html 沉浸式状态栏
     */
    private void setStatusBar() {
        //设置statubar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(R.color.accent_material_light);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity销毁时候取消请求
        RequestManager.cancelAll(this);
    }


    /**
     * actionbar 图标点击
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //设置标题
    public void setTitle(CharSequence text) {
        getSupportActionBar().setTitle(text);
    }

    public void setTitle(int resId) {
        getSupportActionBar().setTitle(resId);
    }


    //发起请求
    protected void executeRequest(Request<?> request) {
        RequestManager.addRequest(request, this);
    }

    //取消所有请求
    protected void cancelRequest() {
        RequestManager.cancelAll(this);
    }
}
