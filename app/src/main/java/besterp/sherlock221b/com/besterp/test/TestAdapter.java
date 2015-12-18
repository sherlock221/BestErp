package besterp.sherlock221b.com.besterp.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import besterp.sherlock221b.com.besterp.R;

/**
 * Created by sherlock on 15/12/17.
 */
public class TestAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private int layout;
    private Context context;
    private List<User> dataList;
    public TestAdapter(Context context, int layout, List<User> dataList) {
        this.context = context;
        this.layout = layout;
        this.mInflater.from(context);
        this.dataList = dataList;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }
    @Override
    public User getItem(int position) {
        return dataList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView userName;
        TextView userAge;
        TextView userAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(this.layout, parent, false);
            holder = new ViewHolder();
            holder.userName = (TextView) convertView.findViewById(R.id.user_name);
            holder.userAge = (TextView) convertView.findViewById(R.id.user_age);
            holder.userAddress = (TextView) convertView.findViewById(R.id.user_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = getItem(position);
        holder.userName.setText(user.getUserName());
        holder.userAddress.setText(user.getUserAddress());
        holder.userAge.setText(user.getUserAge());
        return convertView;
    }
}
