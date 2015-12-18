package besterp.sherlock221b.com.besterp.test;

import android.content.Context;


import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.ui.common.CommonAdapter;
import besterp.sherlock221b.com.besterp.util.ViewHolder;

/**
 * Created by sherlock on 15/12/17.
 */
public class MyAdapter extends CommonAdapter<User> {
    public MyAdapter(Context context, int layout, List<User> dataList) {
        super(context, layout, dataList);
    }
    @Override
    public void convert(ViewHolder holder, User user) {
        holder  .setText(R.id.user_name, user.getUserName())
                .setText(R.id.user_age, user.getUserAge())
                .setText(R.id.user_address, user.getUserAddress());
    }
}
