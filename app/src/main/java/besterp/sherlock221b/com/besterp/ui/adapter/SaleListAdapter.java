package besterp.sherlock221b.com.besterp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.db.model.SaleAccount;
import besterp.sherlock221b.com.besterp.model.ProductModel;

/**
 * Created by sherlock on 15/12/9.
 */
public class SaleListAdapter extends ArrayAdapter<SaleAccount> {

    private  int _resource;

    public SaleListAdapter(Context context, int resource, List<SaleAccount> objects) {
        super(context, resource, objects);
        this._resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SaleAccount saleAccount   = getItem(position);
        SaleListViewHolder viewHolder;
        View view;

        if (convertView == null) {
            viewHolder = new SaleListViewHolder();
            view = LayoutInflater.from(getContext()).inflate(_resource, null);

            viewHolder.product = (TextView) view.findViewById(R.id.sale_item_product);
            viewHolder.standard = (TextView) view.findViewById(R.id.sale_item_standard);
            viewHolder.util = (TextView) view.findViewById(R.id.sale_item_util);
            viewHolder.number = (TextView) view.findViewById(R.id.sale_item_number);
            viewHolder.price = (TextView) view.findViewById(R.id.sale_item_price);
            viewHolder.total = (TextView) view.findViewById(R.id.sale_item_total);

            view.setTag(viewHolder);

        } else {

            view  = convertView;
            viewHolder = (SaleListViewHolder) view.getTag();
        }



        String productName = saleAccount.getProduct().getProductName();

        viewHolder.product.setText(productName);
        viewHolder.standard.setText(saleAccount.getProductStandard().getStandardName());
        viewHolder.util.setText(saleAccount.getProduct().getProductUnit());
        viewHolder.number.setText(String.valueOf(saleAccount.getSaleAccountNumber()));
        viewHolder.price.setText(String.valueOf(saleAccount.getSaleAccountPrice()));
        viewHolder.total.setText(String.valueOf(saleAccount.getSaleAccountTotal()));

        return view;
    }

}



class SaleListViewHolder {

    TextView product;
    TextView standard;
    TextView util;
    TextView number;
    TextView price;
    TextView total;
}