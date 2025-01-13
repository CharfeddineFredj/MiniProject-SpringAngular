package com.securityecommerce.controllers;



import com.securityecommerce.models.User;
import com.securityecommerce.repository.UserRepository;
import com.securityecommerce.service.UserService;
import com.securityecommerce.utils.StorgeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StorgeService storgeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;

    @GetMapping("/all")
    public List<User> ListUser() {
        return userService.getall();
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User u) {
        return userService.create(u);
    }

    @PostMapping("/saveimguser")
    public User saveimguser(User u, @RequestParam("file") MultipartFile file) {
        String image = storgeService.store(file);
        u.setImage(image);
        return userService.create(u);
    }

    @GetMapping("/getone/{id}")
    public User getone(@PathVariable Long id) {
        return userService.getbyId(id);
    }

    @PutMapping("/updateu/{idc}")
    public User updateUser(@PathVariable Long idc, @RequestBody User u) {
        u.setId(idc);
        return userService.update(u);
    }

    @DeleteMapping("/delet/{id}")
    public HashMap<String, String> deletuser(@PathVariable Long id) {

        HashMap message = new HashMap();
        try {
            userService.delete(id);
            message.put("etat", "user delet");
            return message;
        } catch (Exception e) {
            message.put("etat", "Error");
            return message;
        }
    }

    @GetMapping("/confirme")
    public String confirmemail(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setConfirme(true);
            userService.create(user);
            return "User confirmed!";
        }else{
            throw new RuntimeException("email non confirme");
        }

    }

    @PostMapping("/forgetpassword")
    public  HashMap<String,String> forgetpassword(String email) throws MessagingException {
        HashMap message = new HashMap();
        User userexisting = userRepository.findByEmail(email);
        if (userexisting == null){
            message.put("user","user not found");
            return message;
        }
        UUID Token =UUID.randomUUID();
        userexisting.setPasswordResetToken(Token.toString());
        userexisting.setId(userexisting.getId());
        String from ="admin@gmail.com" ;
        String to = userexisting.getEmail();
        MimeMessage messagee = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messagee);
        helper.setSubject("Complete Registration!");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText("Votre code est :"+userexisting.getPasswordResetToken(),true);
        javaMailSender.send(messagee);
        userRepository.saveAndFlush(userexisting);
        message.put("user","user found, check your eamil");
        return message;
    }
    @PostMapping("/resetpassword/{passwordResetToken}")
    public HashMap<String, String> resetpassword(@PathVariable String passwordResetToken, @RequestBody HashMap<String, String> request) {
        String newPassword = request.get("newPassword");
        User userexisting = userRepository.findByPasswordResetToken(passwordResetToken);
        HashMap<String, String> message = new HashMap<>();

        if (userexisting != null) {
            userexisting.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userexisting.setPasswordResetToken(null);
            userRepository.save(userexisting);
            message.put("resetpassword", "processed");
            return message;
        } else {
            message.put("resetpassword", "failed");
            return message;
        }
    }


}


