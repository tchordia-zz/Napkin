package com.tanmaychordia.napkin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tanmay on 2/20/15.
 */
public class ProfProjectAdapter extends ArrayAdapter<Project> {

    private final Context context;
    private final List<Project> values;

    public ProfProjectAdapter(Context context, List<Project> values) {
        super(context, R.layout.list_tile, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_tile, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textProjectName);

        textView.setText(values.get(position).getName());

        textView = (TextView) rowView.findViewById(R.id.projectTypeText);
        textView.setText(values.get(position).getAppType());

        textView = (TextView) rowView.findViewById(R.id.projectDescriptionId);
        textView.setText(values.get(position).getDescription());


        return rowView;
    }
}
