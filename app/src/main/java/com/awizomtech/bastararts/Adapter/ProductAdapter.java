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

import com.awizomtech.bastararts.Activity.ProductDetailsActivity;
import com.awizomtech.bastararts.AppConfig.AppConfig;
import com.awizomtech.bastararts.Model.ProductModel;
import com.awizomtech.bastararts.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private List<ProductModel> productModelList;
    private Context mCtx;

    public ProductAdapter(Context baseContext, List<ProductModel> productModelList) {
        this.productModelList = productModelList;
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
        final ProductModel n = productModelList.get(position);
        holder.ProductName.setText(n.getProductName().toString());

        try {
            Glide.with(mCtx).load(AppConfig.BASE_URL + n.ProductImage.toString()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.ViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pname = n.getProductName().toString();
                String productcode = n.getProductCode().toString();
                String decription = n.getDescription().toString();
                String image = n.getProductImage().toString();
                String pid = String.valueOf(n.getProductID());
                String cid = String.valueOf(n.getCategoryID());
                Intent intent = new Intent(mCtx, ProductDetailsActivity.class);
                intent.putExtra("Productname", pname);
                intent.putExtra("ProductCode", productcode);
                intent.putExtra("Description", decription);
                intent.putExtra("Image", image);
                intent.putExtra("pid", pid);
                intent.putExtra("Cid", cid);
                mCtx.startActivity(intent);
            }
        });
       /* holder.Cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ctype = n.getType().toString();
                if (ctype.contains("Free")) {
                    String cid = String.valueOf(n.getCourseID());
                    String coursename = String.valueOf(n.getCourseName());
                    Intent intent = new Intent(mCtx, FreeSubcriptionActivity.class);
                    intent.putExtra("CourseName", coursename);
                    intent.putExtra("Cid", cid);
                    mCtx.startActivity(intent);
                } else {
                    String cid = String.valueOf(n.getCourseID());
                    String coursename = String.valueOf(n.getCourseName());
                    Intent intent = new Intent(mCtx, CourseLevelActivity.class);
                    intent.putExtra("CourseName", coursename);
                    intent.putExtra("Cid", cid);
                    mCtx.startActivity(intent);
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {

        return productModelList.size();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_adapter, parent, false);

        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ProductName;
        ImageView imageView;
        Button Request, ViewDetail;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            ProductName = view.findViewById(R.id.productname);
            imageView = view.findViewById(R.id.image);
            Request = view.findViewById(R.id.requestqout);
            ViewDetail = view.findViewById(R.id.viewdetail);
        }


    }

}