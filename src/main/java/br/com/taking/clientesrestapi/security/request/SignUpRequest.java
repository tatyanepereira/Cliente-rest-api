package br.com.taking.clientesrestapi.security.request;

public class SignUpRequest {
	
	private String username;
	private String password;
	private String email;
	private String nome;
	
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
