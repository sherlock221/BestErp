package besterp.sherlock221b.com.besterp.ui.activity.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.cons.ResultCode;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import besterp.sherlock221b.com.besterp.ui.common.DrawerActivity;
import besterp.sherlock221b.com.besterp.ui.common.ToolBarActivity;
import besterp.sherlock221b.com.besterp.util.Pinyin4jUtil;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import besterp.sherlock221b.com.besterp.util.ValidateUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends ToolBarActivity {


    @Bind(R.id.product_name)
    EditText productNameEditText;

    @Bind(R.id.product_util)
    EditText productUtilEditText;

    @Bind(R.id.product_standard)
    EditText productStandardEditText;

    @Bind(R.id.product_name_layout)
    TextInputLayout productNameLayout;

    @Bind(R.id.product_util_layout)
    TextInputLayout productUtilLayout;

    @Bind(R.id.product_standard_layout)
    TextInputLayout productStandardLayout;

    @Bind(R.id.product_add_btn)
    Button  productAddBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        setTitle("添加商品");

    }



    private boolean checkForm() {
        String  productNameStr = productNameEditText.getText().toString();
        String  productStandardStr = productStandardEditText.getText().toString();
        String  productUtilStr = productUtilEditText.getText().toString();

        boolean isFlag = true;

        if (ValidateUtil.isEmpty(productNameStr)) {
            productNameLayout.setError("请输入商品名称!");
            isFlag = false;
        }
        else{
            productNameLayout.setErrorEnabled(false);
        }

        if(ValidateUtil.isEmpty(productStandardStr)){
            productStandardLayout.setError("请输入规格!");
            isFlag = false;
        }
        else{
            productStandardLayout.setErrorEnabled(false);
        }


        if(ValidateUtil.isEmpty(productUtilStr)){
            productUtilLayout.setError("请输入单位!");
            isFlag = false;
        }
        else{
            productUtilLayout.setErrorEnabled(false);
        }

        return isFlag;
    }



    @OnClick(value = R.id.product_add_btn)
    void onProductAddBtnClick(){

        if(checkForm()){
            saveProduct();
        }
    }

    private void saveProduct() {

        showLoading();

        try {
            Product product = new Product();
            product.setUpdateTime(new Date());
            product.setCrtTime(new Date());
            product.setIsDelete(false);
            product.setProductName(this.productNameEditText.getText().toString());
            product.setProductUnit(this.productUtilEditText.getText().toString());
            product.setSortKey("");
            product.setProductSaleUseCount(0);
            product.setProductUseCount(0);
            product.setProductPurchaseUseCount(0);
            //设置sortkey
            product.setSortKey(createSortKey(product.getProductName()));
            DbUtil.getProductService().save(product);

            ProductStandard productStandard = new ProductStandard();
            productStandard.setUpdateTime(new Date());
            productStandard.setCrtTime(new Date());
            productStandard.setIsDelete(false);
            productStandard.setStandardName(this.productStandardEditText.getText().toString());
            productStandard.setProductId(product.getId());
            //存储
            DbUtil.getProductStandardService().saveOrUpdate(productStandard);


            setResult(ResultCode.SUCCESS,new Intent().putExtra("isRefresh",true));

        }
        catch (Exception e){
                ToastUtils.toast("处理失败!");
        }
        finally {
            hideloading();
        }


        ToastUtils.toast("添加成功!");
        resetForm();
    }


    private void resetForm(){
        this.productNameEditText.setText("");
        this.productUtilEditText.setText("");
        this.productStandardEditText.setText("");
    }


    /**
     * 生成sortkey
     * @return
     */
    private String createSortKey(String text){
        String firstLetter = text.substring(0,1);
        return  Pinyin4jUtil.cn2FirstSpell(firstLetter).toUpperCase();
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


