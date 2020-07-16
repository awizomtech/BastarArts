package com.awizomtech.bastararts.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awizomtech.bastararts.R;

public class ProfileFragment extends Fragment {
    TextView UserName,Mobile,Email,Address;
    ImageView Image;
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

    }
}