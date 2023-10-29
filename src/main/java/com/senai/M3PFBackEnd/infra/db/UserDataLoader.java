package com.senai.M3PFBackEnd.infra.db;

import com.senai.M3PFBackEnd.entities.UserEntity;
import com.senai.M3PFBackEnd.enums.Genre;
import com.senai.M3PFBackEnd.enums.UserType;
import com.senai.M3PFBackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            UserEntity user1 = new UserEntity(
                    // 1L,
                    "Manda-chuva",
                    Genre.CISGENDER,
                    "390.053.750-00",
                    "(49) 9 8622-8534",
                    "administrador@email.com",
                    // true,
                    "senhamestre",
                    // "ADMINISTRATOR"
                    UserType.ADMINISTRATOR
            );
            userRepository.save(user1);
        }
        System.out.println(userRepository.count());
    }
}
