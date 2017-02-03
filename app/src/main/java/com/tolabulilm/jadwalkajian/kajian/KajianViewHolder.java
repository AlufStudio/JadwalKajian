package com.tolabulilm.jadwalkajian.kajian;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tolabulilm.jadwalkajian.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class KajianViewHolder extends RecyclerView.ViewHolder {
    private final TextView ustadz;
    private final TextView title;
    private final TextView place;
    private final TextView date;
    private final Button addButton;
    private final Button moreButton;
    private final TextView address;
    private final TextView contactNumber;

    public KajianViewHolder(View itemView) {
        super(itemView);
        ustadz = (TextView)itemView.findViewById(R.id.card_kajian_ustadz);
        title = (TextView)itemView.findViewById(R.id.card_kajian_title);
        place = (TextView)itemView.findViewById(R.id.card_kajian_place);
        date = (TextView)itemView.findViewById(R.id.card_kajian_date);
        addButton = (Button)itemView.findViewById(R.id.card_kajian_add_button);
        moreButton = (Button)itemView.findViewById(R.id.card_kajian_more_button);
        address = (TextView)itemView.findViewById(R.id.card_kajian_address);
        contactNumber = (TextView)itemView.findViewById(R.id.card_kajian_contactnumber);
    }

    public void setPrimaryInfo(String ustadz, String title, String place, String hijri, long millis) {
        this.ustadz.setText(ustadz);
        this.title.setText(title);
        this.place.setText(place);
        String dateText = "";
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millis);
        Date date = c.getTime();
        if (hijri.length() != 0) {
            dateText = hijri + " / ";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
        dateText = dateText + format.format(date);
        this.date.setText(dateText);
        //gunakan simple date format untuk mendapatkan tanggal dari calendar;
    }

    public Button getAddButton() {
        return this.addButton;
    }

    public Button getMoreButton() {
        return this.moreButton;
    }

    public void setAddtitionalInfo(String address, String contactNumber) {
        this.address.setText(address);
        this.contactNumber.setText(contactNumber);
    }
}
