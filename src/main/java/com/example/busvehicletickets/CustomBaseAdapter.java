package com.example.busvehicletickets;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.busvehicletickets.dto.TicketDto;
import com.example.busvehicletickets.dto.TravelDto;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
    private static ArrayList<TravelDto> ticketDtos;

    private LayoutInflater mInflater;

    public CustomBaseAdapter(Context context, ArrayList<TravelDto> results) {
        ticketDtos = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return ticketDtos.size();
    }

    public Object getItem(int position) {
        return ticketDtos.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_row_view, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtCityState = (TextView) convertView
                    .findViewById(R.id.cityState);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.phone);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(ticketDtos.get(position).getFromCity() + "asdfs");
        holder.txtCityState.setText(ticketDtos.get(position).getToCity());
        holder.txtPhone.setText(ticketDtos.get(position).getPrice());
        holder.txtPhone.setText(ticketDtos.get(position).getChairNumber());
        holder.txtPhone.setText(ticketDtos.get(position).getDistance());
        holder.txtPhone.setText(ticketDtos.get(position).getDate());
        holder.txtPhone.setText(ticketDtos.get(position).getTravelTime());
        holder.txtPhone.setText(ticketDtos.get(position).getLeavingTime());

        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtCityState;
        TextView txtPhone;
    }
}