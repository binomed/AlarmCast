package com.binomed.alarmcast.controllers;

import android.app.Activity;
import android.content.Context;

import com.binomed.alarmcast.Alarm;

public class AlarmSettingsController {
	private Alarm alarm;
	private Context contexte;
	
	public AlarmSettingsController(Context cont) {
		this.contexte = (Activity) cont;
		alarm = new Alarm();		
	}
}
