package com.example.michelle.todomanylists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Michelle on 25-11-2016.
 * Costumized Arrayadapter
 */

public class ToDo_adapter extends ArrayAdapter<ToDo_item> {

    public ToDo_adapter(Context context, ArrayList<ToDo_item> values) {
        super(context, R.layout.row_layout_todo, values);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View  view = inflater.inflate(R.layout.row_layout_todo, parent, false);
        ToDo_item item = getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.todo_TextView);
        textView.setText(item.toString());

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox.setChecked(item.is_checked);

        return view;
    }
}

