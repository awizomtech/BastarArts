package com.awizomtech.bastararts.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.awizomtech.bastararts.Activity.QuoteListActivity;
import com.awizomtech.bastararts.Adapter.QuotationAdapter;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.Model.QuotationModel;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MyRequestFragment extends Fragment {
    QuotationAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<QuotationModel> quotationModels;
    private String result = "";
    ProgressDialog progressDialog;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_my_request, container, false);
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
                GetQuotationlist();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        GetQuotationlist();

    }

    private void GetQuotationlist() {
        try {
            String userid = SharedPrefManager.getInstance(getContext()).getUser().getUserID();
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
                adapter = new QuotationAdapter(getContext(), quotationModels);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}