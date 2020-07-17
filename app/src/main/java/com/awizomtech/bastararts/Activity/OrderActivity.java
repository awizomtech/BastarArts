package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awizomtech.bastararts.AppConfig.AppConfig;
import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

public class OrderActivity extends AppCompatActivity {
    String Pid,Pname,Price,Image,Qid;
    TextView pPrice,ProductName;
    EditText Qty,Et_Address,Et_landMark,Country,State,City,PinCode;
    Button OrderConfirm;
    ImageView ProductImage;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Iniview();
    }

    private void Iniview() {
      Qty =findViewById(R.id.qty);
        pPrice =findViewById(R.id.price);
        ProductName =findViewById(R.id.productname);
        Et_Address =findViewById(R.id.address);
        Et_landMark =findViewById(R.id.landmark);
        Country =findViewById(R.id.country);
        State =findViewById(R.id.state);
        City =findViewById(R.id.city);
        PinCode =findViewById(R.id.pincode);
        OrderConfirm =findViewById(R.id.orderConfirm);
        ProductImage =findViewById(R.id.imageProduct);

        Pid = getIntent().getExtras().getString("Pid");
        Qid = getIntent().getExtras().getString("Qid");
        Pname = getIntent().getExtras().getString("Pname");
        Price = getIntent().getExtras().getString("Price");
        Image = getIntent().getExtras().getString("Image");


        ProductName.setText(Pname.toString());
        Qty.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                int qty;
                if (s.length() > 0) {
                  String no=Qty.getText().toString();
                   qty= Integer.valueOf(no);
                    if (qty == 0) {
                        qty=1;
                        Qty.setText(String.valueOf(1));
                        float price =Float.valueOf(Price);
                        float netprice = price*qty;
                        pPrice.setText(String.valueOf(netprice));

                    }else {
                        float price =Float.valueOf(Price);
                        float netprice = price*qty;
                        pPrice.setText(String.valueOf(netprice));
                    }
                }else {
                    pPrice.setText(Price.toString());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {


            }
        });
        try {
            Glide.with(this).load(AppConfig.BASE_URL + Image.toString()).into(ProductImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Et_Address.getText().toString().isEmpty()){
                    Et_Address.setError("Required !");
                }else if(Et_landMark.getText().toString().isEmpty()){
                    Et_landMark.setError("Required !");
                }
                else if(Country.getText().toString().isEmpty()){
                    Country.setError("Required !");
                }
                else if(State.getText().toString().isEmpty()){
                    State.setError("Required !");
                }
                else if(City.getText().toString().isEmpty()){
                    City.setError("Required !");
                }
                else if(PinCode.getText().toString().isEmpty()){
                    PinCode.setError("Required !");
                }else {
                    String userid = SharedPrefManager.getInstance(OrderActivity.this).getUser().getUserID();
                    String address = Et_Address.getText().toString();
                    String landmark = Et_landMark.getText().toString();
                    String country = Country.getText().toString();
                    String state = State.getText().toString();
                    String city = City.getText().toString();
                    String pincode = PinCode.getText().toString();
                    String qty = Qty.getText().toString();
                    String price = pPrice.getText().toString();
                    String pid = Pid.toString();
                    String qid = Qid.toString();

                    try {
                        result = new UserHelper.PostOrder().execute(userid.toString(),address.toString(), landmark.toString(),country.toString(),state.toString(),city.toString(),pincode.toString(),qty.toString(),price.toString(),pid.toString(),qid.toString()).get();
                        if (result.isEmpty()) {
                            Toast.makeText(OrderActivity.this, "Invalid request", Toast.LENGTH_SHORT).show();
                            result = new UserHelper.PostOrder().execute(userid.toString(),address.toString(), landmark.toString(),country.toString(),state.toString(),city.toString(),pincode.toString(),qty.toString(),price.toString(),pid.toString(),qid.toString()).get();
                        } else {
                            Toast.makeText(OrderActivity.this, "Request Successful Send", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(OrderActivity.this, HomePageActivity.class);
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