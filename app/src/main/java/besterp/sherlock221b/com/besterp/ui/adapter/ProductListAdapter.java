package besterp.sherlock221b.com.besterp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.model.ProductModel;

/**
 * Created by sherlock on 15/12/2.
 */
public class ProductListAdapter extends ArrayAdapter<ProductModel>  {

    /**
     * 需要渲染的item布局文件
     */
    private int resource;

    /**
     *字母表分组工具
     */
    private SectionIndexer mIndexer;


    public ProductListAdapter(Context context, int resource, List<ProductModel> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProductModel productModel = getItem(position);
        LinearLayout layout = null;

        if (convertView == null) {
            layout = (LinearLayout) LayoutInflater.from(getContext()).inflate(resource, null);
        } else {
            layout = (LinearLayout) convertView;
        }

        TextView name = (TextView) layout.findViewById(R.id.name);
        LinearLayout sortKeyLayout = (LinearLayout) layout.findViewById(R.id.sort_key_layout);
        TextView sortKey = (TextView) layout.findViewById(R.id.sort_key);

        name.setText(productModel.getName());


        //取得当前 position对应的 section值
        int section =  mIndexer.getSectionForPosition(position);

        if (position == mIndexer.getPositionForSection(section)) {
            sortKey.setText(productModel.getSortKey());
            sortKeyLayout.setVisibility(View.VISIBLE);
        } else {
            sortKeyLayout.setVisibility(View.GONE);
        }

        return layout;
    }


    /**
     * 给当前适配器传入一个分组工具。
     * @param indexer
     */
    public void setIndexer(SectionIndexer indexer) {
        mIndexer = indexer;
    }
}
