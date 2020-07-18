package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.awizomtech.bastararts.Adapter.PaymentAdapter;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.Model.PaymentModel;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PaymentListActivity extends AppCompatActivity {
    PaymentAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<PaymentModel> paymentModels;
    private String result = "";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_list);
        InitView();
    }
    private void InitView() {
        Button backpress=findViewById(R.id.back);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView=findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetPaymentlist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        GetPaymentlist();

    }
    private void GetPaymentlist() {
        try {
            String userid = SharedPrefManager.getInstance(PaymentListActivity.this).getUser().getUserID();
            result = new UserHelper.GetPaymentList().execute(userid.toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<PaymentModel>>() {
                }.getType();
                progressDialog.dismiss();
                paymentModels= new Gson().fromJson(result, listType);
                Log.d("Error", paymentModels.toString());
                adapter = new PaymentAdapter(PaymentListActivity.this, paymentModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}