package com.awizomtech.bastararts.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awizomtech.bastararts.Helper.UserHelper;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends Activity implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
String OrderId;
String result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());
        Button button = (Button) findViewById(R.id.btn_pay);
        Button backpress=findViewById(R.id.back);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        OrderId = getIntent().getExtras().getString("OrderId");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Bastar Arts");
            options.put("description", "Online Product Selling Service");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@gmail.com");
            preFill.put("contact", "9575081924");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            String orderid =OrderId.toString();
            String userid= razorpayPaymentID.toString();
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            result = new UserHelper.PostPaymnet().execute(userid.toString(),orderid.toString()).get();
            if (result.isEmpty()) {
            } else {
                Toast.makeText(PaymentActivity.this, "Request Successful Send", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this, OrderListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            String orderid =OrderId.toString();
            String userid= response.toString();
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
            result = new UserHelper.PostPaymnet().execute(userid.toString(),orderid.toString()).get();
            if (result.isEmpty()) {
           } else {
                Toast.makeText(PaymentActivity.this, "Request Successful Send", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this, OrderListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}
