package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.awizomtech.bastararts.Adapter.ProductAdapter;
import com.awizomtech.bastararts.Adapter.ProductCategoryAdapter;
import com.awizomtech.bastararts.Adapter.ViewPagerAdapter;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.Model.ProductCategoryModel;
import com.awizomtech.bastararts.Model.ProductModel;
import com.awizomtech.bastararts.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductCategoryActivity extends AppCompatActivity {
    ProductCategoryAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ProductCategoryModel>  productCategoryModels;
    private String result = "";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        InitView();
    }
    private void InitView() {
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
                GetProductCategorylist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        GetProductCategorylist();

    }

    private void GetProductCategorylist() {
        try {
            result = new UserHelper.GetProductCatList().execute().get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductCategoryModel>>() {
                }.getType();
                progressDialog.dismiss();
                productCategoryModels= new Gson().fromJson(result, listType);
                Log.d("Error", productCategoryModels.toString());
                adapter = new ProductCategoryAdapter(this, productCategoryModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}