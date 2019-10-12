package by.htp.entity;

import java.io.Serializable;
import java.util.List;

public class Doctor implements Serializable{
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", position=" + position + ", specialization=" + specialization
				+ ", patients=" + patients + ", getPatients()=" + getPatients() + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getPosition()=" + getPosition() + ", getSpecialization()="
				+ getSpecialization() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Doctor(){}
	
	private int id;
	private String name;
	private String position;
	private String specialization;
	private List<Patient> patients;
	
//	public void addPatientfromList(Patient patient) {
//		if (patients == null) {
//			patients = new ArrayList<Patient>();
//		}
//		patients.add(patient);
//	}
	
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	
}
