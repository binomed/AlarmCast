package com.binomed.alarmcast.controllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.binomed.alarmcast.Alarm;
import com.binomed.alarmcast.activities.Reveil;
import com.binomed.alarmcast.db.DBHelper;


/**
 * @author Gawel
 * 
 */
public class AlarmController {
	private Alarm[] alarmes;
	private String[] alarmNames = {};
	private Context contexte;
	
	
	/**
	 * Constructeur AlarmController
	 */
	public AlarmController(Context cont) {
		this.contexte = (Activity) cont;
		
		getAlarms();
	}
	
	public void getAlarms() {
		
		alarmNames = new String[0];
		
		DBHelper db = new DBHelper(this.contexte);
		SQLiteDatabase alarmsDB = db.getReadableDatabase();
		Cursor cursor = alarmsDB.query(DBHelper.ALARMS_TABLE_NAME, DBHelper.ALARMS_FROM, null, null, null, null, android.provider.BaseColumns._ID);
		((Activity)contexte).startManagingCursor(cursor);	
		
		while(cursor.moveToNext()) {
			String alName = cursor.getString(1);
			addString(alName);
		}
		
		((Reveil)contexte).showAlarms(alarmNames);
	}
	
	public void addAlarm() {
		DBHelper db = new DBHelper(this.contexte);
		SQLiteDatabase alarmsDB = db.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(DBHelper.ALARMS_NAME_COL, "Nom");
		values.put(DBHelper.ALARMS_TIME_COL, "Time");
		values.put(DBHelper.ALARMS_REPEATS_COL, "Repeats");
		values.put(DBHelper.ALARMS_TYPE_COL, "Type");
		values.put(DBHelper.ALARMS_ACTIVE_COL, "0");
		alarmsDB.insertOrThrow(DBHelper.ALARMS_TABLE_NAME, null, values);
		
		getAlarms();
	}
	
	public void addString(String t) {
		
		String[] temp = this.alarmNames;
		this.alarmNames = new String[this.alarmNames.length+1];
		for(int i=0;i<temp.length;i++) {
		    this.alarmNames[i] = temp[i];
		}
		this.alarmNames[temp.length] = t;

	}

}
