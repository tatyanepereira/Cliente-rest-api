package br.com.taking.clientesrestapi.security.response;

public class UserInfoResponse {
	
	private int id;
	private String username;
	private String email;
	
	
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
