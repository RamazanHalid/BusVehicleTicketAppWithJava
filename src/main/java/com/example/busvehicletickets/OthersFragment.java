package com.example.busvehicletickets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

public class OthersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.others_frag_layout, container, false);


        TextView settingsTextView = (TextView) view.findViewById(R.id.others_settings);
        settingsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchResultActivity.class);
                startActivity(intent);

            }
        });

        TextView profileTextView = (TextView) view.findViewById(R.id.others_profile);
        profileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    startActivity(intent);
            }
        });

        TextView contactUsTextView = (TextView) view.findViewById(R.id.others_contactUs);
        contactUsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView logoutTextView = (TextView) view.findViewById(R.id.others_logout);
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
