package besterp.sherlock221b.com.besterp.ui.activity.product;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.cons.ResultCode;
import besterp.sherlock221b.com.besterp.db.DbUtil;
import besterp.sherlock221b.com.besterp.db.model.Product;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;
import besterp.sherlock221b.com.besterp.task.LoadingTask;
import besterp.sherlock221b.com.besterp.task.SaveProductTask;
import besterp.sherlock221b.com.besterp.ui.common.DrawerActivity;
import besterp.sherlock221b.com.besterp.ui.common.ToolBarActivity;
import besterp.sherlock221b.com.besterp.util.DialogUtil;
import besterp.sherlock221b.com.besterp.util.Pinyin4jUtil;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import besterp.sherlock221b.com.besterp.util.ValidateUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.next.tagview.TagCloudView;

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

    @Bind(R.id.product_tag_cloud_view)
    TagCloudView productStandardTagCloudsView;


    @Bind(R.id.add_product_standard)
    Button addProductStandardBtn;


    private List<String> productStandardTagList;


    @OnClick(R.id.add_product_standard)
    void onAddProductStandardClick(){
        String tag = productStandardEditText.getText().toString().trim();

        if(ValidateUtil.isEmpty(tag)){
            productStandardLayout.setError("请输入规格!");
        }
        else if(productStandardTagList.contains(tag)){
            productStandardLayout.setError(tag + " 已经存在!");
        }
        else{
            productStandardLayout.setErrorEnabled(false);
            productStandardTagList.add(tag);
            productStandardTagCloudsView.setTags(productStandardTagList);
            productStandardEditText.setText("");
        }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        setTitle("添加商品");
        initTagView();

    }


    private void initTagView(){
        productStandardTagList = new ArrayList<>();
        productStandardTagCloudsView.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onTagClick(final int position) {
                String tag = productStandardTagList.get(position);
                DialogUtil.simpleConfirm(AddProductActivity.this, "删除提示", "删除("+tag+")这个标签吗?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case Dialog.BUTTON_POSITIVE:
                                productStandardTagList.remove(position);
                                productStandardTagCloudsView.setTags(productStandardTagList);
                                break;
                            default:
                                break;
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    private boolean checkForm() {
        String  productNameStr = productNameEditText.getText().toString();
        String  productUtilStr = productUtilEditText.getText().toString();

        boolean isFlag = true;

        if (ValidateUtil.isEmpty(productNameStr)) {
            productNameLayout.setError("请输入商品名称!");
            isFlag = false;
        }
        else{
            productNameLayout.setErrorEnabled(false);
        }


        if(ValidateUtil.isEmpty(productUtilStr)){
            productUtilLayout.setError("请输入单位!");
            isFlag = false;
        }
        else{
            productUtilLayout.setErrorEnabled(false);
        }

        if(productStandardTagList.size() <= 0){
            productStandardLayout.setError("请添加一个规格!");
            isFlag = false;
        }
        else{
            productStandardLayout.setErrorEnabled(false);
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

        SaveProductTask task = new SaveProductTask(this);
        task.setFinishListener(new SaveProductTask.DataFinishListener<Integer>() {
            @Override
            public void onSuccess(Integer status) {
                switch (status){
                    case ResultCode.SUCCESS :
                        ToastUtils.toast("添加成功!");
                        resetForm();
                        break;
                    default:
                        ToastUtils.toast("异常!");
                        break;
                }
            }
            @Override
            public void onError() {

            }
        });

        task.execute(product, productStandardTagList);
    }


    private void resetForm(){
        this.productNameEditText.setText("");
        this.productUtilEditText.setText("");
        this.productStandardEditText.setText("");
        this.productStandardTagList.clear();
        this.productStandardTagCloudsView.setTags(this.productStandardTagList);
        this.productNameEditText.setFocusable(true);
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


