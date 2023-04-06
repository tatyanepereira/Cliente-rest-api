package br.com.taking.clientesrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.taking.clientesrestapi.model.Usuario;
import br.com.taking.clientesrestapi.security.JwtUtils;
import br.com.taking.clientesrestapi.security.request.LoginRequest;
import br.com.taking.clientesrestapi.security.request.SignUpRequest;
import br.com.taking.clientesrestapi.security.response.UserInfoResponse;
import br.com.taking.clientesrestapi.service.UsuarioService;
import br.com.taking.clientesrestapi.service.exception.UsernameOuEmailExistenteException;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AutenticacaoController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST)
	public ResponseEntity<String> registrarUsuario(@RequestBody SignUpRequest request){
		
		try {
			
			usuarioService.registrarUsuario(request);
			
		} catch (UsernameOuEmailExistenteException e) {
			
			return new ResponseEntity<String>("E-mail ou username já utilizado", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Usuario criado com sucesso", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/api/auth/signin", method = RequestMethod.POST)
	public ResponseEntity<UserInfoResponse> login(@RequestBody LoginRequest request){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Usuario userDetails = (Usuario) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		
		UserInfoResponse userResponse = new UserInfoResponse();
		userResponse.setId(userDetails.getId());
		userResponse.setUsername(userDetails.getUsername());
		userResponse.setEmail(userDetails.getEmail());
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
				.body(userResponse);
	}
	
	
	@RequestMapping(value = "/api/auth/signout", method = RequestMethod.POST)
	public ResponseEntity<String> logout(){
		
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body("Usuário deslogado...");
		
	} 

}
