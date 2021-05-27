package com.example.busvehicletickets.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.busvehicletickets.R;
import com.example.busvehicletickets.dto.TravelDto;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TravelDtoForReservedTicketsAdapter extends ArrayAdapter<TravelDto> {
    private Context mContext;
    private List<TravelDto> travelDtoList;

    public TravelDtoForReservedTicketsAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<TravelDto> list) {
        super(context, 0 , list);
        mContext = context;
        travelDtoList = list;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_reserved_tickets,parent,false);

        TravelDto currentTravel = travelDtoList.get(position);

        TextView fromCity = (TextView)listItem.findViewById(R.id.reserved_textView_from);
        fromCity.setText(currentTravel.getFromCity().toUpperCase());

        TextView toCity = (TextView) listItem.findViewById(R.id.reserved_textView_to);
        toCity.setText(currentTravel.getToCity().toUpperCase());

        TextView leavingTime = (TextView) listItem.findViewById(R.id.reserved_textView_leavingTime);
        leavingTime.setText(currentTravel.getLeavingTime());

        TextView travelDate = (TextView) listItem.findViewById(R.id.reserved_textView_date);
        travelDate.setText(currentTravel.getDate());

        return listItem;
    }
}
