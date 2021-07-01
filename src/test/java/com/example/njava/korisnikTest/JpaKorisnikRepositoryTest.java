package com.example.njava.korisnikTest;

import com.example.njava.korisnik.Korisnik;
import com.example.njava.korisnik.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaKorisnikRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findOneByUsername() {
        Korisnik korisnik = userRepository.findOneByUsername("user").orElse(null);

        Assertions.assertNotNull(korisnik);

        Assertions.assertEquals(korisnik.getUsername(), "user");
    }
}
