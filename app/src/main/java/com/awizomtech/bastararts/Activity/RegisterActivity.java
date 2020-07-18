package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.awizomtech.bastararts.Helper.AccountHelper;
import com.awizomtech.bastararts.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {
    EditText firtname,Mobile,emailaddress,conpass,password,Address;
    Button ll_submit;
    TextView login;
    String result;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
        InitView();
    }
    private void InitView() {
        firtname=findViewById(R.id.name);
        password = findViewById(R.id.password);
        login=findViewById(R.id.login);
        emailaddress=findViewById(R.id.email);
        conpass=findViewById(R.id.conpassword);
        Mobile=findViewById(R.id.mobile);
        Address=findViewById(R.id.address);
        ll_submit=findViewById(R.id.registerbtn);

        ll_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpass=conpass.getText().toString();
                String pass=password.getText().toString();
                if(firtname.getText().toString().isEmpty()){
                    firtname.setError("Required !");
                }else if(emailaddress.getText().toString().isEmpty()){
                    emailaddress.setError("Required !");
                }
                else if(Address.getText().toString().isEmpty()){
                    Address.setError("Required !");
                }
                else if(Mobile.getText().toString().isEmpty()||Mobile.getText().toString().length()!=10){
                    Mobile.setError("Required !");
                }
                else if(password.getText().toString().isEmpty()||password.getText().toString().length()!=6){
                    password.setError("Required !");
                }else if(conpass.getText().toString().isEmpty()){
                    conpass.setError("Required !");
                }else if(!pass.contains(cpass)){
                    password.setError("Password are not match !");
                }
                else {

                    progressDialog.show();

                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            RegisterActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                            String name = firtname.getText().toString();
                            String passw = password.getText().toString();
                            String mob = Mobile.getText().toString();
                            String email = emailaddress.getText().toString();
                            String address = Address.getText().toString();
                            try {
                                result = new AccountHelper.PostRegister().execute(name.toString(),mob.toString(), email.toString(),address.toString(),passw.toString()).get();
                                if (result.isEmpty()) {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Invalid request", Toast.LENGTH_SHORT).show();
                                    result = new AccountHelper.PostRegister().execute(name.toString(),mob.toString(), email.toString(),address.toString(),passw.toString()).get();
                                } else {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                                }

                            });
                        }

                    }, 500);
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}