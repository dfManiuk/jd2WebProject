package by.htp.entity;

import java.util.Date;

public class Card {

	int idPatient;
	String patientName;
	Date date;
	String epicris;
	Medication medication;
	
	public Card() {}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEpicris() {
		return epicris;
	}

	public void setEpicris(String epicris) {
		this.epicris = epicris;
	}

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((epicris == null) ? 0 : epicris.hashCode());
		result = prime * result + idPatient;
		result = prime * result + ((medication == null) ? 0 : medication.hashCode());
		result = prime * result + ((patientName == null) ? 0 : patientName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (epicris == null) {
			if (other.epicris != null)
				return false;
		} else if (!epicris.equals(other.epicris))
			return false;
		if (idPatient != other.idPatient)
			return false;
		if (medication == null) {
			if (other.medication != null)
				return false;
		} else if (!medication.equals(other.medication))
			return false;
		if (patientName == null) {
			if (other.patientName != null)
				return false;
		} else if (!patientName.equals(other.patientName))
			return false;
		return true;
	}
	
	
}
