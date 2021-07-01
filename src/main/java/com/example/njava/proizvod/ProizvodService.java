package com.example.njava.proizvod;

import java.util.List;
import java.util.Optional;

public interface ProizvodService {

    List<ProizvodDTO> findAll();
    Optional<ProizvodDTO> findById(Long id);
    List<ProizvodDTO> findByKorisnikId(Long korisnikId);
    void deleteById(Long id);
    void deleteByNaslov(String naslov);
    ProizvodDTO findByNaslov(String naslov);
    Proizvod save(Proizvod proizvod);
    void update(String naslov, String opis, String stanje, Integer cijena, String kategorija, Long id);
}
