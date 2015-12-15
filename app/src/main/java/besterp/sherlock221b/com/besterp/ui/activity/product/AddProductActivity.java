package besterp.sherlock221b.com.besterp.ui.activity.product;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.ui.adapter.HomeAdapter;
import besterp.sherlock221b.com.besterp.ui.common.CollapsingToolbarActivity;

public class AddProductActivity extends CollapsingToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景图片
        setBackgroundImage(R.drawable.collapsingtoobar_bg);
        setContentView(R.layout.activity_add_product);

        setTite("你好");

        String[] items = {"a","b","a","b","a","b","a","b","a","b","a","b"};

        RecyclerView  mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        mRecyclerView.setAdapter(new HomeAdapter(items,R.layout.item_home));
        //设置Item增加、移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
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


