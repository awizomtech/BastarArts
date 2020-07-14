package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.awizomtech.bastararts.Adapter.ProductAdapter;
import com.awizomtech.bastararts.Adapter.ViewPagerAdapter;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.Model.ProductModel;
import com.awizomtech.bastararts.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CategoryBaseProductActivity extends AppCompatActivity {
    ProductAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ProductModel> productModels;
    private String result = "";
    String cid ="";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_base_product);
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

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        /*   recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetProductlist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        GetProductlist();

    }

    private void GetProductlist() {
        try {
            cid = getIntent().getExtras().getString("Cid");
            result = new UserHelper.GetCatBaseProductList().execute(cid.toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductModel>>() {
                }.getType();
                progressDialog.dismiss();
                productModels= new Gson().fromJson(result, listType);
                Log.d("Error", productModels.toString());
                adapter = new ProductAdapter(this, productModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}