package besterp.sherlock221b.com.besterp.ui.activity.account;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.cons.AccountTypeEnum;
import besterp.sherlock221b.com.besterp.ui.common.BaseActivity;
import besterp.sherlock221b.com.besterp.util.ToastUtils;
import besterp.sherlock221b.com.besterp.util.ValidateUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddAccountSalesActivity extends BaseActivity {


    @Bind(R.id.product_price)
    EditText productPriceText;
    @Bind(R.id.price_textInput_layout)
    TextInputLayout priceTextInputLayout;
    @Bind(R.id.product_number)
    EditText productNumber;
    @Bind(R.id.number_textInput_layout)
    TextInputLayout numberTextInputLayout;
    @Bind(R.id.product_total)
    EditText productTotal;

    @Bind(R.id.accountTypeRadioGroup)
    RadioGroup accountTypeRadioGroup;

    @Bind(R.id.product_add_btn)
    Button productAddBtn;
    @Bind(R.id.accountTypeRadioSale)
    RadioButton accountTypeRadioSale;
    @Bind(R.id.accountTypeRadioPub)
    RadioButton accountTypeRadioPub;


    private AccountTypeEnum currentRadioSelectValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_sales);
        ButterKnife.bind(this);
        setTitle("添加清单");


        //获得activity传递来的type 没有默认SALE
        currentRadioSelectValue = AccountTypeEnum.getEnum(getIntent().getIntExtra("accountType", 0));
        if (currentRadioSelectValue == null)
            currentRadioSelectValue = AccountTypeEnum.SALE;

        //选中当前传递过来类型 清单
        setRadioSelected(currentRadioSelectValue);

    }


    /**
     * 添加清单
     */
    @OnClick(R.id.product_add_btn)
    public void onAddAccountSale() {
        int radioButtonId = accountTypeRadioGroup.getCheckedRadioButtonId();
        switch (radioButtonId) {
            case R.id.accountTypeRadioSale:
                currentRadioSelectValue = AccountTypeEnum.SALE;
                break;
            default:
                currentRadioSelectValue = AccountTypeEnum.PURCHASE;
                break;
        }

        checkForm();

        ToastUtils.toast(String.valueOf(currentRadioSelectValue));
    }


    //自动计算金额
    @OnTextChanged(value = {R.id.product_number,R.id.product_price}, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onProductTextChanged(CharSequence text) {
        ToastUtils.toast("onTextChanged");
        if (!text.toString().equals("")) {
            String number = productNumber.getText().toString();
            String price = productPriceText.getText().toString();
            String total = cacleTotal(number, price);
            if (total != null) {
                ToastUtils.toast(total);
                productTotal.setText(total);
            }
        } else {
            productTotal.setText("");
        }
    }

    private String cacleTotal(String number, String price) {
        if (!ValidateUtil.isNumeric(number)) {
            return null;
        } else if (!ValidateUtil.isDecimal(price)) {
            return null;
        }

        int numInt = Integer.parseInt(number);
        double priceDb = Double.parseDouble(price);

        return String.valueOf(numInt * priceDb);
    }


    private boolean checkForm() {
        String productNumberStr = productNumber.getText().toString();
        String productPriceTextStr = productPriceText.getText().toString();

        boolean isFlag = true;

        if (!ValidateUtil.isNumeric(productNumberStr)) {
            numberTextInputLayout.setError("请输入整数!");
            isFlag = false;
        } else {
            numberTextInputLayout.setErrorEnabled(false);
        }


        if (!ValidateUtil.isDecimal(productPriceTextStr)) {
            priceTextInputLayout.setError("请输入小数 xx.xx!");
            isFlag = false;
        } else {
            priceTextInputLayout.setErrorEnabled(false);
        }
        return isFlag;
    }


    private void setRadioSelected(AccountTypeEnum accountType) {
        switch (accountType) {
            case SALE:
                accountTypeRadioSale.setChecked(true);
                break;
            default:
                accountTypeRadioPub.setChecked(true);
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_account_sales, menu);
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

