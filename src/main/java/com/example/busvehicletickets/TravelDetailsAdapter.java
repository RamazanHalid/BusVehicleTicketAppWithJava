package com.example.busvehicletickets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TravelDetailsAdapter extends ArrayAdapter<TravelDetails> {
    private Context mContext;
    private List<TravelDetails> travelDetailsList = new ArrayList<>();

    public TravelDetailsAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<TravelDetails> list) {
        super(context, 0 , list);
        mContext = context;
        travelDetailsList = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_results,parent,false);

        TravelDetails currentTravel = travelDetailsList.get(position);

        TextView leavingTime = (TextView)listItem.findViewById(R.id.textView_leavingTime);
        leavingTime.setText(currentTravel.getLeavingTime());

        TextView distance = (TextView) listItem.findViewById(R.id.textView_distance);
        distance.setText(currentTravel.getDistance());

        TextView travelTime = (TextView) listItem.findViewById(R.id.textView_travelTime);
        travelTime.setText(currentTravel.getTravelTime());

        TextView price = (TextView) listItem.findViewById(R.id.textView_price);
        price.setText(currentTravel.getPrice());

        return listItem;
    }
}
