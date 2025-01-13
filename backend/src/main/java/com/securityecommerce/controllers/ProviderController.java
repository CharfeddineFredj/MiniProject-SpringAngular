package com.securityecommerce.controllers;


import com.securityecommerce.models.Customer;
import com.securityecommerce.models.ERole;
import com.securityecommerce.models.Provider;
import com.securityecommerce.models.Role;
import com.securityecommerce.payload.request.SignupRequest;
import com.securityecommerce.payload.response.MessageResponse;
import com.securityecommerce.repository.RoleRepository;
import com.securityecommerce.repository.UserRepository;
import com.securityecommerce.security.jwt.JwtUtils;
import com.securityecommerce.security.services.RefreshTokenService;
import com.securityecommerce.service.ProviderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/All")
    public List<Provider> ListProvider(){
        return  providerService.getall();
    }
    @PostMapping("/save")
    public Provider savaProvider(@RequestBody Provider p){
        return  providerService.create(p);
    }
    @GetMapping("getone/{id}")
    public Provider getone(@PathVariable Long id){
        return  providerService.getbyId(id);
    }
    @PutMapping("updatep/{idp}")
    public Provider updateProvider(@PathVariable Long idp, @RequestBody Provider p){
        p.setId(idp);
        return providerService.update(p);
    }
    @DeleteMapping("delet/{id}")
    public HashMap<String,String> deletprovider(@PathVariable Long id){
        HashMap message = new HashMap();
        try {
            providerService.delete(id);
            message.put("etat", "provider delet");
            return message;
        }catch (Exception e){
            message.put("etat","Error");
            return message;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Provider provider = new Provider(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getMatricule(),signUpRequest.getService(),signUpRequest.getCompany());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Role modRole = roleRepository.findByName(ERole.ROLE_PROVIDER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(modRole);
            provider.setRoles(roles);
        providerService.create(provider);
        String from ="admin@gmail.com" ;
        String to = signUpRequest.getEmail();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setSubject("Confirmation Registration!");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText("<HTML><body> <a href=\"http://localhost:8085/users/confirme?email="
                +signUpRequest.getEmail()+"\">VERIFY</a></body></HTML>",true);
        javaMailSender.send(message);

        return ResponseEntity.ok(new MessageResponse("Provider registered successfully!,verife votre email for confirme"));


    }



}
