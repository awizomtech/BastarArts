package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.awizomtech.bastararts.Helper.AccountHelper;
import com.awizomtech.bastararts.Model.LoginModel;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    EditText Username, Password;
    TextView ForgetPassword, Register;
    Button Login;
    String result;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
        InitView();
    }

    private void InitView() {
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        ForgetPassword = findViewById(R.id.forgetpassword);
        Register = findViewById(R.id.register);
        Login = findViewById(R.id.login);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Username.getText().toString().isEmpty() || Username.getText().toString().length() != 10) {
                    Username.setError("Required !");
                } else if (Password.getText().toString().isEmpty() || Password.getText().toString().length() != 6) {
                    Password.setError("Required !");
                } else {

                    progressDialog.show();

                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        String username = Username.getText().toString();
                                        String password = Password.getText().toString();
                                        result = new AccountHelper.LogIn().execute(username.toString(), password.toString()).get();
                                        String first = result.split(":")[1];
                                        String second = first.split(",")[0];
                                        if (second.contains("null")) {
                                            progressDialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "Invalid request", Toast.LENGTH_SHORT).show();
                                        } else {
                                            progressDialog.dismiss();
                                            Type listType = new TypeToken<LoginModel>() {
                                            }.getType();
                                            LoginModel loginModel = new Gson().fromJson(result, listType);
                                            String userid = String.valueOf(loginModel.getUserID());
                                            String customername = String.valueOf(loginModel.getName());
                                            String customerid = String.valueOf(loginModel.getCustomerID());
                                            if (!userid.equals("null")) {
                                                LoginModel loginmodel1 = new LoginModel();
                                                loginmodel1.UserID = userid.toString();
                                                loginmodel1.Name = customername.toString();
                                                loginmodel1.CustomerID = Integer.valueOf(customerid);
                                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(loginmodel1);
                                                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getApplicationContext(), "User ID or Password are not Correct", Toast.LENGTH_LONG).show();
                                            }
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
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}