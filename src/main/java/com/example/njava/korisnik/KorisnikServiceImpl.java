package com.example.njava.korisnik;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KorisnikServiceImpl implements  KorisnikService{

    private final KorisnikRepository korisnikRepository;
    private final UserRepository userRepository;

    public KorisnikServiceImpl(KorisnikRepository korisnikRepository, UserRepository userRepository) {
        this.korisnikRepository = korisnikRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Set<KorisnikDTO> findAll() {
        return korisnikRepository.findAll().stream().map(this::mapKorisnikToDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<KorisnikDTO> findByUsername(String username) {
        return userRepository.findOneByUsername(username).map(this::mapKorisnikToDTO);
    }

    @Override
    public Optional<KorisnikDTO> save(KorisnikCommand korisnikCommand) {
        return korisnikRepository.save(mapCommandToKorisnik(korisnikCommand)).map(this::mapKorisnikToDTO);
    }

    @Override
    public Optional<KorisnikDTO> update(String username, KorisnikCommand updatedKorisnikCommand) {
        return korisnikRepository.update(username, mapCommandToKorisnik(updatedKorisnikCommand)).map(this::mapKorisnikToDTO);
    }

    @Override
    public void deleteByUsername(String username) {
        korisnikRepository.deleteByUsername(username);
    }

    private KorisnikDTO mapKorisnikToDTO(final Korisnik korisnik){
        KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik.getIme(), korisnik.getPrezime(), korisnik.getUsername(), korisnik.getKontakt(), korisnik.getLokacija(), korisnik.getBrojTelefona());
        korisnikDTO.setAuthorities(korisnik.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
        return korisnikDTO;
    }

    private Korisnik mapCommandToKorisnik(final KorisnikCommand korisnikCommand){
        Korisnik korisnik = new Korisnik(korisnikCommand.getIme(), korisnikCommand.getPrezime(), korisnikCommand.getUsername(), korisnikCommand.getPassword(), korisnikCommand.getKontakt(), korisnikCommand.getLokacija(), korisnikCommand.getEmail(), korisnikCommand.getBrojTelefona());
        korisnik.setAuthorities(korisnikCommand.getAuthorities());
        return korisnik;
    }


}
