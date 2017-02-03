package com.tolabulilm.jadwalkajian.donasi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tolabulilm.jadwalkajian.R;

public class DonasiViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView description;
    private final TextView donated;
    private final TextView needed;
    private final TextView bankName;
    private final TextView bankNumber;
    private final TextView accName;

    public DonasiViewHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.card_donasi_title);
        description = (TextView)itemView.findViewById(R.id.card_donasi_description);
        donated = (TextView)itemView.findViewById(R.id.card_donasi_donated);
        needed = (TextView)itemView.findViewById(R.id.card_donasi_needed);
        bankName = (TextView)itemView.findViewById(R.id.card_donasi_bank_name);
        bankNumber = (TextView)itemView.findViewById(R.id.card_donasi_bank_number);
        accName = (TextView)itemView.findViewById(R.id.card_donasi_acc_name);
    }

    public void setView(Donasi donasi) {
        this.title.setText(donasi.getTitle());
        this.description.setText(donasi.getDescription());
        this.donated.setText(donasi.getDonated());
        this.needed.setText(donasi.getNeeded());
        this.bankName.setText(donasi.getBankName());
        this.bankNumber.setText(donasi.getBankNumber());
        this.accName.setText(donasi.getAccName());
    }

    public void setProgressBar(int donated, int needed) {
        //buat progressbar pake library luar
    }
}
