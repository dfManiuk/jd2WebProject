package by.htp.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User  {
	
	public User() {}
	
	@Id
	@Column(name = "iduser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "userName")
	private String name;
	
	@Column(name = "userPosition")
	private String position;
	
	@Column(name = "idspecialization")
	private int specialization;
	
	@Column(name = "login")
	private String 	login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "out")
	private boolean out;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", position=" + position + ", specialization=" + specialization
				+ ", login=" + login + ", password=" + password + ", out=" + out + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (out ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + specialization;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (out != other.out)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (specialization != other.specialization)
			return false;
		return true;
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

	public int getSpecialization() {
		return specialization;
	}

	public void setSpecialization(int specialization) {
		this.specialization = specialization;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isOut() {
		return out;
	}

	public void setOut(boolean out) {
		this.out = out;
	}

	
	
	
}
