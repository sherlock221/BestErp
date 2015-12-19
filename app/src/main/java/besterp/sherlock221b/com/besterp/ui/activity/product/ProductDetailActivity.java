package besterp.sherlock221b.com.besterp.ui.activity.product;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.dao.ProductStandardDao;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import besterp.sherlock221b.com.besterp.ui.adapter.ProductStandardAdapter;
import besterp.sherlock221b.com.besterp.ui.common.CollapsingToolbarActivity;
import besterp.sherlock221b.com.besterp.util.DialogUtil;
import besterp.sherlock221b.com.besterp.util.PageUtil;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductDetailActivity extends CollapsingToolbarActivity {

    @Bind(R.id.product_standard_recyclerView)
    RecyclerView productStandardRecyclerView;


    private ProductStandardAdapter productStandardAdapter;

    private List<ProductStandard> productStandardList;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_activity);
        ButterKnife.bind(this);

        product = (Product) PageUtil.getParcelable("product", getIntent());
        setTite(product.getProductName());


        //查询数据
        productStandardList  = DbUtil.getProductStandardService().queryBuilder().where(ProductStandardDao.Properties.ProductId.eq(product.getId())).list();
        //设置布局管理器
        productStandardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        productStandardAdapter = new ProductStandardAdapter(R.layout.product_standard,productStandardList);
        productStandardRecyclerView.setAdapter(productStandardAdapter);
        //设置Item增加、移除动画
        productStandardRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    private void deleteProduct(){
        if(product != null){
            try {
                DbUtil.getProductService().deleteProduct(product);
                ToastUtils.showShort("删除成功");
                finish();
            } catch (Exception e) {
                ToastUtils.showShort("删除失败 数据库错误!");
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.product_detail_delete) {
            DialogUtil.simpleConfirm(this, "提示", "确定是否删除这件商品?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case Dialog.BUTTON_POSITIVE:
                            deleteProduct();
                            break;
                        case Dialog.BUTTON_NEGATIVE:
                            break;
                        case Dialog.BUTTON_NEUTRAL:
                            break;
                    }
                    //关闭dialog
                    dialog.dismiss();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
