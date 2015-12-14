package besterp.sherlock221b.com.besterp.ui.activity;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
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
import besterp.sherlock221b.com.besterp.cons.CustomTypeEnum;
import besterp.sherlock221b.com.besterp.db.DbCore;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.model.DrawerMenuModel;
import besterp.sherlock221b.com.besterp.model.ProductModel;
import besterp.sherlock221b.com.besterp.ui.adapter.ProductListAdapter;
import besterp.sherlock221b.com.besterp.ui.common.BaseActivity;





/**
 * 商品列表
 */
public class ProductActivity extends BaseActivity {


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
     * 存储所有手机中的联系人
     */
    private List<Product> products = new ArrayList<>();


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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        DrawerMenuModel dm = getMenuItem(this.getIntent());
        if(dm != null)
            setTitle(dm.getMenuName());



        adapter = new ProductListAdapter(this, R.layout.list_product_item, products);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        productsListView = (ListView) findViewById(R.id.contacts_list_view);
        alphabetButton = (Button) findViewById(R.id.alphabetButton);
        sectionToastLayout = (RelativeLayout) findViewById(R.id.section_toast_layout);
        sectionToastText = (TextView) findViewById(R.id.section_toast_text);

        Cursor cursor = getProductsData();
        startManagingCursor(cursor);
        indexer = new AlphabetIndexer(cursor,3, alphabet);
        adapter.setIndexer(indexer);

        if (products.size() > 0) {
            setupContactsListView();
            setAlpabetListener();
        }

    }




    private void setAlpabetListener(){

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

    private void setupContactsListView(){
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

    private Cursor  getProductsData(){
        Cursor productCursor = DbUtil.getProductService().queryProduct();
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

            } while (productCursor.moveToNext());
        }

        return productCursor;
    }


    /**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
     *
     * @param sortKeyString
     *            数据库中读取出的sort key
     * @return 英文字母或者#
     */
    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
