package com.binomed.alarmcast.activities;

import com.binomed.alarmcast.R;
import com.binomed.alarmcast.controllers.AlarmController;
import com.binomed.alarmcast.controllers.AlarmSettingsController;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.TimePicker;

public class AlarmSettingsActivity extends PreferenceActivity
		implements TimePickerDialog.OnTimeSetListener,
		Preference.OnPreferenceChangeListener {
	
	private AlarmSettingsController controleur;
	
	public void onCreate(Bundle savedInstanceState) {   	
    	super.onCreate(savedInstanceState);
        
    	setContentView(R.layout.alarm_settings_activity);
        addPreferencesFromResource(R.xml.alarm_settings);
        
        controleur = new AlarmSettingsController(this);
	}

	@Override
	public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onPreferenceChange(Preference arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
