package besterp.sherlock221b.com.besterp.ui.common;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.cons.MenuActivityEnum;
import besterp.sherlock221b.com.besterp.model.DrawerMenuModel;
import besterp.sherlock221b.com.besterp.ui.activity.account.AccountSalesActivity;
import besterp.sherlock221b.com.besterp.ui.activity.CustomActivity;
import besterp.sherlock221b.com.besterp.ui.activity.product.ProductActivity;
import besterp.sherlock221b.com.besterp.ui.activity.SearchProductActivity;
import besterp.sherlock221b.com.besterp.ui.adapter.DrawerMenuAdapter;
import besterp.sherlock221b.com.besterp.util.DoubleClickExitHelper;
import besterp.sherlock221b.com.besterp.util.GsonUtil;
import besterp.sherlock221b.com.besterp.util.net.RequestManager;
import besterp.sherlock221b.com.besterp.view.LoadingDialog;
import besterp.sherlock221b.com.besterp.view.ToolBarHelper;

/**
 * Created by sherlock on 15/11/28.
 */
public class DrawerActivity extends BaseActivity {

    //顶部
    public Toolbar toolbar;
    public ToolBarHelper toolBarHelper;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;
    private ListView drawerMenu;

    DoubleClickExitHelper doubleClick = new DoubleClickExitHelper(this);

    Typeface iconfont;

    private List menuList;

    private final String MENU_JSON = "[" +
            "{'menuIcon': " + R.string.icon_sale + ",'menuName' : '销售清单', 'menuType' : " + MenuActivityEnum.SCALE + " }," +
            "{'menuIcon': " + R.string.icon_search + ",'menuName' : '速查',   'menuType'  :  " + MenuActivityEnum.SEARCH + "}," +
            "{'menuIcon': " + R.string.icon_product + ",'menuName' : '商品',  'menuType' : " + MenuActivityEnum.PRODUCT + " }," +
            "{'menuIcon': " + R.string.icon_custom + ",'menuName' : '客户','menuType'  :  " + MenuActivityEnum.CUSTOM + "}," +
            "{'menuIcon': " + R.string.icon_purchase + ",'menuName' : '进货清单','menuType'  :  " + MenuActivityEnum.PURCHASE + "}," +
            "{'menuIcon': " + R.string.icon_stock + ",'menuName' : '库存','menuType'  :  " + MenuActivityEnum.STOCK + "}," +
            "{'menuIcon': " + R.string.icon_report + ",'menuName' : '报表','menuType'  :  " + MenuActivityEnum.REPORT + "}" +
            "]";

    private DrawerMenuAdapter drawerMenuArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DrawerActivity", getClass().getSimpleName());
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

        initMenuData();
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
        drawerMenuArrayAdapter = new DrawerMenuAdapter(this, R.layout.base_drawer_menu_list, menuList);
        drawerMenu.setAdapter(drawerMenuArrayAdapter);

        //处理item点击事件
        drawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrawerMenuModel dm = (DrawerMenuModel) menuList.get(position);
                startMenuActivity(dm);

                //设置选中项目
                drawerMenuArrayAdapter.setSelectItem(position);
                drawerMenuArrayAdapter.notifyDataSetInvalidated();
            }
        });
    }



    private void startMenuActivity(DrawerMenuModel dm) {


         Intent intent;

        switch (dm.getMenuType()) {
            case SCALE:
                intent = new Intent(DrawerActivity.this, AccountSalesActivity.class);
                break;
            case SEARCH:
                intent = new Intent(DrawerActivity.this, SearchProductActivity.class);
                break;
            case PRODUCT:
                intent = new Intent(DrawerActivity.this, ProductActivity.class);
                break;
            case CUSTOM:
                intent = new Intent(DrawerActivity.this, CustomActivity.class);
                break;
//            case PURCHASE:
//                startActivity(new Intent(DrawerActivity.this, ProductActivity.class));
//                break;
//            case STOCK:
//                startActivity(new Intent(DrawerActivity.this, ProductActivity.class));
//                break;
//            case REPORT:
//                startActivity(new Intent(DrawerActivity.this, ProductActivity.class));
//                break;
            default:
                intent = new Intent(DrawerActivity.this, AccountSalesActivity.class);
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("menuItem", dm);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }

    /**
     * 获得menuItem
     * @param intent
     * @return
     */
    protected  DrawerMenuModel getMenuItem(Intent intent){
        return (DrawerMenuModel) intent.getSerializableExtra("menuItem");
    }


    private void initMenuData() {
        menuList = GsonUtil.getInstance().fromJson(MENU_JSON, new TypeToken<List<DrawerMenuModel>>() {
        }.getType());
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
    }


}
