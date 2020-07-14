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
        import com.awizomtech.bastararts.Model.ProductCategoryModel;
        import com.awizomtech.bastararts.R;
        import java.util.List;

        import androidx.annotation.RequiresApi;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder> {
    private List<ProductCategoryModel> productCategoryModelList;
    private Context mCtx;

    public HomeCategoryAdapter(Context baseContext, List<ProductCategoryModel> productCategoryModelList) {
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
        holder.CategoryName.setText(n.getCategoryName().toString());
        holder.CategoryName.setOnClickListener(new View.OnClickListener() {
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
                .inflate(R.layout.home_category_list_adapter, parent, false);

        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView CategoryName;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            CategoryName = view.findViewById(R.id.category);



        }


    }

}