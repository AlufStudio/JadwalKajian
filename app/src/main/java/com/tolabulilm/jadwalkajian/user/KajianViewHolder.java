package com.tolabulilm.jadwalkajian.user;

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
}
