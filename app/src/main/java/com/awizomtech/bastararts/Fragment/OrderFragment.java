package com.awizomtech.bastararts.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.awizomtech.bastararts.Adapter.OrderAdapter;
import com.awizomtech.bastararts.Adapter.QuotationAdapter;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.Model.OrderModel;
import com.awizomtech.bastararts.Model.QuotationModel;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class OrderFragment extends Fragment {
   OrderAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<OrderModel> orderModels;
    private String result = "";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_order_fragment, container, false);
        InitView();
        return root;
    }

    private void InitView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView=root.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)root.findViewById(R.id.swipeToRefresh);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetOrderlist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        GetOrderlist();

    }

    private void GetOrderlist() {
        try {
            String userid = SharedPrefManager.getInstance(getContext()).getUser().getUserID();
            result = new UserHelper.GetOrderList().execute(userid.toString()).get();
            if (result.isEmpty()) {
                progressDialog.dismiss();
            } else {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<OrderModel>>() {
                }.getType();
                progressDialog.dismiss();
                orderModels= new Gson().fromJson(result, listType);
                Log.d("Error", orderModels.toString());
                adapter = new OrderAdapter(getContext(), orderModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}