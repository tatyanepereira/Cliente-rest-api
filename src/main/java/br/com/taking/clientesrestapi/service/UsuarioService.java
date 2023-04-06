package br.com.taking.clientesrestapi.service;

import br.com.taking.clientesrestapi.security.request.SignUpRequest;
import br.com.taking.clientesrestapi.service.exception.UsernameOuEmailExistenteException;

public interface UsuarioService {
	
	
	void registrarUsuario(SignUpRequest request) throws UsernameOuEmailExistenteException;
	

}
