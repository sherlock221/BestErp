package besterp.sherlock221b.com.besterp.ui.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.util.ViewHolder;

/**
 * Created by sherlock on 15/12/17.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected int layout;
    protected Context context;
    protected List<T> dataList;

    public CommonAdapter(Context context, int layout, List<T> dataList) {
        this.context = context;
        this.layout = layout;
        this.dataList = dataList;
        this.mInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View  getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder = ViewHolder.get(context, convertView, parent, position, layout);
        convert(viewHolder,getItem(position));
        return viewHolder.getConvertView();
    }


    public  abstract  void convert(ViewHolder holder,T t);
}
