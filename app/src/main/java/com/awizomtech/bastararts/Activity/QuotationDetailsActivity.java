package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.awizomtech.bastararts.AppConfig.AppConfig;
import com.awizomtech.bastararts.R;
import com.bumptech.glide.Glide;

public class QuotationDetailsActivity extends AppCompatActivity {
String Pid,Qid,Rid,Pcode,Pname,Price,Qno,Image,Remark,Name,Mobile,Email,Address,Date;
TextView ProductCode,ProductName,ProductPrice,QuotationNo,ProductRemark,ProductDate;
ImageView productImage;
Button OrderNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_details);
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
        ProductCode=findViewById(R.id.productCode);
        ProductName=findViewById(R.id.productName);
        ProductPrice=findViewById(R.id.productPrice);
        QuotationNo=findViewById(R.id.quotationNo);
        ProductRemark=findViewById(R.id.productRemark);
        productImage=findViewById(R.id.image);
        OrderNow=findViewById(R.id.order);
        ProductDate=findViewById(R.id.date);

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
       String newDate=Date.split("T")[0];
        ProductDate.setText("Date : "+newDate.toString());
        ProductCode.setText("Product Code : "+Pcode.toString());
        ProductName.setText("Product Name : "+Pname.toString());
        ProductPrice.setText("Product Price : "+Price.toString());
        QuotationNo.setText("Quotation Number : "+Qno.toString());
        ProductRemark.setText("Product Remark : "+Remark.toString());
        try {
            Glide.with(this).load(AppConfig.BASE_URL + Image.toString()).into(productImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}