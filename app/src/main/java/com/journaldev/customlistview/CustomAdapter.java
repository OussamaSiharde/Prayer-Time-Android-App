package com.journaldev.customlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView date;
        TextView fajr;
        TextView sunrise;
        TextView dhuhr;
        TextView asr;
        TextView maghrib;
        TextView isha;
    }


    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;

    }


    @Override
    public void onClick(View v) {


        int position = (Integer) v.getTag();
        Object object = getItem(position);
        DataModel dataModel = (DataModel) object;


    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater;
            inflater = from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.fajr = (TextView) convertView.findViewById(R.id.fajr);
            viewHolder.sunrise = (TextView) convertView.findViewById(R.id.sunrise);
            viewHolder.dhuhr = (TextView) convertView.findViewById(R.id.dhuhr);
            viewHolder.asr = (TextView) convertView.findViewById(R.id.asr);
            viewHolder.maghrib = (TextView) convertView.findViewById(R.id.maghrib);
            viewHolder.isha = (TextView) convertView.findViewById(R.id.isha);

            // pass value to layout
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        if (dataModel != null) {
            viewHolder.date.setText(dataModel.getDate());
            viewHolder.fajr.setText(dataModel.getFajr());
            viewHolder.sunrise.setText(dataModel.getSunrise());
            viewHolder.dhuhr.setText(dataModel.getDhuhr());
            viewHolder.asr.setText(dataModel.getAsr());
            viewHolder.maghrib.setText(dataModel.getMaghrib());
            viewHolder.isha.setText(dataModel.getIsha());
        }

        return convertView;
    }


}
