package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.awizomtech.bastararts.AppConfig.AppConfig;
import com.awizomtech.bastararts.R;
import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {
String ProductName,ProductCode,Description,Image,Pid,Cid;
ImageView imageView;
TextView Pname,Pcode,Descrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
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
        Pname=findViewById(R.id.productname);
        Pcode=findViewById(R.id.productcode);
        Descrip =findViewById(R.id.description);
        imageView=findViewById(R.id.imageProduct);
        ProductName = getIntent().getExtras().getString("Productname");
        ProductCode = getIntent().getExtras().getString("ProductCode");
        Description = getIntent().getExtras().getString("Description");
        Image = getIntent().getExtras().getString("Image");
        Pid = getIntent().getExtras().getString("pid");
        Cid = getIntent().getExtras().getString("Cid");
        Pname.setText(ProductName.toString());
        Pcode.setText("Product Code : "+ProductCode.toString());
        Descrip.setText(Description.toString());
        try {
            Glide.with(this).load(AppConfig.BASE_URL + Image.toString()).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}