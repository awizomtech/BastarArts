package com.awizomtech.bastararts.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awizomtech.bastararts.Activity.HomePageActivity;
import com.awizomtech.bastararts.Activity.LoginActivity;
import com.awizomtech.bastararts.Helper.AccountHelper;
import com.awizomtech.bastararts.Model.LoginModel;
import com.awizomtech.bastararts.Model.ProfileModel;
import com.awizomtech.bastararts.R;
import com.awizomtech.bastararts.SharedPreference.SharedPrefManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class ProfileFragment extends Fragment {
    TextView UserName,Mobile,Email,Address;
    de.hdodenhof.circleimageview.CircleImageView Image;
    String result;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        Initview();
        return root;
    }

    private void Initview() {
        UserName =root.findViewById(R.id.userName);
        Mobile =root.findViewById(R.id.mobNo);
        Email =root.findViewById(R.id.emailAddress);
        Address =root.findViewById(R.id.address);
        Image =root.findViewById(R.id.userImage);
        try {
           String userid= SharedPrefManager.getInstance(getContext()).getUser().getUserID();
            result = new AccountHelper.GetProfile().execute(userid.toString()).get();
            String first = result.split(":")[1];
            String second = first.split(",")[0];
            if (second.contains("null")) {
                Toast.makeText(getContext(), "Invalid request", Toast.LENGTH_SHORT).show();
            } else {

                Type listType = new TypeToken<ProfileModel>() {
                }.getType();
                ProfileModel profileModel = new Gson().fromJson(result, listType);
                UserName.setText(profileModel.getName().toString());
                Mobile.setText(profileModel.getMobile().toString());
                Email.setText(profileModel.getEmail().toString());
                Address.setText(profileModel.getAddress().toString());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}