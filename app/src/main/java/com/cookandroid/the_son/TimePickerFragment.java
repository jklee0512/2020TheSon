package com.cookandroid.the_son;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private AlarmManager mAlarmManager;

   @NonNull
   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState){

       Calendar c = Calendar.getInstance();
       int hour = c.get(Calendar.HOUR_OF_DAY);
       int minute = c.get(Calendar.MINUTE);

       return new TimePickerDialog(getContext(), this, hour, minute, DateFormat.is24HourFormat(getContext()));
   }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
       Calendar calendar = Calendar.getInstance();
       calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
       calendar.set(Calendar.MINUTE, minute);

    }
}
