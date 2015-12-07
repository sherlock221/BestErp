package besterp.sherlock221b.com.besterp.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.model.DrawerMenuModel;
import besterp.sherlock221b.com.besterp.util.IconFontUtil;

/**
 * Created by sherlock on 15/12/1.
 */
public class DrawerMenuAdapter extends ArrayAdapter<DrawerMenuModel> {


    private int resourceId;
    private int  selectItem;

    public DrawerMenuAdapter(Context context, int resource, List<DrawerMenuModel> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerMenuModel drawerMenuModel = getItem(position);
        View view;
        ViewHolder viewHolder;

        Log.d("Drawer", String.valueOf(position));



        if(convertView == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder.menuIcon = (TextView) view.findViewById(R.id.menu_icon);
            viewHolder.menuName = (TextView) view.findViewById(R.id.menu_name);
            view.setTag(viewHolder);
        }
        else{

            view  = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }



//        if (position == selectItem) {
//
//            view.setBackgroundColor(Color.RED);
//        }
//        else {
//            view.setBackgroundColor(Color.TRANSPARENT);
//        }


        Typeface iconfont = IconFontUtil.getIconFontTypeFace();
        viewHolder.menuIcon.setTypeface(iconfont);
        viewHolder.menuIcon.setText(drawerMenuModel.getMenuIcon());
        viewHolder.menuName.setText(drawerMenuModel.getMenuName());

        return view;
    }


    public void setSelectItem(int selectItem){
        this.selectItem = selectItem;
    }
}


class ViewHolder {

    TextView menuIcon;
    TextView menuName;
}