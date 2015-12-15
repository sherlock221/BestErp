package besterp.sherlock221b.com.besterp.ui.common;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.view.ToolBarHelper;

/**
 * Created by sherlock on 15/12/15.
 */
public class CollapsingToolbarActivity extends AppCompatActivity {


    //顶部
    public Toolbar toolbar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    /*base view*/
    private FrameLayout mContentView;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    private int backgroundImageId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ac", getClass().getSimpleName());

    }


    //创建一个根
    private void initContentView(Context context) {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);

    }



    private void initCustomView(int id,LinearLayout context){
        View userView = mInflater.inflate(id, null);
        context.addView(userView);
    }

    private void initView(int layoutResID){
        //初始化根
        initContentView(this);

        //解析文件
        mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.base_collapsing_toolbar_activity, mContentView);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_layout);
        LinearLayout customContext = (LinearLayout) view.findViewById(R.id.custom_content);

        //设置背景图片
        if(this.backgroundImageId != -1){
            ImageView imageView = (ImageView) view.findViewById(R.id.collapsingtoobar_image);
            imageView.setImageResource(this.backgroundImageId);
        }


        //添加自定义view
        initCustomView(layoutResID, customContext);
    }


    private void settingComponent (){
        setSupportActionBar(toolbar);
        //设置返回键可用
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");

        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色


    }

    @Override
    public void setContentView(int layoutResID) {
        //创建并添加view
        initView(layoutResID);
        //设置部分
        settingComponent();

        setContentView(mContentView);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void  setTite(String title){
        mCollapsingToolbarLayout.setTitle(title);
    }

    protected  void setBackgroundImage (int resId){
        this.backgroundImageId = resId;
    }




}
