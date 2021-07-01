package com.example.njava.korisnik;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MockKorisnikResitory implements KorisnikRepository {

    private final Set<Korisnik> MOCKED_KORISNICI = new HashSet<>(
            Arrays.asList(
                    new Korisnik(1, "A", "A", "A", "12345678", "AAAA", "aaaaa", "a@a.a", "09123456789"),
                    new Korisnik(2, "B", "B", "B", "12345678", "AAAA", "aaaaa", "a@a.a", "09123456789"),
                    new Korisnik(3, "C", "C", "C", "12345678", "AAAA", "aaaaa", "a@a.a", "09123456789"),
                    new Korisnik(4, "D", "D", "D", "12345678", "AAAA", "aaaaa", "a@a.a", "09123456789")
                    )
    );

    @Override
    public Set<Korisnik> findAll() {
        return MOCKED_KORISNICI;
    }

    @Override
    public Optional<Korisnik> findByUsername(String username) {
        return MOCKED_KORISNICI.stream().filter(it -> Objects.equals(it.getUsername(), username)).findAny();
    }

    @Override
    public Optional<Korisnik> save(Korisnik korisnik) {
        if(!MOCKED_KORISNICI.contains(korisnik)){
            MOCKED_KORISNICI.add(korisnik);
            return Optional.of(korisnik);
        }else{
            return Optional.empty();
        }

    }

    @Override
    public Optional<Korisnik> update(String username, Korisnik updatedKorisnik) {
        boolean exists = MOCKED_KORISNICI.removeIf(
                it ->Objects.equals(it.getUsername(), username) &&
                        Objects.equals(it.getUsername(), updatedKorisnik.getUsername())
        );

        if (exists){
            MOCKED_KORISNICI.add(updatedKorisnik);
            return  Optional.of(updatedKorisnik);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public void deleteByUsername(String username) {
        MOCKED_KORISNICI.removeIf(korisnik -> Objects.equals(korisnik.getUsername(), username));
    }
}
