package com.vaika.backend.service;

import com.vaika.backend.TypeDeRole;
import com.vaika.backend.entity.Roles;
import com.vaika.backend.entity.User;
import com.vaika.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void inscription(User user){
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("votre mail est invalide");
        }
        if (!user.getEmail().contains(".")) {
            throw new RuntimeException("votre mail est invalide");
        }
        Optional<User> userOptional=this.userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("votre mail est deja utilisé");
        }
        String mdpEncode= this.bCryptPasswordEncoder.encode(user.getPassword());
        Roles userRole= new Roles();
        userRole.setLibelle(TypeDeRole.User);
        user.setRole(userRole);
        user.setPassword(mdpEncode);
        this.userRepository.save(user);
    }
}
