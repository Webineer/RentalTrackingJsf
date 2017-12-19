package com.webineering.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

	private String username;
	private String password;
	private String errorMsg;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isValidUser() {
		return "admin".equals(this.username) && "admin".equals(this.password);
	}

	public String validate() {
		if ("admin".equals(username) && "admin1234".equals(password)) {
			errorMsg = null;
			return "welcome";
		} else {
			errorMsg = "Invalid user id or password. Please try again.";
			return null;
		}
	}

}
