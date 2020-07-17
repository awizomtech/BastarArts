package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.awizomtech.bastararts.R;

public class OrderDetailActivity extends AppCompatActivity {
String OrderID,Quantity,OrderNo,OrderDate,TotalAmount,IsPaymentDone,
        ShippingCharge,AddressLineOne,Status,Landmark,PinCode,Country,State,City,ProductID,ProductCode,ProductName,ProductImage,Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Initview();
    }

    private void Initview() {
        OrderID = getIntent().getExtras().getString("OrderID");
        Quantity = getIntent().getExtras().getString("Quantity");
        OrderNo = getIntent().getExtras().getString("OrderNo");
        OrderDate = getIntent().getExtras().getString("OrderDate");
        TotalAmount = getIntent().getExtras().getString("TotalAmount");
        IsPaymentDone = getIntent().getExtras().getString("IsPaymentDone");
        ShippingCharge = getIntent().getExtras().getString("ShippingCharge");
        AddressLineOne = getIntent().getExtras().getString("AddressLineOne");
        Status = getIntent().getExtras().getString("Status");
        Landmark = getIntent().getExtras().getString("Landmark");
        PinCode = getIntent().getExtras().getString("PinCode");
        Country = getIntent().getExtras().getString("Country");
        State = getIntent().getExtras().getString("State");
        City = getIntent().getExtras().getString("City");
        ProductID = getIntent().getExtras().getString("ProductID");
        ProductCode = getIntent().getExtras().getString("ProductCode");
        ProductName = getIntent().getExtras().getString("ProductName");
        ProductImage = getIntent().getExtras().getString("ProductImage");
        Description = getIntent().getExtras().getString("Description");
    }
}