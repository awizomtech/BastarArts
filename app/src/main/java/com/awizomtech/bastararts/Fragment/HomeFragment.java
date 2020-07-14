package com.awizomtech.bastararts.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awizomtech.bastararts.Activity.HomePageActivity;
import com.awizomtech.bastararts.Activity.ProductCategoryActivity;
import com.awizomtech.bastararts.Adapter.HomeCategoryAdapter;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment {
    ProductAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<ProductModel> productModels;
    private String result = "";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView, CatView;
    ViewPager viewPager;
    HomeCategoryAdapter categoryAdapter;
    private List<ProductCategoryModel> productCategoryModels;
    TextView AllCat;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        InitView();
        return root;
    }

    private void InitView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView = root.findViewById(R.id.recyclerView);
        AllCat = root.findViewById(R.id.allCategory);
        CatView = root.findViewById(R.id.categoryView);
        viewPager = (ViewPager) root.findViewById(R.id.viewPager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipeToRefresh);
        CatView.setHasFixedSize(true);
        CatView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        /*   recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetProductlist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        AllCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                startActivity(intent);
            }
        });
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        GetProductCategorylist();
        GetProductlist();
    }

    private void GetProductlist() {
        try {
            result = new UserHelper.GetProductList().execute().get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<ProductModel>>() {
                }.getType();
                progressDialog.dismiss();
                productModels = new Gson().fromJson(result, listType);
                Log.d("Error", productModels.toString());
                adapter = new ProductAdapter(getContext(), productModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
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
                productCategoryModels = new Gson().fromJson(result, listType);
                Log.d("Error", productCategoryModels.toString());
                categoryAdapter = new HomeCategoryAdapter(getContext(), productCategoryModels);
                CatView.setAdapter(categoryAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
