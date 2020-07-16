package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.awizomtech.bastararts.R;

public class QuotationDetailsActivity extends AppCompatActivity {
String Pid,Qid,Rid,Pcode,Pname,Price,Qno,Image,Remark,Name,Mobile,Email,Address,Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_details);
        InitView();
    }

    private void InitView() {
        Pid = getIntent().getExtras().getString("Pid");
        Qid = getIntent().getExtras().getString("Qid");
        Rid = getIntent().getExtras().getString("Rid");
        Pcode = getIntent().getExtras().getString("Pcode");
        Pname = getIntent().getExtras().getString("Pname");
        Price = getIntent().getExtras().getString("Price");
        Qno = getIntent().getExtras().getString("Qno");
        Image = getIntent().getExtras().getString("Image");
        Remark = getIntent().getExtras().getString("Remark");
        Name = getIntent().getExtras().getString("Name");
        Mobile = getIntent().getExtras().getString("Mobile");
        Email = getIntent().getExtras().getString("Email");
        Address = getIntent().getExtras().getString("Address");
        Date = getIntent().getExtras().getString("Date");

    }
}