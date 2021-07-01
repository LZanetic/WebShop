package com.example.njava.korisnik;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface KorisnikService {

    Set<KorisnikDTO> findAll();

    Optional<KorisnikDTO> findByUsername(String username);

    Optional<KorisnikDTO> save(KorisnikCommand korisnikCommand);

    Optional<KorisnikDTO> update(String username, KorisnikCommand updatedKorisnikCommand);

    void deleteByUsername(String username);
}
