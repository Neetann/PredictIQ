/*
 * package com.nokia.predict.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.nokia.predict.model.MyAppUser; import
 * com.nokia.predict.model.MyAppUserRepository; import
 * com.nokia.predict.utils.JwtTokenUtil;
 * 
 * @RestController public class VerificationController {
 * 
 * @Autowired private MyAppUserRepository myAppUserRepository;
 * 
 * @Autowired private JwtTokenUtil jwtUtil;
 * 
 * @GetMapping("/req/signup/verify") public ResponseEntity
 * verifyEmail(@RequestParam("token") String token) { String emailString =
 * jwtUtil.extractEmail(token); MyAppUser user =
 * myAppUserRepository.findByEmail(emailString); if (user == null ) { return
 * ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token Expired!"); }
 * 
 * 
 * // user.setVerified(true); myAppUserRepository.save(user);
 * 
 * return
 * ResponseEntity.status(HttpStatus.CREATED).body("Email successfully verified!"
 * ); }
 * 
 * 
 * }
 * 
 */