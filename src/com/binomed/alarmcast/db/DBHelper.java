package com.binomed.alarmcast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;

public class DBHelper extends SQLiteOpenHelper {
	// Base de données
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "alarmcast";
	
	// Table "ALARMS"
	public static final String ALARMS_TABLE_NAME = "alarms";
	public static final String ALARMS_NAME_COL = "name";
	public static final String ALARMS_TIME_COL = "time";
	public static final String ALARMS_REPEATS_COL = "repeats";
	public static final String ALARMS_TYPE_COL = "type";
	public static final String ALARMS_ACTIVE_COL = "active";
	public static final String[] ALARMS_FROM = {_ID, ALARMS_NAME_COL, ALARMS_TIME_COL, ALARMS_REPEATS_COL, ALARMS_TYPE_COL, ALARMS_ACTIVE_COL };
	private static final String ALARMS_TABLE_CREATE =  "CREATE TABLE " + ALARMS_TABLE_NAME + " (" +
														_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
														ALARMS_NAME_COL + " TEXT, " +
														ALARMS_TIME_COL + " TEXT, " +
														ALARMS_REPEATS_COL + " TEXT, " +
														ALARMS_TYPE_COL + " TEXT, " +
														ALARMS_ACTIVE_COL + " TEXT);";
	
	// Insertions
	private static final String INSERT_ALARM = "INSERT INTO " + ALARMS_TABLE_NAME // 
								+ " (" + ALARMS_NAME_COL + ", " + ALARMS_TIME_COL + ", " + ALARMS_REPEATS_COL + ", " + ALARMS_TYPE_COL + ", " + 
								ALARMS_ACTIVE_COL + ") " 
								+ " VALUES (?,?,?,?,?)";
	
	/**
	 * Constructeur du DBHelper
	 * @param context Contexte
	 */
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ALARMS_TABLE_CREATE);
    }


	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
