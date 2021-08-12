package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterShopping extends ArrayAdapter<Shopping> {
    private Context context;
    private int layout;
    private List<Shopping> list;

    public AdapterShopping(@NonNull Context context, int resource, @NonNull List<Shopping> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(context).inflate(layout,null);
        }

        Shopping s = list.get(position);

        TextView txtDate = item.findViewById(R.id.itemDate);
        TextView txtName = item.findViewById(R.id.itemName);
        TextView txtPrice = item.findViewById(R.id.itemPrice);
        TextView txtCount = item.findViewById(R.id.itemCount);
        TextView txtTotal = item.findViewById(R.id.itemTotalPrice);

        NumberFormat fmtC = NumberFormat.getCurrencyInstance();
        NumberFormat fmtN = NumberFormat.getNumberInstance();

        Calendar c = Calendar.getInstance();
        c.setTime(s.getDateBought());
        txtDate.setText(c.get(Calendar.DAY_OF_MONTH)+"\n"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));
        txtName.setText(s.getName());
        txtPrice.setText(fmtC.format(s.getPrice()));
        txtCount.setText(fmtN.format(s.getCount()));
        txtTotal.setText(fmtC.format(s.getPrice() * s.getCount()));

        return item;
    }
}
