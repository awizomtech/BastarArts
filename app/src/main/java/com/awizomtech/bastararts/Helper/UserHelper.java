package com.awizomtech.bastararts.Helper;

import android.os.AsyncTask;

import com.awizomtech.bastararts.AppConfig.AppConfig;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class UserHelper {
    public static final class GetProductList extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

//            String userid = params[0];

            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"GetProductList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }

    public static final class GetProductCatList extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

//            String userid = params[0];

            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"GetProductCatList");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
    public static final class GetCatBaseProductList extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

          String cid = params[0];

            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"GetCatBaseProductList?"+"CategoryID="+cid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
    public static final class PostRequestQuote extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            //     InputStream inputStream
            String userid = params[0];
            String pid = params[1];
            String message = params[2];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"Requestforquote");
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();
                parameters.add("UserID", userid);
                parameters.add("ProductID", pid);
                parameters.add("Message", message);
                builder.post(parameters.build());

                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
    public static final class GetQuoteList extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"GetQuoteList?"+"UserID="+userid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
    public static final class PostOrder extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            //     InputStream inputStream
            String userid = params[0];
            String address = params[1];
            String landmark = params[2];
            String country = params[3];
            String state = params[4];
            String city = params[5];
            String pincode = params[6];
            String qty = params[7];
            String price = params[8];
            String pid = params[9];
            String qid = params[10];

            String json = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
           /*     builder.url(AppConfig.BASE_URL_API+"PostOrder");*/
                builder.url(AppConfig.BASE_URL_API+"PostOrder?"+"UserID="+userid+"&Address="+address+"&Landmark="+landmark+"&Country="+country+"&State="+state+"&City="+city+"&PinCode="+pincode+"&Quantity="+qty+"&TotalAmount="+price+"&ProductID="+pid+"&QuotationID="+qid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();
               /* parameters.add("UserID", userid);
                parameters.add("Address", address);
                parameters.add("Landmark", landmark);
                parameters.add("Country", country);
                parameters.add("State", state);
                parameters.add("City", city);
                parameters.add("PinCode", pincode);
                parameters.add("Quantity", qty);
                parameters.add("TotalAmount", price);
                parameters.add("ProductID", pid);
                parameters.add("QuotationID", qid);*/
                builder.post(parameters.build());

                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
    public static final class GetOrderList extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"GetOrderList?"+"UserID="+userid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
    public static final class PostPaymnet extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String orderid = params[1];
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"Payment?"+"TransactionID="+userid+"&OrderID="+orderid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                FormBody.Builder parameters = new FormBody.Builder();
                builder.post(parameters.build());
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
            }
        }

    }

    public static final class GetPaymentList extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            String userid = params[0];
            String json = "";
            try {

                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                builder.url(AppConfig.BASE_URL_API+"GetPaymnetList?"+"UserID="+userid);
                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                builder.addHeader("Accept", "application/json");
                okhttp3.Response response = client.newCall(builder.build()).execute();
                if (response.isSuccessful()) {
                    json = response.body().string();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

        protected void onPostExecute(String result) {

            if (result.isEmpty()) {

            } else {
                super.onPostExecute(result);
//
            }


        }

    }
}
