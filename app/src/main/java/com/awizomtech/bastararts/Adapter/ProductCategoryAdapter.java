package com.awizomtech.bastararts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.awizomtech.bastararts.Activity.CategoryBaseProductActivity;
import com.awizomtech.bastararts.Activity.ProductDetailsActivity;
import com.awizomtech.bastararts.AppConfig.AppConfig;
import com.awizomtech.bastararts.Model.ProductCategoryModel;
import com.awizomtech.bastararts.Model.ProductModel;
import com.awizomtech.bastararts.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.MyViewHolder> {
    private List<ProductCategoryModel> productCategoryModelList;
    private Context mCtx;

    public ProductCategoryAdapter(Context baseContext, List<ProductCategoryModel> productCategoryModelList) {
        this.productCategoryModelList = productCategoryModelList;
        this.mCtx = baseContext;

    }

    /* for solve issue of item change on scroll this method is set*/
    @Override
    public long getItemId(int position) {
        return position;
    }

    /* for solve issue of item change on scroll this method is set*/
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ProductCategoryModel n = productCategoryModelList.get(position);
        holder.ProductName.setText(n.getCategoryName().toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cid = String.valueOf(n.getCategoryID());
                Intent intent = new Intent(mCtx, CategoryBaseProductActivity.class);
                intent.putExtra("Cid", cid);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return productCategoryModelList.size();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_category_list_adapter, parent, false);

        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ProductName;
        ImageView imageView;
        CardView cardView;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            ProductName = view.findViewById(R.id.productname);
            imageView = view.findViewById(R.id.image);
            cardView = view.findViewById(R.id.cardview);


        }


    }

}