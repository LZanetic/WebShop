package com.example.njava.korisnik;

import java.util.Optional;
import java.util.Set;

public interface KorisnikRepository {

    Set<Korisnik> findAll();

    Optional<Korisnik> findByUsername(String username);

    Optional<Korisnik> save(Korisnik korisnik);

    Optional<Korisnik> update(String username, Korisnik updatedKorisnik);

    void deleteByUsername(String username);
}
