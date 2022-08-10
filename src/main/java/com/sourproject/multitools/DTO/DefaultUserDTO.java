package com.sourproject.multitools.DTO;

import java.io.Serializable;

public class DefaultUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idDefaultUser;
	private String userName;
	private String email;
	private String password;
	private Boolean isAdmin;

	public DefaultUserDTO() {

	}

	public Integer getIdDefaultUser() {
		return idDefaultUser;
	}

	public void setIdDefaultUser(Integer idDefaultUser) {
		this.idDefaultUser = idDefaultUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
