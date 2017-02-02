package com.tolabulilm.jadwalkajian.kajian;

public class TimePickDialog extends DialogFragment implements
    TimePickerDialog.onTimeSetListener {

    int hour;
    int minute;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()))
    }

    @Override
    public void onTimeSet(TimerPicker view, int hour, int minute) {
        
    }
}
