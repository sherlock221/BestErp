package besterp.sherlock221b.com.besterp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import besterp.sherlock221b.com.besterp.R;
import besterp.sherlock221b.com.besterp.db.model.ProductStandard;

/**
 * Created by sherlock on 15/12/15.
 */
public class ProductStandardAdapter extends RecyclerView.Adapter<ProductStandardAdapter.ProductStandardViewHolder> {

    private List<ProductStandard> productStandardList;
    private int layout;

    public ProductStandardAdapter(int layout, List<ProductStandard> productStandardList) {
        this.productStandardList = productStandardList;
        this.layout = layout;
    }


    @Override
    public ProductStandardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ProductStandardViewHolder holder = new ProductStandardViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ProductStandardViewHolder holder, int position) {
            ProductStandard productStandard =  productStandardList.get(position);
            holder.standardText.setText(productStandard.getStandardName());
            holder.itemView.setTag(productStandard);
    }

    @Override
    public int getItemCount() {
        return productStandardList.size();
    }

    class ProductStandardViewHolder extends RecyclerView.ViewHolder {

        TextView standardText;

        public ProductStandardViewHolder(View itemView) {
            super(itemView);
            standardText = (TextView) itemView.findViewById(R.id.standard_name);
        }
    }
}
