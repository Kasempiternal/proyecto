package rest.alcoholimpiadas;

import javax.xml.bind.annotation.XmlRootElement;

import rest.alcoholimpiadas.*;


@XmlRootElement
public class User {
	
	
	private int idUser;
	private String name;
	private int type;
	private String pass;
	
	
	public User() {
		super();
	}


	public User(int idUser, String name, int type, String pass) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.type = type;
		this.pass = pass;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", type=" + type + ", pass=" + pass + "]";
	}




}
