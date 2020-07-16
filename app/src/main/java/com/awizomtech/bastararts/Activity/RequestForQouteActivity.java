package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awizomtech.bastararts.AppConfig.AppConfig;
import com.awizomtech.bastararts.Helper.AccountHelper;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

public class RequestForQouteActivity extends AppCompatActivity {
    String ProductName,ProductCode,Image,Pid;
    ImageView imageView;
    TextView Pname,Pcode,Productid;
    EditText Descrip;
    Button Submit;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_qoute);
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
        Descrip =findViewById(R.id.msg);
        imageView=findViewById(R.id.imageProduct);
        Submit=findViewById(R.id.submit);
        Productid=findViewById(R.id.productid);
        ProductName = getIntent().getExtras().getString("Productname");
        ProductCode = getIntent().getExtras().getString("ProductCode");
        Image = getIntent().getExtras().getString("Image");
        Pid = getIntent().getExtras().getString("pid");
        Pname.setText(ProductName.toString());
        Pcode.setText("Product Code : "+ProductCode.toString());
        Productid.setText(Pid.toString());
        try {
            Glide.with(this).load(AppConfig.BASE_URL + Image.toString()).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Descrip.getText().toString().isEmpty()){
                    Descrip.setError("Required !");
                }else {

                    String userid = SharedPrefManager.getInstance(RequestForQouteActivity.this).getUser().getUserID();
                    String pid = Pid.toString();
                    String message=Descrip.getText().toString();
                    try {
                        result = new UserHelper.PostRequestQuote().execute(userid.toString(),pid.toString(), message.toString()).get();
                        if (result.isEmpty()) {

                            Toast.makeText(RequestForQouteActivity.this, "Invalid request", Toast.LENGTH_SHORT).show();
                            result = new UserHelper.PostRequestQuote().execute(userid.toString(),pid.toString(), message.toString()).get();
                        } else {
                            Toast.makeText(RequestForQouteActivity.this, "Request Successful Send", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RequestForQouteActivity.this, HomePageActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    };

                }
            }
        });
    }
}