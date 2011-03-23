package com.binomed.alarmcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Toast.makeText(context, "C'est l'heure !!!",Toast.LENGTH_LONG).show();			
			//On peut mettre ce que l'on veut. Vibreur, lecture d'un mp3 ou autre.
		} catch (Exception r) {
			Toast.makeText(context, "Erreur.",Toast.LENGTH_SHORT).show();
			r.printStackTrace();
		}		
	}
}
