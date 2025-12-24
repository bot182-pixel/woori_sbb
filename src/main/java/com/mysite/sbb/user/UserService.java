package com.mysite.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void create(String username, String password){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        //password 암호화
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        user.setPassword(password);
        this.userRepository.save(user);
    }
}
