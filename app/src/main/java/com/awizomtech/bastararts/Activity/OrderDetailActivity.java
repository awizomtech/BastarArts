package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.awizomtech.bastararts.R;

public class OrderDetailActivity extends AppCompatActivity {
    String OrderID, Quantity, OrderNo, OrderDate, TotalAmount, IsPaymentDone,
            ShippingCharge, AddressLineOne, Status, Landmark, PinCode, Country, State, City, ProductID, ProductCode, ProductName, ProductImage, Description;
    TextView Orderno, Orderdate, Shippingcharge, Totalamount, Productname, Productcode, Qty, Address1, Address2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Initview();
    }

    private void Initview() {
        Button backpress=findViewById(R.id.back);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Orderno = findViewById(R.id.orderNo);
        Orderdate = findViewById(R.id.date);
        Shippingcharge = findViewById(R.id.shippingcharge);
        Totalamount = findViewById(R.id.productprice);
        Productname = findViewById(R.id.productname);
        Productcode = findViewById(R.id.productcode);
        Qty = findViewById(R.id.productqty);
        Address1 = findViewById(R.id.addresslineOne);
        Address2 = findViewById(R.id.AddresslineTwo);

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
        String date = OrderDate.split("T")[0];
        Orderno.setText(OrderNo.toString());
        Orderdate.setText( date.toString());
        Shippingcharge.setText(ShippingCharge.toString());
        Totalamount.setText(TotalAmount.toString()+" ₹");
        Productname.setText(ProductName.toString());
        Productcode.setText(ProductCode.toString());
        Qty.setText(Quantity.toString());
        Address1.setText(AddressLineOne.toString()+" "+Landmark.toString());
        Address2.setText(Country.toString()+" "+State.toString()+" "+City.toString()+" "+PinCode.toString());


    }
}