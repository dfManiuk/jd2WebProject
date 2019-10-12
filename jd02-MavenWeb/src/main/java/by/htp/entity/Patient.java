package by.htp.entity;

import java.io.Serializable;

public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3210426395671567682L;
	
	private int idPatient;
	private String name;
	private String passport;
	private String data; 
	private String adress;
	private String telephone;
	private int idMedication; 
	private int idCard;
	
	public Patient() {}
	
	
	public int getIdPatient() {
		return idPatient;
	}


	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getIdMedication() {
		return idMedication;
	}
	public void setIdMedication(int idMedication) {
		this.idMedication = idMedication;
	}
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	@Override
	public String toString() {
		return "Patient [idPatient=" + idPatient + ", name=" + name + ", passport=" + passport + ", data=" + data
				+ ", adress=" + adress + ", telephone=" + telephone + ", idMedication=" + idMedication + ", idCard="
				+ idCard + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + idCard;
		result = prime * result + idMedication;
		result = prime * result + idPatient;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
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
		Patient other = (Patient) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idCard != other.idCard)
			return false;
		if (idMedication != other.idMedication)
			return false;
		if (idPatient != other.idPatient)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	} 
	
}
