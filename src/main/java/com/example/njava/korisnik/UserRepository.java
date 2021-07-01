package com.example.njava.korisnik;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Korisnik, Long> {
        Optional<Korisnik> findOneByUsername(String username);
}
