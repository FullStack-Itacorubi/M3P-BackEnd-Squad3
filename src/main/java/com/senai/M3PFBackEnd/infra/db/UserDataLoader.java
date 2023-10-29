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
        if (userRepository.count() != 0) return;
        UserEntity seederAdmin = new UserEntity(
            "Manda-chuva",
            Genre.CISGENDER,
            "000.000.000-00",
            "(00) 0 0000-0000",
            "administrador@email.com",
            "senhamestre",
            UserType.ADMINISTRATOR
        );
        userRepository.save(seederAdmin);
        
        System.out.println(userRepository.count());
    }
}
