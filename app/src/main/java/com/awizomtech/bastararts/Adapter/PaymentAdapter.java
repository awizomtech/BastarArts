package com.awizomtech.bastararts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.awizomtech.bastararts.Activity.QuotationDetailsActivity;
import com.awizomtech.bastararts.Model.PaymentModel;
import com.awizomtech.bastararts.Model.QuotationModel;
import com.awizomtech.bastararts.R;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    private List<PaymentModel> paymentModelList;
    private Context mCtx;

    public PaymentAdapter(Context baseContext, List<PaymentModel> paymentModelList) {
        this.paymentModelList = paymentModelList;
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
        final PaymentModel n = paymentModelList.get(position);
        holder.TransactionID.setText("TransactionID : "+n.getTransactionID().toString());
        holder.PaymentMode.setText("Payment Mode : "+n.getPaymentMode().toString());
        String qdate=n.getPaymentDate().toString();
        String newDate= qdate.split("T")[0];
        holder.PaymentDate.setText(newDate.toString());
        holder.TotalAmount.setText("TotalAmount : "+String.valueOf(n.getTotalAmount())+" ₹");
    }

    @Override
    public int getItemCount() {

        return paymentModelList.size();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_list_adapter, parent, false);

        return new MyViewHolder(v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TransactionID,PaymentMode,TotalAmount,PaymentDate;
        CardView cardView;

        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View view) {
            super(view);
            TransactionID = view.findViewById(R.id.transactionID);
            PaymentMode = view.findViewById(R.id.paymentMode);
            TotalAmount = view.findViewById(R.id.paymentAmount);
            PaymentDate = view.findViewById(R.id.paymentdate);
            cardView = view.findViewById(R.id.cardview);


        }


    }

}