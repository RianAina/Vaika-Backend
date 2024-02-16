package com.vaika.backend.service;

import com.vaika.backend.entity.User;
import com.vaika.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    public void inscription(User user){
        this.userRepository.save(user);
    }
}
