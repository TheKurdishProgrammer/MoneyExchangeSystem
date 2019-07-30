package com.example.mycompany.paymentSystem.db;

import com.example.mycompany.paymentSystem.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();

        // Crete users
        User dan = new User("user",passwordEncoder.encode("user123"),"USER","");
        User admin = new User("baran",passwordEncoder.encode("baran123"),"BRANCH","ACCESS_TEST1");
        User manager = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");

        List<User> users = Arrays.asList(dan,admin,manager);


        // Save to db
        this.userRepository.saveAll(users);
    }
}
