package besterp.sherlock221b.com.besterp.ui.common;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.view.ToolBarHelper;

/**
 * Created by sherlock on 15/12/18.
 */
public class ToolBarActivity extends  BaseActivity {

    //顶部
    protected  Toolbar toolbar;
    protected  LinearLayout userContent;
    protected  View  rootContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setContentView(int layoutResID) {
        initView(layoutResID);
        settingComponent();
        setContentView(rootContent);
    }

    private void initCustomView(int id,LinearLayout userContent){
        View userView = LayoutInflater.from(this).inflate(id, null);
        userContent.addView(userView);
    }

    private void initView(int layoutResID){
        //解析文件
        rootContent = LayoutInflater.from(this).inflate(R.layout.base_toolbar_main, null);
        toolbar = (Toolbar) rootContent.findViewById(R.id.base_toolbar);
        userContent = (LinearLayout) rootContent.findViewById(R.id.user_content);

        //添加自定义view
        initCustomView(layoutResID,userContent);
    }


    private void settingComponent (){
        setSupportActionBar(toolbar);
        //设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
