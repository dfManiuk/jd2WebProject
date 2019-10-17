package by.htp.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Medication {
	private int idPatient;
	private String procedure;
	private String medication;
	private String operation; 
	private int idUser;
	private int periodicity;

	HashMap<Integer, List<String>> procedurs;
	HashMap<Integer, List<String>> medications ;
	HashMap<Integer, List<String>> operations;
	
	public Medication() {
		
	}

	

	public void setProcedure(int idUser, String procedure ) {
		if (procedurs == null) {
			procedurs = new HashMap<Integer, List<String>>();
		}
		
		if (procedurs.containsKey(idUser)) {
			List<String> list = procedurs.get(idUser);
			list.add(procedure);
		} else {
			List<String> list = new ArrayList<String>();
			list.add(procedure);
			procedurs.put(idUser, list);
		}
	
	}
	
	public void setMedication(int idUser, String medication ) {
		if (medications == null) {
			medications = new HashMap<Integer, List<String>>();
		}
		
		if (medications.containsKey(idUser)) {
			List<String> list = medications.get(idUser);
			if (medication !=null) {
				list.add(medication);
			}
			
		} else {
			List<String> list = new ArrayList<String>();
			if (medication != null) {
				list.add(medication);
			}
			
			medications.put(idUser, list);
		}
	}
	
	public void setOperation(int idUser, String operation ) {
		if (operations == null) {
			operations = new HashMap<Integer, List<String>>();
		}
		
		if (operations.containsKey(idUser)) {
			List<String> list = operations.get(idUser);
			list.add(operation );
		} else {
			List<String> list = new ArrayList<String>();
			list.add(operation );
			operations.put(idUser, list);
		}
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public String getProcedure() {
		return procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public HashMap<Integer, List<String>> getProcedurs() {
		return procedurs;
	}

	public void setProcedurs(HashMap<Integer, List<String>> procedurs) {
		this.procedurs = procedurs;
	}

	public HashMap<Integer, List<String>> getMedications() {
		return medications;
	}

	public void setMedications(HashMap<Integer, List<String>> medications) {
		this.medications = medications;
	}

	public HashMap<Integer, List<String>> getOperations() {
		return operations;
	}

	public void setOperations(HashMap<Integer, List<String>> operations) {
		this.operations = operations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPatient;
		result = prime * result + idUser;
		result = prime * result + ((medication == null) ? 0 : medication.hashCode());
		result = prime * result + ((medications == null) ? 0 : medications.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((operations == null) ? 0 : operations.hashCode());
		result = prime * result + ((procedure == null) ? 0 : procedure.hashCode());
		result = prime * result + ((procedurs == null) ? 0 : procedurs.hashCode());
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
		Medication other = (Medication) obj;
		if (idPatient != other.idPatient)
			return false;
		if (idUser != other.idUser)
			return false;
		if (medication == null) {
			if (other.medication != null)
				return false;
		} else if (!medication.equals(other.medication))
			return false;
		if (medications == null) {
			if (other.medications != null)
				return false;
		} else if (!medications.equals(other.medications))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (operations == null) {
			if (other.operations != null)
				return false;
		} else if (!operations.equals(other.operations))
			return false;
		if (procedure == null) {
			if (other.procedure != null)
				return false;
		} else if (!procedure.equals(other.procedure))
			return false;
		if (procedurs == null) {
			if (other.procedurs != null)
				return false;
		} else if (!procedurs.equals(other.procedurs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medication [idPatient=" + idPatient + ", procedure=" + procedure + ", medication=" + medication
				+ ", operation=" + operation + ", idUser=" + idUser + ", procedurs=" + procedurs + ", medications="
				+ medications + ", operations=" + operations + "]";
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}

	
	
}
