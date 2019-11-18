package com.hdt.example_assess.service.Impl;

import com.hdt.example_assess.entity.User;
import com.hdt.example_assess.respository.UserRepository;
import com.hdt.example_assess.utils.common.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
    public User loadUserById(Long id){
        User user = userRepository.findById(id).get();
        if (user == null) {
            throw new UsernameNotFoundException("sad");
        }
        return user;
    }

}
