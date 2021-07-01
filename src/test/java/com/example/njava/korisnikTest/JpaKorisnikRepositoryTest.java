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
    void findOneByUsername(){
        Korisnik korisnik = userRepository.findOneByUsername("user").orElse(null);

        Assertions.assertNotNull(korisnik);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(korisnik.getIme(), "Ime2"),
                ()-> Assertions.assertEquals(korisnik.getPrezime(), "prezime2"),
                ()-> Assertions.assertEquals(korisnik.getUsername(), "user"),
                ()-> Assertions.assertEquals(korisnik.getPassword(), "$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy"),
                ()-> Assertions.assertEquals(korisnik.getKontakt(), "09954785858"),
                ()-> Assertions.assertEquals(korisnik.getLokacija(), "lok"),
                ()-> Assertions.assertEquals(korisnik.getEmail(), "mail"),
                ()-> Assertions.assertEquals(korisnik.getBrojTelefona(), null)
        );}
}
