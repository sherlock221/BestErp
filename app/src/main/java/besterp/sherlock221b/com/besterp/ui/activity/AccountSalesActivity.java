package besterp.sherlock221b.com.besterp.ui.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.DatePicker;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.dao.SaleAccountDao;
import besterp.sherlock221b.com.besterp.db.model.SaleAccount;
import besterp.sherlock221b.com.besterp.model.DrawerMenuModel;
import besterp.sherlock221b.com.besterp.ui.adapter.SaleListAdapter;
import besterp.sherlock221b.com.besterp.ui.common.BaseActivity;
import besterp.sherlock221b.com.besterp.ui.common.BaseDatePickDialog;
import besterp.sherlock221b.com.besterp.util.DateUtil;
import besterp.sherlock221b.com.besterp.util.DensityUtil;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountSalesActivity extends BaseActivity {


    @Bind(R.id.sale_list_view)
    SwipeMenuListView saleListView;
    @Bind(R.id.btn_prev_date)
    Button btnPrevDate;
    @Bind(R.id.btn_next_date)
    Button btnNextDate;
    @Bind(R.id.btn_select_date)
    Button btnSelectDate;


    private SaleListAdapter saleListAdapter;
    private List<SaleAccount> saleDataList;

    private DatePickerDialog.OnDateSetListener selectDateListener;
    private BaseDatePickDialog selectDatePickDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sales);
        ButterKnife.bind(this);

        DrawerMenuModel dm = getMenuItem(this.getIntent());
        if (dm != null)
            setTitle(dm.getMenuName());


        Date currentDate = DateUtil.getDateYMDByString("current");
        long time = currentDate.getTime();
        saleDataList = getSaleData(currentDate);

        Log.d("saleDate", DateUtil.yyyyMMdd.format(currentDate));


        saleListView.setMenuCreator(createMenuCreator());
        // Close Interpolator
        saleListView.setCloseInterpolator(new BounceInterpolator());

        saleListAdapter = new SaleListAdapter(this, R.layout.list_sale_item, saleDataList);
        saleListView.setAdapter(saleListAdapter);


        final Calendar calendar = Calendar.getInstance();
        selectDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                    calendar.set(Calendar.YEAR,year);
                    calendar.set(Calendar.MONTH,monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    ToastUtils.showShort("您选择的是: " + DateUtil.yyyyMMdd.format(calendar.getTime()));

                    //刷新adapter
                    refreshSaleListView(calendar.getTime());



            }
        };

        selectDatePickDialog = new BaseDatePickDialog(AccountSalesActivity.this, selectDateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

    }



    @OnClick(R.id.btn_select_date)
    public void showDatePickDialog(){
        selectDatePickDialog.show();
    }


    /**
     * 查询销售清单
     *
     * @return
     */
    private List<SaleAccount> getSaleData(Date date) {
        return DbUtil.getSaleAccountService().queryBuilder()
                .where(SaleAccountDao.Properties.SaleDate.eq(date))
                .list();

    }

    /**
     * 更新listview
     * @param date
     */
    private void refreshSaleListView(Date date) {

        //日期
        date = DateUtil.getDateYMDByDate(date);

        //更新list
        saleDataList.clear();
        List<SaleAccount> newList = getSaleData(date);
        saleDataList.addAll(newList);
        saleListAdapter.notifyDataSetChanged();
    }


    /**
     * 创建侧滑菜单
     *
     * @return
     */
    private SwipeMenuCreator createMenuCreator() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(DensityUtil.dp2px(AccountSalesActivity.this, 100));
                // set item title
                openItem.setTitle("修改");
                // set item title fontsize
                openItem.setTitleSize(14);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(DensityUtil.dp2px(AccountSalesActivity.this, 100));
                // set item title
                deleteItem.setTitle("删除");
                // set item title fontsize
                deleteItem.setTitleSize(14);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        return creator;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_sales, menu);
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
