package com.tolabulilm.jadwalkajian.setting;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tolabulilm.jadwalkajian.R;

/**
 * Created by fata on 1/18/2017.
 */

public class SettingListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final int[] imageId;

    public SettingListAdapter(Activity context, String[] title, int[] imageId ) {
        super(context, R.layout.setting_item, title);
        this.context = context;
        this.title = title;
        this.imageId = imageId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.setting_item, null, true);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.setting_icon);
        TextView textView = (TextView)rowView.findViewById(R.id.setting_title);
        imageView.setImageResource(imageId[position]);
        textView.setText(title[position]);
        return rowView;
    }
}
