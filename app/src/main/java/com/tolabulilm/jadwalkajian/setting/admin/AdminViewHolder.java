package com.tolabulilm.jadwalkajian.setting.admin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tolabulilm.jadwalkajian.R;
import com.tolabulilm.jadwalkajian.kajian.Kajian;

/**
 * Created by fata on 2/4/2017.
 */

public class AdminViewHolder extends RecyclerView.ViewHolder {

    private final TextView ustadz;
    private final TextView title;
    private final TextView place;
    private final TextView time;

    public AdminViewHolder(View itemView) {
        super(itemView);
        ustadz = (TextView)itemView.findViewById(R.id.card_admin_ustadz);
        title = (TextView)itemView.findViewById(R.id.card_admin_title);
        place = (TextView)itemView.findViewById(R.id.card_admin_place);
        time = (TextView)itemView.findViewById(R.id.card_admin_time);
    }

    public void setView(Kajian kajian) {
        this.ustadz.setText(kajian.getUstadz());
        this.title.setText(kajian.getTitle());
        this.place.setText(kajian.getPlace());
    }
}
