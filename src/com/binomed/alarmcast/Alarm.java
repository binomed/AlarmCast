package com.binomed.alarmcast;

import java.io.Serializable;
import android.text.format.Time;

/**
 * @author Gawel
 *
 */
public class Alarm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private Time heure;
	private String repetitions;
	private int type;
	
	private boolean active;
	
	public Time getHeure() {
		return heure;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setRepetitions(String repetitions) {
		this.repetitions = repetitions;
	}
	public String getRepetitions() {
		return repetitions;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
}
