package com.sanju.sachin.dipscounter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class detailsAdapter extends ArrayAdapter<details> {

    detailsAdapter(Activity context, ArrayList<details> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        //Get the one object located position in the list
        details currentName = getItem(position);

        //Find the TextView in the list_item.xml layout with the ID
        TextView oneTextView = listItemView.findViewById(R.id.show_number_of_dips_list_view);
        TextView twoTextView = listItemView.findViewById(R.id.show_current_date_list_view);
        //Get First input  from the current
        assert currentName != null;
        oneTextView.setText(currentName.getNumberOfDips());
        twoTextView.setText(currentName.getDate());
        return listItemView;
    }
}