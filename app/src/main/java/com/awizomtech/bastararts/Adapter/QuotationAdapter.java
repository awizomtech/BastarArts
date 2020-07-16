package com.awizomtech.bastararts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awizomtech.bastararts.Activity.CategoryBaseProductActivity;
import com.awizomtech.bastararts.Activity.QuotationDetailsActivity;
import com.awizomtech.bastararts.Model.ProductCategoryModel;
import com.awizomtech.bastararts.Model.QuotationModel;
import com.awizomtech.bastararts.R;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.MyViewHolder> {
    private List<QuotationModel> quotationModelList;
    private Context mCtx;

    public QuotationAdapter(Context baseContext, List<QuotationModel> quotationModelList) {
        this.quotationModelList = quotationModelList;
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
        final QuotationModel n = quotationModelList.get(position);
        holder.ProductName.setText("Product Name : "+n.getProductName().toString());
        holder.QuoteNo.setText("Quotation No : "+n.getQuotationNo().toString());
        String qdate=n.getCreatedOn().toString();
        String newDate= qdate.split("T")[0];
        holder.QuoteDate.setText("Quotation Date : "+newDate.toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid = String.valueOf(n.getProductID());
                String qid = String.valueOf(n.getQuotationID());
                String rid = String.valueOf(n.getRequestID());
                String pcode = String.valueOf(n.getProductCode());
                String pname = String.valueOf(n.getProductName());
                String price = String.valueOf(n.getPrice());
                String qno = String.valueOf(n.getQuotationNo());
                String image = String.valueOf(n.getProductImage());
                String remark = String.valueOf(n.getRemark());
                String name = String.valueOf(n.getName());
                String mobile = String.valueOf(n.getMobile());
                String email = String.valueOf(n.getEmail());
                String address = String.valueOf(n.getAddress());
                String date = String.valueOf(n.getCreatedOn());

                Intent intent = new Intent(mCtx, QuotationDetailsActivity.class);
                intent.putExtra("Pid", pid);
                intent.putExtra("Qid", qid);
                intent.putExtra("Rid", rid);
                intent.putExtra("Pcode", pcode);
                intent.putExtra("Pname", pname);
                intent.putExtra("Price", price);
                intent.putExtra("Qno", qno);
                intent.putExtra("Image", image);
                intent.putExtra("Remark", remark);
                intent.putExtra("Name", name);
                intent.putExtra("Mobile", mobile);
                intent.putExtra("Email", email);
                intent.putExtra("Address", address);
                intent.putExtra("Date", date);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return quotationModelList.size();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quotation_list_adapter, parent, false);

        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ProductName,QuoteNo,QuoteDate;
        CardView cardView;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            ProductName = view.findViewById(R.id.productname);
            QuoteNo = view.findViewById(R.id.quotationNo);
            QuoteDate = view.findViewById(R.id.quotationdate);
            cardView = view.findViewById(R.id.cardview);


        }


    }

}