package com.example.busvehicletickets.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.busvehicletickets.R;

import org.jetbrains.annotations.Nullable;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_frag_layout, container, false);
        TextView favoriteTicketsTextView = (TextView) view.findViewById(R.id.menu_favoriteTickets);
        favoriteTicketsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView boughtTicketsTextView = (TextView) view.findViewById(R.id.menu_boughtTickets);
        boughtTicketsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView reservationTicketsTextView = (TextView) view.findViewById(R.id.menu_reservations);
        reservationTicketsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        TextView previousTravelsTextView = (TextView) view.findViewById(R.id.menu_previousTravels);
        previousTravelsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView canceledTicketsTextView = (TextView) view.findViewById(R.id.menu_canceledTickets);
        canceledTicketsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}