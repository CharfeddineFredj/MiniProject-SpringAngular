package com.securityecommerce.controllers;



import com.securityecommerce.models.Customer;
import com.securityecommerce.models.ERole;
import com.securityecommerce.models.Role;
import com.securityecommerce.models.User;
import com.securityecommerce.payload.request.SignupRequest;
import com.securityecommerce.payload.response.MessageResponse;
import com.securityecommerce.repository.RoleRepository;
import com.securityecommerce.repository.UserRepository;
import com.securityecommerce.security.jwt.JwtUtils;
import com.securityecommerce.security.services.RefreshTokenService;
import com.securityecommerce.service.CustomerService;
import com.securityecommerce.utils.EmailService;
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
@RequestMapping("/customers")
public class CustomerController{
    @Autowired
    private CustomerService customerService;
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
    EmailService emailService;
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/all")
    public List<Customer> ListCustomer(){
        return customerService.getall();
    }
    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer c){
        return customerService.create(c);
    }
    @GetMapping("getone/{id}")
    public Customer getone(@PathVariable Long id){
        return customerService.getbyId(id);
    }
    @PutMapping("updatec/{idc}")
    public Customer updateCustomer(@PathVariable Long idc , @RequestBody Customer c){
        c.setId(idc);
        return  customerService.update(c);
    }
    @DeleteMapping("delet/{id}")
    public HashMap<String,String> deletCustomer(@PathVariable Long id) {

        HashMap message = new HashMap();
        try {
            customerService.delete(id);
            message.put("etat", "customer delet");
            return message;
        } catch (Exception e) {
            message.put("etat", "Error");
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
            Customer customer = new Customer(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()),signUpRequest.getAdress(),signUpRequest.getCity());

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();
                            Role modRole = roleRepository.findByName(ERole.ROLE_CUSOTMER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
            customer.setRoles(roles);
            customerService.create(customer);
           // mail confirmation
            //user.setConfirm(false);
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

            return ResponseEntity.ok(new MessageResponse("Customer registered successfully!,verife votre email for confirme"));
        }

}
