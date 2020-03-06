//package com.oc.projets.projet_7.restController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.codec.json.Jackson2JsonDecoder;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.oc.projets.projet_7.dto.UsagerLoginDTO;
//import com.oc.projets.projet_7.jwt.JwtTokenProvider;
//import com.oc.projets.projet_7.service.UsagerService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;
//
//	@Autowired
//	private UsagerService usagerService;
//
//	@PostMapping("/login")
//	public ResponseEntity signin(@RequestBody UsagerLoginDTO usagerLoginDTO) {
//		System.out.println("Dans signin.");
//		try {
//			String email = usagerLoginDTO.getEmail();
//			System.out.println("email : " + email);
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, usagerLoginDTO.getPassword()));
//			String token = jwtTokenProvider.createToken(email, this.usagerService.findByEmail(email).getRole());
//
//			Map<Object, Object> model = new HashMap<>();
//			model.put("username", email);
//			model.put("token", token);
//			return ResponseEntity.ok(model);
//		} catch (AuthenticationException e) {
//			throw new BadCredentialsException("Invalid username/password supplied");
//		}
//	}
//}
