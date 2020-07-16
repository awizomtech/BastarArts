package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.awizomtech.bastararts.Adapter.ProductCategoryAdapter;
import com.awizomtech.bastararts.Adapter.QuotationAdapter;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.Model.ProductCategoryModel;
import com.awizomtech.bastararts.Model.QuotationModel;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class QuoteListActivity extends AppCompatActivity {
   QuotationAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<QuotationModel> quotationModels;
    private String result = "";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
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
                GetQuotationlist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        GetQuotationlist();

    }

    private void GetQuotationlist() {
        try {
            String userid = SharedPrefManager.getInstance(QuoteListActivity.this).getUser().getUserID();
            result = new UserHelper.GetQuoteList().execute(userid.toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<QuotationModel>>() {
                }.getType();
                progressDialog.dismiss();
                quotationModels= new Gson().fromJson(result, listType);
                Log.d("Error", quotationModels.toString());
                adapter = new QuotationAdapter(this, quotationModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}