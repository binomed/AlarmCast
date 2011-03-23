package com.binomed.alarmcast.activities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import com.binomed.alarmcast.Alarm;
import com.binomed.alarmcast.AlarmReceiver;
import com.binomed.alarmcast.R;
import com.binomed.alarmcast.R.id;
import com.binomed.alarmcast.R.layout;
import com.binomed.alarmcast.adapter.AlarmListAdapter;
import com.binomed.alarmcast.controllers.AlarmController;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Reveil extends Activity implements OnItemClickListener {
    public final static int ALARM_ID = 1234567;
    static Alarm alarm;
    
    private Button clickme;
    private ListView alarmList;
    private AlarmController controleur;
    
    private String [] listInfo = {"a","b","c"};
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//Chargement des informations du reveil
    	//charger();
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        // Création du contrôleur
        controleur = new AlarmController(this);
        
        //clickme = (Button) findViewById(R.id.Button01);
        //clickme.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { playMp3(); } });
        
        //Affichage 
        //affichage();
        
        //Planification
        //planifierAlarm();
    }
    
    public void showAlarms(String[] alarmes) {
    	alarmList = (ListView) findViewById(R.id.AlarmList);
        alarmList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alarmes));
    }
    
    
    private void affichage() {
    	//Ici on a juste voulu créer un affichage de l'heure qui soit au format hh:mm.
		String heureReveil = "";
		heureReveil += alarm.getHeure().hour >10 ? alarm.getHeure().hour : "0" + alarm.getHeure().hour;
		heureReveil +=":";
		heureReveil += alarm.getHeure().minute >10 ? alarm.getHeure().minute : "0" + alarm.getHeure().minute;
		//CheckBox ck_alarm = (CheckBox)findViewById(R.id.heure);
		/*ck_alarm.setText(heureReveil);
		ck_alarm.setChecked(alarm.isActive());*/
	}
	
    /*
     * changeHeure se déclenche automatiquement au click sur l'heure ou la CheckBox.
     * Active ou désactive le reveil.
     * Affiche un dialog pour choisir l'heure de reveil
     */
    public void changeHeure(View target){
		/*CheckBox ck_alarm = (CheckBox)findViewById(R.id.heure);
		
		//Si on active l'alarm alors on veut choisir l'heure.
		if(ck_alarm.isChecked()){
	    	TimePickerDialog dialog = new TimePickerDialog(this, this, alarm.getHeure().hour, alarm.getHeure().minute, true);
	    	dialog.show();
		}*/
		
		//On replanifie l'alarme.
		planifierAlarm();
    }
    
    /*
     * Chargement des informations du reveil.
     * Ici pour la sauvegarde on a simplement déserialiser l'objet Alarm.
     */
    public void charger(){
    	alarm = null;
    	try {
    		ObjectInputStream alarmOIS= new ObjectInputStream(openFileInput("alarm.serial"));
    		alarm = (Alarm) alarmOIS.readObject(); 
    		alarmOIS.close();
		}
    	catch(FileNotFoundException fnfe){
    		alarm = new Alarm();
        	alarm.setActive(true);
        	Time t = new Time();
        	t.hour = 7;
        	t.minute = 30;
        	alarm.setHeure(t);
    	}
    	catch(IOException ioe) {
			ioe.printStackTrace();
		}
    	catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
    }   
    
    /*
     * Sauvegarde des informations du reveil
     */
    public void sauver(){
    	try {
    		ObjectOutputStream alarmOOS= new ObjectOutputStream(openFileOutput("alarm.serial",MODE_WORLD_WRITEABLE));
			alarmOOS.writeObject(alarm);
			alarmOOS.flush();
			alarmOOS.close();
		}
    	catch(IOException ioe) {
			ioe.printStackTrace();
		}
    }
	
    	
    /*
	 * Job de planification du reveil
	 */
    private void planifierAlarm() {
		//Récupération de l'instance du service AlarmManager.
		AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		//On instancie l'Intent qui va être appelé au moment du reveil.
		Intent intent = new Intent(this, AlarmReceiver.class);
		
		//On créer le pending Intent qui identifie l'Intent de reveil avec un ID et un/des flag(s)
		PendingIntent pendingintent = PendingIntent.getBroadcast(this, ALARM_ID, intent, 0);
		
		//On annule l'alarm pour replanifier si besoin
		am.cancel(pendingintent);
		
		//La on va déclencher un calcul pour connaitre le temps qui nous sépare du prochain reveil.
		Calendar reveil  = Calendar.getInstance();
		reveil.set(Calendar.HOUR_OF_DAY, alarm.getHeure().hour);
		reveil.set(Calendar.MINUTE, alarm.getHeure().minute);
		if(reveil.compareTo(Calendar.getInstance()) == -1)
			reveil.add(Calendar.DAY_OF_YEAR, 1);
		
		Calendar cal = Calendar.getInstance();
		reveil.set(Calendar.SECOND, 0);
		cal.set(Calendar.SECOND, 0);
		long diff = reveil.getTimeInMillis() - cal.getTimeInMillis();
		
		//On ajoute le reveil au service de l'AlarmManager
		am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis() + diff, pendingintent);
		Toast.makeText(this, "Alarme programmée le " + 
				reveil.get(Calendar.DAY_OF_MONTH) + " à " + 
				reveil.get(Calendar.HOUR_OF_DAY) + ":" + + 
				reveil.get(Calendar.MINUTE), Toast.LENGTH_SHORT).show();
	}
    
    public void addAlarm(View target) {
    	startActivity(new Intent(this, AlarmSettingsActivity.class));    	
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}