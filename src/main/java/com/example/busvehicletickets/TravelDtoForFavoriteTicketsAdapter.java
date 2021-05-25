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

import com.example.busvehicletickets.dto.TravelDto;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TravelDtoForFavoriteTicketsAdapter extends ArrayAdapter<TravelDto> {
    private Context mContext;
    private List<TravelDto> travelDtoList;

    public TravelDtoForFavoriteTicketsAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<TravelDto> list) {
        super(context, 0 , list);
        mContext = context;
        travelDtoList = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_favorite_tickets,parent,false);

        TravelDto currentTravel = travelDtoList.get(position);

        TextView fromCity = (TextView)listItem.findViewById(R.id.favorite_textView_from);
        fromCity.setText(currentTravel.getFromCity().toUpperCase());

        TextView toCity = (TextView) listItem.findViewById(R.id.favorite_textView_to);
        toCity.setText(currentTravel.getToCity().toUpperCase());

        TextView leavingTime = (TextView) listItem.findViewById(R.id.favorite_textView_leavingTime);
        leavingTime.setText(currentTravel.getLeavingTime());

        TextView travelDate = (TextView) listItem.findViewById(R.id.favorite_textView_date);
        travelDate.setText(currentTravel.getDate());

        return listItem;
    }
}
