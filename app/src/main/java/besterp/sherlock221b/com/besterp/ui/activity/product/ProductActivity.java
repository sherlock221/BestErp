package besterp.sherlock221b.com.besterp.ui.activity.product;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.cons.ResultCode;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.model.DrawerMenuModel;
import besterp.sherlock221b.com.besterp.task.SearchProductTask;
import besterp.sherlock221b.com.besterp.ui.adapter.ProductListAdapter;
import besterp.sherlock221b.com.besterp.ui.common.DrawerActivity;
import besterp.sherlock221b.com.besterp.util.PageUtil;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import besterp.sherlock221b.com.besterp.util.ValidateUtil;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


/**
 * 商品列表
 */
public class ProductActivity extends DrawerActivity {


    /**
     * 分组的布局
     */
    private LinearLayout titleLayout;

    /**
     * 分组上显示的字母
     */
    private TextView title;

    /**
     * 联系人ListView
     */
    private ListView productsListView;

    /**
     * 联系人列表适配器
     */
    private ProductListAdapter adapter;

    /**
     * 用于进行字母表分组
     */
    private AlphabetIndexer indexer;


    /**
     * 产品列表
     */
    private List<Product> products = new ArrayList<>();


    private List<Product> cacheProducts = new ArrayList<>();


    /**
     * 定义字母表的排序规则
     */
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;

    /**
     * 右侧可滑动字母表
     */
    private Button alphabetButton;


    /**
     * 弹出式分组的布局
     */
    private RelativeLayout sectionToastLayout;

    /**
     * 弹出式分组上的文字
     */
    private TextView sectionToastText;


    /**
     * 查询task
     */
    SearchProductTask searchTask;


    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * activity 返回值
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == ResultCode.SUCCESS)
        {
            boolean isRefresh = data.getBooleanExtra("isRefresh",false);
            if(isRefresh){
                refreshProductList();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        DrawerMenuModel dm = getMenuItem(this.getIntent());
        if (dm != null)
            setTitle(dm.getMenuName());

        //showLoading("正在加载");




        adapter = new ProductListAdapter(this, R.layout.list_product_item, products);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        productsListView = (ListView) findViewById(R.id.contacts_list_view);
        alphabetButton = (Button) findViewById(R.id.alphabetButton);
        sectionToastLayout = (RelativeLayout) findViewById(R.id.section_toast_layout);
        sectionToastText = (TextView) findViewById(R.id.section_toast_text);

        Cursor cursor = packageProductsData(DbUtil.getProductService().queryProduct(),true);
        startManagingCursor(cursor);
        indexer = new AlphabetIndexer(cursor, 3, alphabet);
        adapter.setIndexer(indexer);

        if (products.size() > 0) {
            setupContactsListView();
            setAlpabetListener();
        }

        //创建查询task
        searchTask = new SearchProductTask();

    }


    /**
     * product 点击
     */
    @OnItemClick(R.id.contacts_list_view)
    public void onProductItemClick(int position){
        Product  product = products.get(position);
        PageUtil.forwardActivityWithParams(ProductActivity.this,ProductDetailActivity.class,"product",product);
    }


    private void refreshProductList(){
        packageProductsData(DbUtil.getProductService().queryProduct(),false);
        adapter.notifyDataSetChanged();
    }

    private void refreshProductList(Cursor cursor){
        packageProductsData(cursor,false);
        adapter.notifyDataSetChanged();
    }



    private void setAlpabetListener() {

        alphabetButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float alphabetHeight = alphabetButton.getHeight();
                float y = event.getY();

                int sectionPosition = (int) ((y / alphabetHeight) / (1f / 27f));
                if (sectionPosition < 0) {
                    sectionPosition = 0;
                } else if (sectionPosition > 26) {
                    sectionPosition = 26;
                }

                String sectionLetter = String.valueOf(alphabet.charAt(sectionPosition));
                int position = indexer.getPositionForSection(sectionPosition);

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        alphabetButton.setBackgroundResource(R.drawable.a_z_click);
                        sectionToastLayout.setVisibility(View.VISIBLE);
                        sectionToastText.setText(sectionLetter);
                        productsListView.setSelection(position);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        sectionToastText.setText(sectionLetter);
                        productsListView.setSelection(position);
                        break;
                    default:
                        alphabetButton.setBackgroundResource(R.drawable.a_z);
                        sectionToastLayout.setVisibility(View.GONE);
                }

                return false;
            }
        });


    }

    private void setupContactsListView() {
        productsListView.setAdapter(adapter);
        productsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int section = indexer.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = indexer.getPositionForSection(section + 1);

                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf(alphabet.charAt(section)));
                }

                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

    }

    private Cursor packageProductsData(Cursor productCursor,Boolean isFirst) {

        products.clear();

        if (productCursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(productCursor.getLong(0));
                product.setProductName(productCursor.getString(1));
                product.setProductDesc(productCursor.getString(2));
                product.setProductUnit(productCursor.getString(4));

                product.setProductUseCount(productCursor.getInt(5));
                product.setProductPurchaseUseCount(productCursor.getInt(6));
                product.setProductSaleUseCount(productCursor.getInt(7));
                product.setIsDelete(Boolean.parseBoolean(productCursor.getString(8)));

                product.setCrtTime(new Date(productCursor.getLong(9)));
                product.setUpdateTime(new Date(productCursor.getLong(10)));

                //sortkey
                product.setSortKey(productCursor.getString(3));
                products.add(product);

                if(isFirst){
                    cacheProducts.add(product);
                }

            } while (productCursor.moveToNext());
        }

        return productCursor;
    }




    /**
     * 模糊匹配商品名称
     *
     * @param newText
     */
    private void getProductByName(String newText) {
        if (searchTask != null && searchTask.getStatus() != AsyncTask.Status.RUNNING) {
            ToastUtils.toast("开始查询了 : " + newText);
            searchTask = new SearchProductTask();
            searchTask.setFinishListener(new SearchProductTask.DataFinishListener() {
                @Override
                public void dataFinishSuccessfully(Cursor cursor) {
                    refreshProductList(cursor);
                }
            });
            searchTask.execute(newText);
        } else {
            ToastUtils.toast("正在查询中 稍后: ");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);

        MenuItem menuItem = menu.findItem(R.id.search_product);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

//        Cursor cursor = DbUtil.getProductService().queryProduct();
//        SimpleCursorAdapter  simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.search_product,cursor,new String[] { "PRODUCT_NAME" },
//                new int[] { R.id.textview });
//        searchView.setSuggestionsAdapter(simpleCursorAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (ValidateUtil.isEmpty(query)) return false;
                getProductByName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                if(ValidateUtil.isEmpty(newText))return false;
//                getProductByName(newText);
                return false;
            }
        });


        //设置打开关闭动作监听
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                products.clear();
                products.addAll(cacheProducts);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.product_add) {
            PageUtil.forwardActivityForResult(ProductActivity.this,AddProductActivity.class,1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消task
        if (searchTask != null && searchTask.getStatus() == AsyncTask.Status.RUNNING) {
            // 如果Task还在运行，则先取消它
            searchTask.cancel(true);
        }

    }
}
