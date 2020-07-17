package com.awizomtech.bastararts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.awizomtech.bastararts.Activity.OrderDetailActivity;
import com.awizomtech.bastararts.Activity.QuotationDetailsActivity;
import com.awizomtech.bastararts.Model.OrderModel;
import com.awizomtech.bastararts.Model.QuotationModel;
import com.awizomtech.bastararts.R;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private List<OrderModel> orderModelList;
    private Context mCtx;

    public OrderAdapter(Context baseContext, List<OrderModel> orderModelList) {
        this.orderModelList = orderModelList;
        this.mCtx = baseContext;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final OrderModel n = orderModelList.get(position);
        holder.ProductName.setText("Product Name : "+n.getProductName().toString());
        holder.QuoteNo.setText("Order No : "+n.getOrderNo().toString());
        String qdate=n.getOrderDate().toString();
        String newDate= qdate.split("T")[0];
        holder.QuoteDate.setText("Date : "+newDate.toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OrderID = String.valueOf(n.getOrderID());
                String Quantity = String.valueOf(n.getQuantity());
                String OrderNo = String.valueOf(n.getOrderNo());
                String OrderDate = String.valueOf(n.getOrderDate());
                String TotalAmount = String.valueOf(n.getTotalAmount());
                String IsPaymentDone = String.valueOf(n.IsPaymentDone);
                String ShippingCharge = String.valueOf(n.getShippingCharge());
                String Status = String.valueOf(n.getStatus());
                String AddressLineOne = String.valueOf(n.getAddressLineOne());
                String Landmark = String.valueOf(n.getLandmark());
                String PinCode = String.valueOf(n.getPinCode());
                String Country = String.valueOf(n.getCountry());
                String State = String.valueOf(n.getState());
                String City = String.valueOf(n.getCity());
                String ProductID = String.valueOf(n.getProductID());
                String ProductCode = String.valueOf(n.getProductCode());
                String ProductName = String.valueOf(n.getProductName());
                String ProductImage = String.valueOf(n.getProductImage());
                String Description = String.valueOf(n.getDescription());

                Intent intent = new Intent(mCtx, OrderDetailActivity.class);
                intent.putExtra("OrderID", OrderID);
                intent.putExtra("Quantity", Quantity);
                intent.putExtra("OrderNo", OrderNo);
                intent.putExtra("OrderDate", OrderDate);
                intent.putExtra("TotalAmount", TotalAmount);
                intent.putExtra("IsPaymentDone", IsPaymentDone);
                intent.putExtra("ShippingCharge", ShippingCharge);
                intent.putExtra("Status", Status);
                intent.putExtra("AddressLineOne", AddressLineOne);
                intent.putExtra("Landmark", Landmark);
                intent.putExtra("PinCode", PinCode);
                intent.putExtra("Country", Country);
                intent.putExtra("State", State);
                intent.putExtra("City", City);
                intent.putExtra("ProductID", ProductID);
                intent.putExtra("ProductCode", ProductCode);
                intent.putExtra("ProductName", ProductName);
                intent.putExtra("ProductImage", ProductImage);
                intent.putExtra("Description", Description);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return orderModelList.size();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_adapter, parent, false);

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